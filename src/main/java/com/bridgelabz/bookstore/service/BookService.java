package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class BookService implements IBookService {
    @Autowired
    public BookRepository bookRepository;

    @Override
    public void saveBookData() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/books_data.csv"));
            bufferedReader.readLine();
            String line = "";
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

    @Override
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAllBy(pageable);
    }

    @Override
    public Page<Book> searchBooks(Pageable pageable, String searchKey) {
       List<Book> bookList = new ArrayList<>();
       for (Book book : bookRepository.findAll()) {
           if (book.getAuthor().equalsIgnoreCase(searchKey) || book.getTitle().equalsIgnoreCase(searchKey)) {
               bookList.add(book);
           }
       }
       return new PageImpl<>(bookList, pageable, bookList.size());
    }

    @Override
    public Page<Book> sortBooksByPriceAsc(Pageable pageable) {
        return bookRepository.findAllByOrderByPriceAsc(pageable);
    }

    @Override
    public Page<Book> sortBooksByPriceDesc(Pageable pageable) {
        return bookRepository.findAllByOrderByPriceDesc(pageable);
    }

    @Override
    public Page<Book> sortBooksByNewestArrivals(Pageable pageable) {
        return bookRepository.findAllByOrderByPublicationDateDesc(pageable);
    }
}