package com.example.search.service.impl;

import com.example.search.model.LibraryResponse;
import com.example.search.model.DetailsResponse;
import com.example.search.model.GeneralResponse;
import com.example.search.model.MergedResponse;
import com.example.search.service.SearchService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
public class SearchServiceImpl implements SearchService {

    private final RestTemplate restTemplate;

    public SearchServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "defaultBookAuthor")
    private CompletableFuture<LibraryResponse> getBook(String searchQuery) {
        return CompletableFuture.supplyAsync(() -> {
            String url = "http://library/api/books/" + searchQuery;
            return restTemplate.getForObject(url, LibraryResponse.class);
        });
    }

    @HystrixCommand(fallbackMethod = "defaultDetails")
    private CompletableFuture<DetailsResponse> getDetails(String searchQuery) {
        return CompletableFuture.supplyAsync(() -> {
            String url = "http://details/details?city=" + searchQuery;
            return restTemplate.getForObject(url, DetailsResponse.class);
        });
    }


    @Override
    public CompletableFuture<GeneralResponse> search(String bookQuery, String weatherQuery) {
//        return new CompletableFuture<>();
        CompletableFuture<LibraryResponse> bookAuthorFuture = getBook(bookQuery);
        CompletableFuture<DetailsResponse> detailsFuture = getDetails(weatherQuery);

        return bookAuthorFuture.thenCombine(detailsFuture, (bookAuthor, details) -> {
            GeneralResponse response = new GeneralResponse();
            response.setCode("200");
            response.setTimestamp(System.currentTimeMillis());
            response.setData(new MergedResponse(bookAuthor, details));
            return response;
        });
    }


    private CompletableFuture<LibraryResponse> defaultBookAuthor(String searchQuery) {
        return CompletableFuture.completedFuture(new LibraryResponse());
    }

    private CompletableFuture<DetailsResponse> defaultDetails(String searchQuery) {
        return CompletableFuture.completedFuture(new DetailsResponse());
    }
}
