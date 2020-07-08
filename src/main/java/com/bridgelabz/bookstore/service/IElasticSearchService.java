package com.bridgelabz.bookstore.service;

import java.io.IOException;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;

import com.bridgelabz.bookstore.model.Book;

public interface IElasticSearchService {
	
	public String createBook(Book book) throws IOException; 
	public List<Book> searchBook(String search) throws IOException;
	public String deleteBook(long id)throws IOException;
	
}
