package com.example.denemecomment.controller;

import com.example.denemecomment.model.NewsItem;
import com.example.denemecomment.model.NewsResponse;
import com.example.denemecomment.service.ApiClient;
import com.example.denemecomment.service.NewsApiService;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FiltredController {

    private NewsApiService apiService;

    public FiltredController() {
        apiService = ApiClient.getClient().create(NewsApiService.class);
    }

    public void getFilteredNews(String targetUuid, final NewsListCallback callback) {
        apiService.getNewsList().enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    NewsResponse newsResponse = response.body();
                    List<NewsItem> allNews = new ArrayList<>();
                    allNews.addAll(newsResponse.getFeatured());
                    allNews.addAll(newsResponse.getItems());


                    List<NewsItem> filteredNews = new ArrayList<>();
                    for (NewsItem news : allNews) {
                        if (news.getUuid().equals(targetUuid)) {
                            filteredNews.add(news);

                        }
                    }


                    if (filteredNews.isEmpty()) {
                        callback.onError("No news found with the given UUID.");
                    } else {
                        callback.onSuccess(filteredNews);
                    }
                } else {
                    callback.onError("Failed to fetch news.");
                }
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }


    public interface NewsListCallback {
        void onSuccess(List<NewsItem> filteredNews);
        void onError(String error);
    }
}