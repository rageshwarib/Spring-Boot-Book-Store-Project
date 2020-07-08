package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.model.Book;
import java.io.BufferedReader;
import java.io.IOException;

public interface IAdminBookService {
    String saveBookData(BufferedReader bufferedReader);
    String addBook(Book book) throws IOException;
    String deleteBook(long id) throws IOException ;
    String updateBook(Book book);
}
