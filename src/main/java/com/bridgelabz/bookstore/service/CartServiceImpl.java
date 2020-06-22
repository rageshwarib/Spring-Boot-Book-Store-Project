package com.bridgelabz.bookstore.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.BookCartDto;
import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.exception.UserException;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Cart;
import com.bridgelabz.bookstore.modelmapper.DTOEntityMapper;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.security.jwt.JwtUtils;
import com.bridgelabz.bookstore.utility.ConverterService;
@Service
@Transactional
public class CartServiceImpl implements ICartService{
	@Autowired
    private ConverterService converterService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserRepository userRepository;


    @Override
    public String addToCart(CartDto cartDto, String token) {
        Cart cart = converterService.convertToCartEntity(cartDto);
        Long userId = jwtUtils.getUserIdFromJwtToken(token);
        cart.setUserId(userId);
        userRepository.findById(userId).orElseThrow(() -> new UserException("Invalid User"));
        cartRepository.save(cart);
        return "Added to cart successfully";
    }

    @Override
    public String removeFromCart(CartDto cartDto, String token) {
        Cart cart = converterService.convertToCartEntity(cartDto);
        long userId = jwtUtils.getUserIdFromJwtToken(token);
        cartRepository.deleteCartByBookIdAndUserId(cart.getBookId(), userId);
        return "Book Removed from cart Successfully";
    }

    @Override
    public List<BookCartDto> getBooks(String token) {
        List<BookCartDto> bookCartDto = new ArrayList<>();
        Long userId = jwtUtils.getUserIdFromJwtToken(token);
        try {
            List<Cart> allByUserId = cartRepository.findAllByUserId(userId);
            for (Cart cart : allByUserId) {
                if (cart.getBookQuantity() > 0 ) {
                	Book book = bookRepository.findById(cart.getBookId());
                	System.out.println(book);
                	bookCartDto.add(converterService.convertToBookCartDto(cart));
                	
                 //   cartRepository.deleteCartByBookIdAndUserId(cart.getBookId(), cart.getUserId());
//                bookCartDto.add(new BookCartDto(cart.getBookId(),
//                        book.getAuthor(),
//                        book.getTitle(),
//                        book.getImage(),
//                        book.getPrice(),
//                        cart.getBookQuantity()));
            }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return bookCartDto;
    }
}
