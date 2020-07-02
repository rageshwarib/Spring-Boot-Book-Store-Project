package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface IBookService {
   // List<Book> getAllBooks();
    Page<Book> getAllBooks(Pageable pageable);
    List<Book> searchBooks(String searchKey) throws IOException;
    //Page<BookDTO> searchBooks(String searchKey, Pageable pageable);
    
    Page<Book> sortBooksByPriceAsc(Pageable pageable);
    Page<Book> sortBooksByPriceDesc(Pageable pageable);
  //  Page<Book> sortBooksByNewestArrivals(Pageable pageable);
}
