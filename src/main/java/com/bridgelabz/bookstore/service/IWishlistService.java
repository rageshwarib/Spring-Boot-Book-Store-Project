package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.dto.WishlistDto;
import com.bridgelabz.bookstore.model.Book;

public interface IWishlistService {

	void addToWishlist(WishlistDto wishlistDto, String token);
    void removeFromWishlist(WishlistDto wishlistDto, String token);
    List<Book> getAllBooksList(String token);
}
