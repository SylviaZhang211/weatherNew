package com.example.search.response;

import java.util.Map;

public class GeneralResponse {
    private String code;
    private long timestamp;
    private Map<String, String> data;

    // Constructor, getters, and setters

    public GeneralResponse(String code, long timestamp, Map<String, String> data) {
        this.code = code;
        this.timestamp = timestamp;
        this.data = data;
    }
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

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}
