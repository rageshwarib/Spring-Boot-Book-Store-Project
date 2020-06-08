package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
//@PreAuthorize("hasRole('USER')")
@RequestMapping("/book-store")
public class CustomerBookController {
    @Autowired
    IBookService bookService;

    @GetMapping("/home")
    public ResponseEntity allBooks() {
        return new ResponseEntity(bookService.getAllBooks(), HttpStatus.OK);
    }

    @PostMapping("/search-books")
    public ResponseEntity<Page<BookDTO>> searchBooks(@PageableDefault(size=10) Pageable pageable
            , @RequestBody String searchKey) {
        return new ResponseEntity<>(bookService.searchBooks(pageable, searchKey), HttpStatus.OK);
    }

    @GetMapping("/sort/price-ascending")
    public Page<BookDTO> sortBooksByPriceAsc(@PageableDefault(size=10) Pageable pageable) {
        return bookService.sortBooksByPriceAsc(pageable);
    }

    @GetMapping("/sort/price-descending")
    public ResponseEntity<Page<BookDTO>> sortBooksByPriceDesc(@PageableDefault(size=10) Pageable pageable) {
        return new ResponseEntity<>(bookService.sortBooksByPriceDesc(pageable), HttpStatus.OK);
    }

    @GetMapping("/sort/newest-arrival")
    public ResponseEntity<Page<BookDTO>> sortBooksByNewestArrivals(@PageableDefault(size=10) Pageable pageable) {
        return new ResponseEntity<>(bookService.sortBooksByNewestArrivals(pageable), HttpStatus.OK);
    }
}
