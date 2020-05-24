package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.model.Book;
import java.io.BufferedReader;

public interface IAdminBookService {
    String saveBookData(BufferedReader bufferedReader);
    String addBook(Book book);
}
