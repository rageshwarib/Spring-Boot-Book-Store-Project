package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookService {
    Page<BookDTO> getAllBooks(Pageable pageable);
    Page<BookDTO> searchBooks(Pageable pageable, String searchKey);
    Page<BookDTO> sortBooksByPriceAsc(Pageable pageable);
    Page<BookDTO> sortBooksByPriceDesc(Pageable pageable);
    Page<BookDTO> sortBooksByNewestArrivals(Pageable pageable);
    String verifyUserAccount(Long userId);
}
