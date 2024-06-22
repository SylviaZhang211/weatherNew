package com.example.search.service;

import com.example.search.response.GeneralResponse;

import java.util.concurrent.CompletableFuture;

public interface SearchService {

    CompletableFuture<String> callLibraryService();

    CompletableFuture<String> callDetailsService();

    GeneralResponse search();
}
