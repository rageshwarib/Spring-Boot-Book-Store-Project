package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.service.IAdminBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.*;

@RestController
@RequestMapping("/book-store/admin")
// @PreAuthorize("hasRole('ADMIN')")
public class AdminController extends CustomerBookController {
    @Autowired
    IAdminBookService adminBookService;

    @GetMapping("/save-books")
    public ResponseEntity<String> saveDataInDb() throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/books_data.csv"));
        return new ResponseEntity<>(adminBookService.saveBookData(bufferedReader), HttpStatus.OK);
    }

    @PostMapping("/add-book")
    public ResponseEntity<String> addBook(@RequestBody Book book) throws IOException {
        return new ResponseEntity<>(adminBookService.addBook(book), HttpStatus.OK);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> fileUpload(@RequestParam("file") MultipartFile multipartFile) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            return new ResponseEntity<>(adminBookService.saveBookData(bufferedReader), HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>("Books adding failed", HttpStatus.OK);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateBook(@RequestBody Book book) {
        return new ResponseEntity<>(adminBookService.updateBook(book), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable long id) throws IOException  {
        return new ResponseEntity<>(adminBookService.deleteBook(id), HttpStatus.OK);
    }
}