package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.model.Book;

import java.util.Comparator;
import java.util.List;

public interface IBookService {
    void saveBookData();
    List<Book> getAllBooks();
    List<Book> searchBooks(String searchKey);
}
