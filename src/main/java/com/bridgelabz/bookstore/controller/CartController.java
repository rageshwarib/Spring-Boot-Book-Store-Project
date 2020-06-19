package com.bridgelabz.bookstore.controller;


import com.bridgelabz.bookstore.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bridgelabz.bookstore.dto.BookCartDto;
import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.service.ICartService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/home/cart")
public class CartController {
    @Autowired
    private ICartService iCartService;

    @PutMapping("/add-to-cart")
    public ResponseEntity<String> addToCart(@RequestBody CartDto cartDto) {
        return new ResponseEntity<String>(iCartService.addToCart(cartDto), HttpStatus.OK);
    }

    @PutMapping("/remove-from-cart")
    public ResponseEntity<String> removeFromCart(@RequestBody CartDto cartDto) {
        return new ResponseEntity<String>(iCartService.removeFromCart(cartDto), HttpStatus.OK);
    }
    @GetMapping("/getall/{userId}")
    public List<BookCartDto> getall(@PathVariable int userId) {
        System.out.println("getting books from cart");
        return iCartService.getBooks(userId);
    }



}