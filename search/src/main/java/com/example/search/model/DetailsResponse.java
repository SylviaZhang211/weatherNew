package com.example.search.model;


import lombok.Data;

import java.util.List;

@Data
public class DetailsResponse {
    private List<Integer> woeid;

    public DetailsResponse() {
    }

    public DetailsResponse(List<Integer> woeid) {
        this.woeid = woeid;
    }

}