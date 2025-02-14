package com.example.denemecomment.model;


import java.util.List;

// Main response model
public class NewsResponse {
    private List<NewsItem> featured;
    private List<NewsItem> items;

    public List<NewsItem> getFeatured() {
        return featured;
    }


    public List<NewsItem> getItems() {
        return items;
    }

}
