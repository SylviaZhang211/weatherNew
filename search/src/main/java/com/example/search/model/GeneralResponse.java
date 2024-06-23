package com.example.search.model;


import lombok.Data;

@Data
public class GeneralResponse {
    private String code;
    private long timestamp;
    private MergedResponse data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public MergedResponse getData() {
        return data;
    }

    public void setData(MergedResponse data) {
        this.data = data;
    }
}
