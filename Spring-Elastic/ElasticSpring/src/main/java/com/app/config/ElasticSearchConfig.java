package com.app.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class ElasticSearchConfig {

    private static final String ELASTIC_HOST = "localhost";
    private static final int ELASTIC_PORT = 9200;
    private static final String HTTP_SCHEME = "http";

    private static final RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(new HttpHost(ELASTIC_HOST, ELASTIC_PORT, HTTP_SCHEME))
    );

    @Bean
    public RestHighLevelClient getClient() {
        return client;
    }

    public void close() {
        try {
            client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
