package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.service.BookServiceImpl;
import com.bridgelabz.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verify")
public class VerifyAccountController {

    @Autowired
     private IBookService iBookService;

    @GetMapping("/{userId}")
    public ResponseEntity<String> verifyAccount(@PathVariable long userId) {
        return new ResponseEntity(iBookService.verifyUserAccount(userId), HttpStatus.OK);
    }
}
