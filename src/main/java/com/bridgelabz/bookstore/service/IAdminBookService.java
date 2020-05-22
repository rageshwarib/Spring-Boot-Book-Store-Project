package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.model.Book;
import java.io.BufferedReader;

public interface IAdminBookService {
    void saveBookData(BufferedReader bufferedReader);
    void addBook(Book book);
}
