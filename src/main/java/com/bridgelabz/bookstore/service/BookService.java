package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.modelmapper.DTOEntityMapper;
import com.bridgelabz.bookstore.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class BookService implements IBookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private DTOEntityMapper dtoEntityMapper;
    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public void saveBookData() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/books_data.csv"));
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

    @Override
    public Page<BookDTO> getAllBooks(Pageable pageable) {
        Page<Book> bookEntity = bookRepository.findAllBy(pageable);
        return dtoEntityMapper.mapBookEntityToDTO(bookEntity);
    }

    @Override
    public Page<BookDTO> searchBooks(Pageable pageable, String searchKey) {
       List<Book> bookList = new ArrayList<>();
       for (Book book : bookRepository.findAll()) {
           if (book.getAuthor().equalsIgnoreCase(searchKey) || book.getTitle().equalsIgnoreCase(searchKey)) {
               bookList.add(book);
           }
       }
       Page<Book> bookEntity = new PageImpl<>(bookList, pageable, bookList.size());
       return dtoEntityMapper.mapBookEntityToDTO(bookEntity);
    }

    @Override
    public Page<BookDTO> sortBooksByPriceAsc(Pageable pageable) {
        Page<Book> bookEntity = bookRepository.findAllByOrderByPriceAsc(pageable);
        return dtoEntityMapper.mapBookEntityToDTO(bookEntity);
    }

    @Override
    public Page<BookDTO> sortBooksByPriceDesc(Pageable pageable) {
        Page<Book> bookEntity = bookRepository.findAllByOrderByPriceDesc(pageable);
        return dtoEntityMapper.mapBookEntityToDTO(bookEntity);
    }

    @Override
    public Page<BookDTO> sortBooksByNewestArrivals(Pageable pageable) {
        Page<Book> bookEntity = bookRepository.findAllByOrderByPublicationDateDesc(pageable);
        return dtoEntityMapper.mapBookEntityToDTO(bookEntity);
    }
}