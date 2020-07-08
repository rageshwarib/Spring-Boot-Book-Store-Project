package com.bridgelabz.bookstore.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.seqno.RetentionLeaseActions.AddRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class ElasticSearchServiceImpl implements IElasticSearchService {
	 	private String INDEX = "bookstore";
	 	private String TYPE = "book";
	 
	 	@Autowired
	    private RestHighLevelClient client;

	    @Autowired
	    private ObjectMapper objectMapper;
	    @Autowired
	    private BookRepository bookRepository;
	    
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

	    public List<Book> searchBook(String serachText) throws IOException {
	    	SearchRequest searchRequest = new SearchRequest();
	        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
	        QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(
	                            QueryBuilders.queryStringQuery("*"+serachText+"*")
	                            .analyzeWildcard(true)
	                            .field("title")
	                            .field("author"));
	        searchSourceBuilder.query(queryBuilder);
	        searchRequest.source(searchSourceBuilder);
	        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
	        return getSearchResult(response);
	      }
	    
	    private List<Book> getSearchResult(SearchResponse response) {
	    	SearchHit[] searchHit = response.getHits().getHits();
	    	List<Book> books = new ArrayList<>();
	    		if (searchHit.length > 0) {
	    			Arrays.stream(searchHit).forEach(hit -> books.add(objectMapper
	    					.convertValue(hit.getSourceAsMap(), Book.class))
	                        );
	            }
	            return books;
	        }
	    @Override
	    public String deleteBook(long id) throws IOException {

	        DeleteRequest deleteRequest = new DeleteRequest(INDEX, TYPE, String.valueOf(id));

	        DeleteResponse response = client.delete(deleteRequest,RequestOptions.DEFAULT);

	        return response.getResult().name();
	    }

}
