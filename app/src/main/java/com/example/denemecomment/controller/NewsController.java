package com.example.denemecomment.controller;

import android.content.Context;
import android.content.Intent;

import com.example.denemecomment.DetailNews;
import com.example.denemecomment.model.NewsItem;
import com.example.denemecomment.model.NewsResponse;
import com.example.denemecomment.service.ApiClient;
import com.example.denemecomment.service.NewsApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class NewsController {
    private NewsApiService newsApiService;
    private Context context;


    public NewsController() {
        newsApiService = ApiClient.getClient().create(NewsApiService.class);
    }



    public void getNewsList(final NewsListCallback callback) {
        Call<NewsResponse> call = newsApiService.getNewsList();
        call.enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful()) {

                    callback.onSuccess(response.body().getFeatured(), response.body().getItems());
                } else {
                    callback.onError("Failed to load news list");
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public interface NewsListCallback {
        void onSuccess(List<NewsItem> featured, List<NewsItem> items);
        void onError(String error);
    }
}
