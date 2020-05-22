package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.modelmapper.DTOEntityMapper;
import com.bridgelabz.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.IntStream;

@Service
public class AdminBookService extends BookService implements IAdminBookService{
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private DTOEntityMapper dtoEntityMapper;

    @Override
    public void saveBookData(BufferedReader bufferedReader) {
        try {
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                Book book = new Book();
                book.setId(Long.parseLong(data[0]));
                book.setAuthor(data[1]);
                book.setTitle(data[2]);
                book.setImage(data[3]);
                book.setPrice(Integer.parseInt(data[4]));
                IntStream.range(6, data.length - 1).forEach(column -> data[5] += "," + data[column]);
                book.setDescription(data[5]);
                book.setPublicationDate(LocalDateTime.now());
                bookRepository.save(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }
}
