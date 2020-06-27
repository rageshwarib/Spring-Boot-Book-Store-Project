package com.bridgelabz.bookstore.service;

import java.util.List;

import com.bridgelabz.bookstore.dto.WishlistDto;
import com.bridgelabz.bookstore.model.Book;

public interface IWishlistService {

    String addToWishlist(WishlistDto wishlistDto, String token);
    String removeFromWishlist(WishlistDto wishlistDto, String token);
    List<Book> getAllBooksList(String token);
}
