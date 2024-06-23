package com.example.search.model;


import lombok.Data;

@Data
public class MergedResponse {
    private LibraryResponse bookAuthor;
    private DetailsResponse details;

    public MergedResponse(LibraryResponse bookAuthor, DetailsResponse details) {
        this.bookAuthor = bookAuthor;
        this.details = details;
    }
}