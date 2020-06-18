package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repository.BookRepository;
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
                book.setPublicationDate(LocalDateTime.now());
                bookRepository.save(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Books added successfully";
    }

    @Override
    public String addBook(Book book) {
        bookRepository.save(book);
        return "Book added successfully";
    }

    @Override
    public String updateBook(Book book) {
        Optional<Book> book1 = Optional.ofNullable(bookRepository.findById(book.getId()));
        if (book1.isPresent()) {
            final BeanWrapper src = new BeanWrapperImpl(book);
            PropertyDescriptor[] pds = src.getPropertyDescriptors();
            Set<String> emptyNames = new HashSet<>();
            for (PropertyDescriptor pd : pds) {
                Object srcValue = src.getPropertyValue(pd.getName());
                if (srcValue == null) {
                    emptyNames.add(pd.getName());
                }
            }
            String [] result = new String[emptyNames.size()];
            String [] nullProperties = emptyNames.toArray(result);
            BeanUtils.copyProperties(src, book1.get(), nullProperties);
            bookRepository.save(book1.get());
            return "Book details updated successfully";
        }
        return "Book not found";
    }

    @Override
    public String deleteBook(long id) {
        bookRepository.deleteById(id);
        return "Book deleted successfully";
    }

}
