package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDTO;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.modelmapper.DTOEntityMapper;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements IBookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private DTOEntityMapper dtoEntityMapper;
    @Autowired
    private UserRepository userRepository;

//    @Override
//    public Page<BookDTO> getAllBooks(Pageable pageable) {
//        Page<Book> bookEntity = bookRepository.findAllBy(pageable);
//        return dtoEntityMapper.mapBookEntityToDTO(bookEntity);
//    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Cacheable(value= "searchBooks", key= "#book")
    public Page<BookDTO> searchBooks(Pageable pageable, String searchKey) {
       List<Book> bookList = new ArrayList<>();
       for (Book book : bookRepository.findAll()) {
           if (book.getAuthor().toLowerCase().contains(searchKey.toLowerCase())
                   || book.getTitle().toLowerCase().contains(searchKey.toLowerCase())) {
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