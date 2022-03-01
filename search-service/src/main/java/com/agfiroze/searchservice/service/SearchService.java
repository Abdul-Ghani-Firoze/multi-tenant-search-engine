package com.agfiroze.searchservice.service;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SearchService {

    private static final String FIELD_DOCTYPE = "doctype";
    private static final String FIELD_TIMESTAMP = "@timestamp";
    private static final String FIELD_VERSION = "@version";

    @Qualifier("createInstance")
    @Autowired
    RestHighLevelClient client;

    public SearchHit[] searchByKeyword(String keyword, String tenant) throws IOException {
        String indicesRegex = tenant + "_*";
        String[] excludes = new String[]{FIELD_VERSION, FIELD_TIMESTAMP, FIELD_DOCTYPE};
        SearchRequest searchRequest = new SearchRequest(indicesRegex);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.wildcardQuery("doc_name", keyword + "*"));
        searchSourceBuilder.fetchSource(null, excludes);

        // defining a limit of 10 search hits. So 10 of most matching results
        // will be returned for the given keyword
        searchSourceBuilder.size(10);
        searchRequest.source(searchSourceBuilder);

        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        SearchHits hits = response.getHits();

        SearchHit[] searchHits = hits != null ? hits.getHits() : null;
        return searchHits;
    }
}
