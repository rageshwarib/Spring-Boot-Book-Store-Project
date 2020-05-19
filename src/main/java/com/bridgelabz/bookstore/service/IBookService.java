package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBookService {
    void saveBookData();
    List<Book> getAllBooks();
    List<Book> searchBooks(String searchKey);
    List<Book> sortBooksByPriceAsc();

    Page<Book> fetchBooks(Pageable pageable);
}
