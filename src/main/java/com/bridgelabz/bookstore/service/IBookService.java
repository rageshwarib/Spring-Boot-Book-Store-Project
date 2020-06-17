package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBookService {
    List<Book> getAllBooks();
    Page<BookDTO> searchBooks(Pageable pageable, String searchKey);
    Page<BookDTO> sortBooksByPriceAsc(Pageable pageable);
    Page<BookDTO> sortBooksByPriceDesc(Pageable pageable);
    Page<BookDTO> sortBooksByNewestArrivals(Pageable pageable);
}
