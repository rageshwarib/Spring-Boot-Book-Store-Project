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

    @PostMapping("/add-to-cart")
    public void addToCart(@RequestBody CartDto cartDto, @RequestHeader String token) {
         iCartService.addToCart(cartDto,token);
    }

    @PostMapping("/remove-from-cart")
    public void removeFromCart(@RequestBody CartDto cartDto, @RequestHeader String token) {
        iCartService.removeFromCart(cartDto, token);
    }
    @GetMapping("/getall")
    public List<BookCartDto> getall(@RequestHeader String token) {
        System.out.println("getting books from cart");
        return iCartService.getBooks(token);
    }
    @GetMapping("/order-placed")
    public ResponseEntity getOrderId(@RequestHeader String token) {
        return new ResponseEntity(iCartService.getOrderId(token), HttpStatus.OK);
    }


}
