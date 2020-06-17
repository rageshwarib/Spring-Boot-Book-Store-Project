package com.bridgelabz.bookstore.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Cart;
import com.bridgelabz.bookstore.modelmapper.DTOEntityMapper;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.CartRepository;
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


    @Override
    public String addToCart(CartDto cartDto) {
        Cart cart = converterService.convertToCartEntity(cartDto);
        if (cartRepository.existsCartByUserId(cart.getUserId()) && cartRepository.existsCartByBookId(cart.getBookId()))
            cartRepository.deleteCartsByBookIdAndUserId(cart.getBookId(), cart.getUserId());
        cartRepository.save(cart);
        return "Added to cart successfully";
    }

    @Override
    public String removeFromCart(CartDto cartDto) {
        Cart cart = converterService.convertToCartEntity(cartDto);
        cartRepository.deleteCartsByBookIdAndUserId(cart.getBookId(), cart.getUserId());
        return "Book Removed from cart Successfully";
    }

//    @Override
//    public List<BookCartDto> getAllCartBooks(int userId) {
//        List<BookCartDto> bookCartDto = new ArrayList<>();
//        try {
//            List<Cart> allByUserId = cartRepository.findAllByUserId(userId);
//            for (Cart cart : allByUserId) {
//                if (cart.getBookQuantity() == 0)
//                    cartRepository.deleteCartsByBookIdAndUserId(cart.getBookId(), cart.getUserId());
//                bookCartDto.add(new BookCartDto((int) bookRepository.findById(cart.getBookId()).getId(),
//                        bookRepository.findById(cart.getBookId()).getAuthor(),
//                        bookRepository.findById(cart.getBookId()).getTitle(),
//                        bookRepository.findById(cart.getBookId()).getImage(),
//                        bookRepository.findById(cart.getBookId()).getPrice(),
//                        cartRepository.findByBookIdAndUserId(cart.getBookId(), cart.getUserId()).getBookQuantity()));
//            }
//        } catch (NullPointerException e) {
//            e.printStackTrace();
//        }
//        return bookCartDto;
//    }
}
