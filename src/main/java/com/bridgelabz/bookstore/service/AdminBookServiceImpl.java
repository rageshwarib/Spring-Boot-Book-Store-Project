package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repository.BookRepository;

import antlr.collections.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

@Service
public class AdminBookServiceImpl implements IAdminBookService{
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private IElasticSearchService elasticSearchService;

    @Override
    public String saveBookData(BufferedReader bufferedReader) {
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
                bookRepository.save(book);
                elasticSearchService.createBook(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Books added successfully";
    }

    @Override
    public String addBook(Book book) {
    	try {
		    bookRepository.save(book);
		    elasticSearchService.createBook(book);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    	return "Book added successfully";
    }

    @Override
    public String updateBook(long id, Book book) throws IOException {
    	Optional<Book> bookFind = Optional.ofNullable(bookRepository.findById(id));
    	if (bookFind.isPresent()) {
    		book.setId(id);
    	Book updatedBook = bookRepository.save(book);
    	elasticSearchService.updateBook(id, book);
    	return "Book updated successfully";
    	}else {
    		return "Book not found";
    	}
   }

    @Override
    public String deleteBook(long id) throws IOException {
        bookRepository.deleteById(id);
        elasticSearchService.deleteBook(id);
        return "Book deleted successfully";
    }

}
