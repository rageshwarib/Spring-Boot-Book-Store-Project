package com.bridgelabz.bookstore.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.WishlistDto;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Wishlist;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.WishlistRepository;
import com.bridgelabz.bookstore.utility.ConverterService;

@Service
@Transactional
public class WishlistServiceImpl implements IWishlistService {
	 @Autowired
	    private ConverterService converterService;

	    @Autowired
	    private WishlistRepository wishlistRepository;

	    @Autowired
	    private BookRepository bookStoreRepository;

	    @Override
	    public String addToWishlist(WishlistDto wishlistDto) {
	        Wishlist wishlist = converterService.convertToWishlistEntity(wishlistDto);
	        if(wishlistRepository.existsWishlistByBookId(wishlist.getBookId()) && wishlistRepository.existsWishlistByUserId(wishlist.getUserId()))
	            wishlistRepository.deleteWishlistByBookIdAndUserId(wishlist.getBookId(), wishlist.getUserId());
	        wishlistRepository.save(wishlist);
	        return "Added to Wishlist successfully";
	    }

	    @Override
	    public String removeFromWishlist(WishlistDto wishlistDto) {
	        Wishlist wishlist = converterService.convertToWishlistEntity(wishlistDto);
	        wishlistRepository.deleteWishlistByBookIdAndUserId(wishlist.getBookId(), wishlist.getUserId());
	        return "Removed from Wishlist successfully";
	    }
	    @Override
	    public List<Book> getAllBooksList(int userId) {
	        List<Book> wishlistBooks = new ArrayList<>();
	        List<Wishlist> allByUserId = wishlistRepository.findAllByUserId(userId);
	        for(Wishlist wishlist : allByUserId) {
	            wishlistBooks.add(bookStoreRepository.findById(wishlist.getBookId()));
	        }
	        return wishlistBooks;
	    }

}
