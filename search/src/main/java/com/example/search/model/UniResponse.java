package com.example.search.model;

import lombok.Data;

import java.util.List;

@Data
public class UniResponse {
    private String name;
    //    private String domain;
    private List<String> domains;
    private List<String> web_pages;
}
