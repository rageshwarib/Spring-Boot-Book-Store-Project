package com.bridgelabz.bookstore.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

<<<<<<< HEAD
//@Configuration
//public class ElasticSearchConfig {
//	@Value("${elasticsearch.host}")
//    private String elasticsearchHost;
//
//    @Bean(destroyMethod = "close")
//    public RestHighLevelClient client() {
//
//        RestHighLevelClient client = new RestHighLevelClient(
//                RestClient.builder(new HttpHost(elasticsearchHost)));
//
//        return client;
//
//    }

// }
=======
@Configuration
public class ElasticSearchConfig {
	@Value("${elasticsearch.host}")
    private String elasticsearchHost;

    @Bean(destroyMethod = "close")
    public RestHighLevelClient client() {

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(new HttpHost(elasticsearchHost)));

        return client;

    }

}
>>>>>>> 8bffe02e19b00124c650bcdd0c93dd50963771f2
