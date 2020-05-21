package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Page<BookDTO>> allBooks(@PageableDefault(size=10) Pageable pageable) {
        return new ResponseEntity<>(bookService.getAllBooks(pageable), HttpStatus.OK);
    }

    @PostMapping("/search-books")
    public ResponseEntity<Page<BookDTO>> searchBooks(@PageableDefault(size=10) Pageable pageable
            , @RequestBody String searchKey) {
        return new ResponseEntity<>(bookService.searchBooks(pageable, searchKey), HttpStatus.OK);
    }

    @GetMapping("/sort-books-by-price-ascending")
    public Page<BookDTO> sortBooksByPriceAsc(@PageableDefault(size=10) Pageable pageable) {
        return bookService.sortBooksByPriceAsc(pageable);
    }

    @GetMapping("/sort/price-descending")
    public ResponseEntity<Page<BookDTO>> sortBooksByPriceDesc(@PageableDefault(size=10) Pageable pageable) {
        return new ResponseEntity<>(bookService.sortBooksByPriceDesc(pageable), HttpStatus.OK);
    }

    @GetMapping("/sort-books-by-newest-arrival")
    public ResponseEntity<Page<BookDTO>> sortBooksByNewestArrivals(@PageableDefault(size=10) Pageable pageable) {
        return new ResponseEntity<>(bookService.sortBooksByNewestArrivals(pageable), HttpStatus.OK);
    }
}
