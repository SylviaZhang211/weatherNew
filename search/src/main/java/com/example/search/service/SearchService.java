


package com.example.search.service;

import com.example.search.model.DetailsResponse;
import com.example.search.model.GeneralResponse;

import javax.xml.soap.Detail;
import java.util.concurrent.CompletableFuture;

public interface SearchService {
//    CompletableFuture<GeneralResponse> search(String query);

    CompletableFuture<GeneralResponse> search(String bookQuery, String uniQuery);

}

//package com.example.search.service;
//
//import com.example.search.model.DetailsResponse;
//import com.example.search.model.GeneralResponse;
//
//import javax.xml.soap.Detail;
//import java.util.concurrent.CompletableFuture;
//
//public interface SearchService {
////    CompletableFuture<GeneralResponse> search(String query);
//
//    CompletableFuture<GeneralResponse> search(String bookQuery, String weatherQuery);
//
//}
////package com.example.search.service;
////
////import com.example.search.response.GeneralResponse;
////
////import java.util.concurrent.CompletableFuture;
////
////public interface SearchService {
////
////    CompletableFuture<String> callLibraryService();
////
////    CompletableFuture<String> callDetailsService();
////
////    GeneralResponse search();
////}
