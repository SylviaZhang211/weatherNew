
package com.example.search.model;


import lombok.Data;

import java.util.List;

@Data
public class MergedResponse {
    private LibraryResponse bookAuthor;
    private List<UniResponse> uni;

    public MergedResponse(LibraryResponse bookAuthor, List<UniResponse> uni) {
        this.bookAuthor = bookAuthor;
        this.uni = uni;
    }
}
//package com.example.search.model;
//
//
//import lombok.Data;
//
//@Data
//public class MergedResponse {
//    private LibraryResponse bookAuthor;
//    private DetailsResponse details;
//
//    public MergedResponse(LibraryResponse bookAuthor, DetailsResponse details) {
//        this.bookAuthor = bookAuthor;
//        this.details = details;
//    }
//}