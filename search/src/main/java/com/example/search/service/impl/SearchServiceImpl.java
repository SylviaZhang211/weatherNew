package com.example.search.service.impl;

import com.example.search.response.GeneralResponse;
import com.example.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.scheduling.annotation.Async;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Async
    public CompletableFuture<String> callLibraryService() {
        return CompletableFuture.completedFuture(
                restTemplate.getForObject("http://library-service/library", String.class));
    }

    @Override
    @Async
    public CompletableFuture<String> callDetailsService() {
        return CompletableFuture.completedFuture(
                restTemplate.getForObject("http://details-service/details/port", String.class));
    }

    @Override
    @HystrixCommand(fallbackMethod = "fallbackSearch")
    public GeneralResponse search() {
        CompletableFuture<String> libraryFuture = callLibraryService();
        CompletableFuture<String> detailsFuture = callDetailsService();

        CompletableFuture.allOf(libraryFuture, detailsFuture).join();

        String libraryResult = libraryFuture.join();
        String detailsResult = detailsFuture.join();

        // Merge results
        Map<String, String> data = new HashMap<>();
        data.put("library", libraryResult);
        data.put("details", detailsResult);

        return new GeneralResponse("200", System.currentTimeMillis(), data);
    }

    public GeneralResponse fallbackSearch() {
        Map<String, String> data = new HashMap<>();
        data.put("message", "Service is temporarily unavailable. Please try again later.");
        return new GeneralResponse("500", System.currentTimeMillis(), data);
    }
}
