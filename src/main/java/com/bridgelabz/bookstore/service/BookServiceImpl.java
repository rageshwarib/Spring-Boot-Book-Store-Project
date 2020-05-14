package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class BookServiceImpl {
    @Autowired
    public BookRepository bookStoreRepository;

    String line = "";

    public void saveBookData() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/books_data.csv"));
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                Book book = new Book();
                book.setId(Long.parseLong(data[0]));
                book.setAuthor(data[1]);
                book.setTitle(data[2]);
                book.setImage(data[3]);
                book.setPrice(Integer.parseInt(data[4]));
                book.setDescription(data[5]);
                bookStoreRepository.save(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}