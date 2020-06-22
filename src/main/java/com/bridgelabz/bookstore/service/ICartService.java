package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.dto.BookCartDto;
import com.bridgelabz.bookstore.dto.CartDto;


public interface ICartService {
	 String addToCart(CartDto cartDto, String token);
	 String removeFromCart(CartDto cartDto, String token);
	 List<BookCartDto> getBooks(String token);
}
