package com.example.search.controller;

import com.example.search.model.DetailsResponse;
import com.example.search.model.GeneralResponse;
import com.example.search.service.SearchService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    public GeneralResponse search(@RequestParam String bookQuery, @RequestParam String uniQuery) throws ExecutionException, InterruptedException {
        CompletableFuture<GeneralResponse> responseFuture = searchService.search(bookQuery, uniQuery);
        System.out.println("here");
        return responseFuture.get();
    }

}

//package com.example.search.controller;
//
//import com.example.search.model.DetailsResponse;
//import com.example.search.model.GeneralResponse;
//import com.example.search.service.SearchService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.ExecutionException;
//
//@RestController
//public class SearchController {
//
//    private final SearchService searchService;
//
//    public SearchController(SearchService searchService) {
//        this.searchService = searchService;
//    }
//
//    @GetMapping("/search")
//    public GeneralResponse search(@RequestParam String bookQuery, @RequestParam String weatherQuery) throws ExecutionException, InterruptedException {
//        CompletableFuture<GeneralResponse> responseFuture = searchService.search(bookQuery, weatherQuery);
//        System.out.println("here");
//        return responseFuture.get();
//    }
//
//}
