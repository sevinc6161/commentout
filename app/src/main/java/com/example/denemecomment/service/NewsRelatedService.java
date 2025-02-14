package com.example.denemecomment.service;

import com.example.denemecomment.model.DetailModelResponse;
import com.example.denemecomment.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;


public interface NewsRelatedService {

    @GET("feed_detail")
    Call<DetailModelResponse> getReleatedList();

    @GET("feed_detail_two")
    Call<DetailModelResponse> getReleatedTwoList();




}