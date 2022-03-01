package com.agfiroze.searchservice.controller;

import com.agfiroze.searchservice.service.SearchService;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class SearchController {

    @Autowired
    SearchService searchService;

    @GetMapping("/api/search")
    public List<Map<String, Object>> searchDocumentsByKeywords(
            @RequestHeader(name = "tenantId") String tenantId,
            @RequestParam(name = "keyword") String keyword) {

        List<Map<String, Object>> results = new ArrayList<>();
        try {
            SearchHit[] searchHits = searchService.searchByKeyword(keyword, tenantId);

            if (searchHits != null && searchHits.length > 0) {
                for (SearchHit hit : searchHits) {
                    results.add(hit.getSourceAsMap());
                }
            }

        } catch (IOException e) {
            System.out.println("Error occurred: \n");
            e.printStackTrace();
        }

        return results;
    }
}
