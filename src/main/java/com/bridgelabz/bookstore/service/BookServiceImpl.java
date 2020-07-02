package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.modelmapper.DTOEntityMapper;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements IBookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired 
    private IElasticSearchService iElasticSearchService;
   

    @Override
    public Page<Book> getAllBooks(Pageable pageable) {
        return bookRepository.findAllBy(pageable);
    }

//    @Override
//    public List<Book> getAllBooks() {
//        return bookRepository.findAll();
//    }
    @Override
    public List<Book> searchBooks(String searchText) throws IOException {
        List<Book> searchList = new ArrayList<>();
        searchList = iElasticSearchService.searchBook(searchText);
        return searchList;
    }

    @Override
    public  Page<Book> sortBooksByPriceAsc(Pageable pageable) {
     return bookRepository.findAllByOrderByPriceAsc(pageable);
    }

    @Override
    public Page<Book> sortBooksByPriceDesc(Pageable pageable) {
        return bookRepository.findAllByOrderByPriceDesc(pageable);
      }

//    @Override
//    public Page<Book> sortBooksByNewestArrivals(Pageable pageable) {
//       return bookRepository.findAllByOrderByPublicationDateDesc(pageable);
//     }

}