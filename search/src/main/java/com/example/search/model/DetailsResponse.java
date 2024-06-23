package com.example.search.model;


import lombok.Data;

import java.util.List;

@Data
public class DetailsResponse {
    private List<Integer> woied;

    public DetailsResponse() {
    }

    public DetailsResponse(List<Integer> woied) {
        this.woied = woied;
    }

    //    private String detailId;
//    private String detailName;
//    private int cityId; // cityId as part of the details response
//    public DetailsResponse(){
//
//    }
//    public DetailsResponse(String detailId, String detailName, int cityId) {
//        this.detailId = detailId;
//        this.detailName = detailName;
//        this.cityId = cityId;
//    }
}