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
       // new DetailsResponse("1","1",1);
//        ArrayList<Integer> result = new ArrayList<>();
//        return CompletableFuture.completedFuture(new DetailsResponse(result));
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
        // Return a default or empty response
        return CompletableFuture.completedFuture(new LibraryResponse());
    }

    private CompletableFuture<DetailsResponse> defaultDetails(String searchQuery) {
        // Return a default or empty response
        return CompletableFuture.completedFuture(new DetailsResponse());
    }
}
//package com.example.search.service.impl;
//
//import com.example.search.response.GeneralResponse;
//import com.example.search.service.SearchService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import org.springframework.scheduling.annotation.Async;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.concurrent.CompletableFuture;
//
//@Service
//public class SearchServiceImpl implements SearchService {
//
//
//    private RestTemplate restTemplate;
//
//    @Autowired
//    public SearchServiceImpl(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    @HystrixCommand(fallbackMethod = "fallBackSearch")
//    private CompletableFuture<GeneralResponse> getBookOrAuthor(String searchQuery) {
//        return CompletableFuture.supplyAsync(() -> {
//            String url = "http://library-service/search?q=" + searchQuery;
//            return restTemplate.getForObject(url, GeneralResponse.class);
//        });
//    }
//
////    @Override
////    @Async
////    public CompletableFuture<String> callLibraryService() {
////        return CompletableFuture.completedFuture(
////                restTemplate.getForObject("http://library-service/library", String.class));
////    }
//
//    @Override
//    @Async
//    public CompletableFuture<String> callDetailsService() {
//        return CompletableFuture.completedFuture(
//                restTemplate.getForObject("http://details-service/details/port", String.class));
//    }
//
//    @Override
//    @HystrixCommand(fallbackMethod = "fallbackSearch")
//    public GeneralResponse search() {
//        CompletableFuture<String> libraryFuture = callLibraryService();
//        CompletableFuture<String> detailsFuture = callDetailsService();
//
//        CompletableFuture.allOf(libraryFuture, detailsFuture).join();
//
//        String libraryResult = libraryFuture.join();
//        String detailsResult = detailsFuture.join();
//
//        // Merge results
//        Map<String, String> data = new HashMap<>();
//        data.put("library", libraryResult);
//        data.put("details", detailsResult);
//
//        return new GeneralResponse("200", System.currentTimeMillis(), data);
//    }
//
//    public GeneralResponse fallbackSearch() {
//        Map<String, String> data = new HashMap<>();
//        data.put("message", "Service is temporarily unavailable. Please try again later.");
//        return new GeneralResponse("500", System.currentTimeMillis(), data);
//    }
//}
