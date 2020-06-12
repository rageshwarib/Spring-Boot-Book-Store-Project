package com.bridgelabz.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.bookstore.dto.CartDto;
import com.bridgelabz.bookstore.service.ICartService;
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

}
