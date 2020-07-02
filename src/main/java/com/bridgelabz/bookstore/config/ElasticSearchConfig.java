package com.bridgelabz.bookstore.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;


@Configuration
public class ElasticSearchConfig {
	@Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public RestHighLevelClient client() {
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost("localhost", 9200, "http")));
        // client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        // client.getHttpConnectionManager().getParams().setSoTimeout(5000);
        return client;
    }
}