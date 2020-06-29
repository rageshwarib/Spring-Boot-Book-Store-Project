package com.bridgelabz.bookstore.service;

import java.io.IOException;
import java.util.Map;

import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.model.Book;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class ElasticSearchServiceImpl implements IElasticSearchService {
	 	private String INDEX = "bookstore";
	 	private String TYPE = "book";
	 
	 	@Autowired
	    private RestHighLevelClient client;

	    @Autowired
	    private ObjectMapper objectMapper;
	    
	    @Override
	    public String createBook(Book book) throws IOException {
            String data = objectMapper.writeValueAsString(book);
            System.out.println(data);
            IndexRequest index = new IndexRequest(INDEX, TYPE);
            System.out.println(index);
            index.id(Long.toString(book.getId()));
            index.source(data, XContentType.JSON);
            IndexResponse indexResponse = client.index(index, RequestOptions.DEFAULT);
            return indexResponse.getResult().name();
    }
}
