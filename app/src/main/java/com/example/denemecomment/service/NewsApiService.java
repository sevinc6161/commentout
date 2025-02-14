package com.example.denemecomment.service;


import com.example.denemecomment.model.DetailModelResponse;
import com.example.denemecomment.model.FiltredNewResponse;
import com.example.denemecomment.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsApiService {


    @GET("feed")
    Call<NewsResponse> getNewsList();







}
