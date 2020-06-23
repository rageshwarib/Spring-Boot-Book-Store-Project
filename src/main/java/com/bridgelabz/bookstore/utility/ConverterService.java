package com.bridgelabz.bookstore.utility;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgelabz.bookstore.dto.BookCartDto;
import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.dto.CustomerDetailsDto;
import com.bridgelabz.bookstore.dto.WishlistDto;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Cart;
import com.bridgelabz.bookstore.model.CustomerDetails;
import com.bridgelabz.bookstore.model.Wishlist;
@Component
public class ConverterService {

    private ModelMapper modelMapper = new ModelMapper();

    public BookDTO convertToBookDto(Book book) {
        return modelMapper.map(book, BookDTO.class);
    }

    public Book convertToBookEntity(BookDTO bookDto) {
        return modelMapper.map(bookDto, Book.class);
    }

    public CartDto convertToCartDto(Cart cart) {
        return modelMapper.map(cart, CartDto.class);
    }

    public Cart convertToCartEntity(CartDto cartDto) {
        return modelMapper.map(cartDto, Cart.class);
    }
    public BookCartDto convertToBookCartDto(Cart cart) {
        return modelMapper.map(cart, BookCartDto.class);
    }
    
    public WishlistDto converToWishlistDto(Wishlist wishlist) {
        return modelMapper.map(wishlist, WishlistDto.class);
    }

    public Wishlist convertToWishlistEntity(WishlistDto wishlistDto) {
        return modelMapper.map(wishlistDto, Wishlist.class);
    }
    public CustomerDetailsDto converToCustomerDetailsDto(CustomerDetails customerDetails) {
        return modelMapper.map(customerDetails, CustomerDetailsDto.class);
    }

    public CustomerDetails converToCustomerDetailsEntity(CustomerDetailsDto customerDetailsDto) {
        return modelMapper.map(customerDetailsDto, CustomerDetails.class);
    }

}
