package com.bridgelabz.bookstore.controller;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.service.IBookService;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
//@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
@RequestMapping("/book-store")
@Profile("dev")
public class CustomerBookController {
    @Autowired
    IBookService bookService;

    @GetMapping("/home")
    public ResponseEntity<Page<Book>> allBooks(@PageableDefault(size=12) Pageable pageable) {
        return new ResponseEntity<>(bookService.getAllBooks(pageable), HttpStatus.OK);
    }
    
    @GetMapping("/sort/price-ascending")
    public Page<Book> sortBooksByPriceAsc(@PageableDefault(size=12) Pageable pageable) {
        return bookService.sortBooksByPriceAsc(pageable);
    }

    @GetMapping("/sort/price-descending")
    public ResponseEntity<Page<Book>> sortBooksByPriceDesc(@PageableDefault(size=12) Pageable pageable) {
        return new ResponseEntity<>(bookService.sortBooksByPriceDesc(pageable), HttpStatus.OK);
    }

    @GetMapping("/sort/newest-arrival")
    public ResponseEntity<Page<Book>> sortBooksByNewestArrivals(@PageableDefault(size=12) Pageable pageable) {
        return new ResponseEntity<>(bookService.getAllBooks(pageable), HttpStatus.OK);
    }
    @GetMapping("/search/{searchText}")
    public ResponseEntity<Book> searchBooks(@PathVariable String searchText) throws IOException {
        return new ResponseEntity(bookService.searchBooks(searchText), HttpStatus.OK);
    }
}
