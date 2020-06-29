//package com.bridgelabz.bookstore.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.bridgelabz.bookstore.model.Book;
//import com.bridgelabz.bookstore.repository.BookRepository;
//
//@RestController
//public class SearchController {
//	@Autowired
//    BookRepository eSRepository;
//
//    @GetMapping(value = "/title/{title}")
//    public List<Book> searchName(@PathVariable String title) {
//        return eSRepository.findByTitle(title);
//    }
//
//
//    @GetMapping(value = "/author/{author}")
//    public List<Book> searchSalary(@PathVariable String author) {
//        return eSRepository.findByAuthor(author);
//    }
//
//
//    @GetMapping(value = "/all")
//    public List<Book> searchAll() {
//        List<Book> bookList = new ArrayList<>();
//        Iterable<Book> books = eSRepository.findAll();
//        books.forEach(bookList::add);
//        return bookList;
//    }
//
//}
