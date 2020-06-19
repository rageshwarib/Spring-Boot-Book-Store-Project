package com.bridgelabz.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.WishlistDto;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.service.IWishlistService;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/home/wishlist")
public class WishlistController {
	 @Autowired
	    private IWishlistService iWishlistService;

	    @PostMapping("/add-to-wishlist")
	    public ResponseEntity<String> addToWishlist(@RequestBody WishlistDto wishlistDto) {
	        return new ResponseEntity<String>(iWishlistService.addToWishlist(wishlistDto), HttpStatus.OK);
	    }

	    @PostMapping("/remove-from-wishlist")
	    public ResponseEntity<String> removeFromWishlist(@RequestBody WishlistDto wishlistDto) {
	        return new ResponseEntity<String>(iWishlistService.removeFromWishlist(wishlistDto), HttpStatus.OK);
	    }

	    @GetMapping("/getall/{userId}")
	    public ResponseEntity<List<Book>> getAllBooksList(@PathVariable int userId) {
	        return new ResponseEntity<List<Book>>(iWishlistService.getAllBooksList(userId), HttpStatus.OK);
	    }

}
