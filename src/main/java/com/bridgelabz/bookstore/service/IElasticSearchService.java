package com.bridgelabz.bookstore.service;

import java.io.IOException;

import com.bridgelabz.bookstore.model.Book;

public interface IElasticSearchService {
	
	public String createBook(Book book) throws IOException; 

}
