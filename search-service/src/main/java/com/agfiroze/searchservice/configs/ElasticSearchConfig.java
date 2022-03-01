package com.agfiroze.searchservice.configs;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {
    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchConfig.class);
    private RestHighLevelClient restHighLevelClient;

    @Value("${elasticsearch.server.host}")
    private String host;

    @Value("${elasticsearch.server.port}")
    private int port;

    @Value("${elasticsearch.server.scheme}")
    private String scheme;

    @Bean
    public RestHighLevelClient createInstance() {
        return buildClient();
    }

    private RestHighLevelClient buildClient() {
        try {
            restHighLevelClient =
                    new RestHighLevelClient(RestClient.builder(new HttpHost(host, port, scheme)));
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw e;
        }
        return restHighLevelClient;
    }
}
