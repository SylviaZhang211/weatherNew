package com.example.search.model;


import lombok.Data;

import java.util.List;

@Data
public class LibraryResponse {
    private Long id;
    private String title;
    private List<Long> authorIds;
    //private String type; // Book or Author
}