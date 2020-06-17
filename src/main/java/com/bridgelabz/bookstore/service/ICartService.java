package com.bridgelabz.bookstore.service;

import java.util.List;


import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.model.Book;

public interface ICartService {
	 String addToCart(CartDto cartDto);
	 String removeFromCart(CartDto cartDto);
	// List<BookCartDto> getAllCartBooks(int userId);
}
