package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.service.IAdminBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/book-store/admin")
public class AdminController extends CustomerBookController {
    @Autowired
    IAdminBookService adminBookService;

    @GetMapping("")
    public String saveDataInDb() throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/resources/books_data.csv"));
        adminBookService.saveBookData(bufferedReader);
        return "Data saved successfully";
    }

    @PostMapping("/add-book")
    public String addBook(@RequestBody Book book) {
        adminBookService.addBook(book);
        return "Book added successfully";
    }

    @PostMapping("/upload")
    public String fileUpload(@RequestParam("file") MultipartFile multipartFile) {
        try {
            InputStream inputStream = multipartFile.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            adminBookService.saveBookData(bufferedReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "File data saved sucessfully";
    }
}