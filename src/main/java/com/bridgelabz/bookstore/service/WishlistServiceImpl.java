package com.bridgelabz.bookstore.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.dto.WishlistDto;
import com.bridgelabz.bookstore.exception.UserException;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Cart;
import com.bridgelabz.bookstore.model.Wishlist;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.repository.WishlistRepository;
import com.bridgelabz.bookstore.security.jwt.JwtUtils;
import com.bridgelabz.bookstore.utility.MapperService;

@Service
@Transactional
public class WishlistServiceImpl implements IWishlistService {
	 @Autowired
	    private MapperService converterService;
	 	@Autowired
	    private WishlistRepository wishlistRepository;
	    @Autowired
	    private BookRepository bookStoreRepository;
	    @Autowired
	    private JwtUtils jwtUtils;
	    @Autowired
	    private UserRepository userRepository;

	    @Override
	    public void addToWishlist(WishlistDto wishlistDto, String token) {
	    	if (jwtUtils.validateJwtToken(token)) {
	        Wishlist wishlist = converterService.convertToWishlistEntity(wishlistDto);
	        Long userId = jwtUtils.getUserIdFromJwtToken(token);
	        wishlist.setUserId(userId);
	        userRepository.findById(userId);
	        wishlistRepository.save(wishlist);
	    } else
	    	throw new UserException(UserException.ExceptionType.JWT_NOT_VALID, "Token is not valid");
	    }

	    @Override
	    public void removeFromWishlist(WishlistDto wishlistDto, String token) {
	    	if (jwtUtils.validateJwtToken(token)) {
	    		 Wishlist wishlist = converterService.convertToWishlistEntity(wishlistDto);
	    		 long userId = jwtUtils.getUserIdFromJwtToken(token);
	    		 wishlist.setUserId(userId);
	    		 wishlistRepository.deleteWishlistByBookIdAndUserId(wishlist.getBookId(), userId);
	    	 } else
	    		 throw new UserException(UserException.ExceptionType.JWT_NOT_VALID, "Token is not valid");
	    }

	    @Override
	    public List<Book> getAllBooksList(String token) {
	        List<Book> wishlistBooks = new ArrayList<>();
	        Long userId = jwtUtils.getUserIdFromJwtToken(token);
	        List<Wishlist> allByUserId = wishlistRepository.findAllByUserId(userId);
	        for(Wishlist wishlist : allByUserId) {
	            wishlistBooks.add(bookStoreRepository.findById(wishlist.getBookId()));
	        }
	        return wishlistBooks;
	    }

}
