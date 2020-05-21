package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/home")
    public ResponseEntity<Page<Book>> allBooks(Pageable pageable) {
        return new ResponseEntity<>(bookService.getAllBooks(pageable), HttpStatus.OK);
    }

    @PostMapping("/search-books")
    public ResponseEntity<Page<Book>> searchBooks(Pageable pageable, @RequestBody String searchKey) {
        return new ResponseEntity<>(bookService.searchBooks(pageable, searchKey), HttpStatus.OK);
    }

    @GetMapping("/sort-books-by-price-ascending")
    public Page<Book> sortBooksByPriceAsc(Pageable pageable) {
        return bookService.sortBooksByPriceAsc(pageable);
    }

    @GetMapping("/sort/price-descending")
    public ResponseEntity<Page<Book>> sortBooksByPriceDesc(Pageable pageable) {
        return new ResponseEntity<>(bookService.sortBooksByPriceDesc(pageable), HttpStatus.OK);
    }

    @GetMapping("/sort-books-by-newest-arrival")
    public ResponseEntity<Page<Book>> sortBooksByNewestArrivals(Pageable pageable) {
        return new ResponseEntity<>(bookService.sortBooksByNewestArrivals(pageable), HttpStatus.OK);
    }
}
