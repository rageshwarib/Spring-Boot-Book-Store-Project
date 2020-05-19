package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
 import java.util.List;

@RestController
@RequestMapping("/book-store")
public class BookController {
    @Autowired
    IBookService bookService;

    @GetMapping("")
    public String saveDataInDb() {
        bookService.saveBookData();
        return "Data saved";
    }

    @GetMapping("/all-books")
    public ResponseEntity<List<Book>> allBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    public ResponseEntity<List<Book>> searchBooks(@RequestBody String searchKey) {
        return new ResponseEntity<>(bookService.searchBooks(searchKey), HttpStatus.OK);
    }
}
