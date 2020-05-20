package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface IBookService {
    void saveBookData();
    Page<Book> getAllBooks(Pageable pageable);
    Page<Book> searchBooks(Pageable pageable, String searchKey);
    Page<Book> sortBooksByPriceAsc(Pageable pageable);
    Page<Book> sortBooksByPriceDesc(Pageable pageable);
    Page<Book> sortBooksByNewestArrivals(Pageable pageable);
}
