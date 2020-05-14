package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired
    BookServiceImpl bookService;

    @RequestMapping("book-home")
    public void saveDataInDb() {
        bookService.saveBookData();
    }
}
