// RelatedController.java
package com.example.denemecomment.controller;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.denemecomment.adapter.RelatedAdapter;
import com.example.denemecomment.model.DetailModel;
import com.example.denemecomment.model.DetailModelResponse;
import com.example.denemecomment.model.NewsItem;
import com.example.denemecomment.service.ApiClientRelated;
import com.example.denemecomment.service.NewsRelatedService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RelatedController {
    private NewsRelatedService newsApiService;

    public RelatedController() {
        newsApiService = ApiClientRelated.getReleatedClient().create(NewsRelatedService.class);
    }

    public void getNewsList(final NewsListCallback callback) {
        Call<DetailModelResponse> call = newsApiService.getReleatedList();
        call.enqueue(new Callback<DetailModelResponse>() {
            @Override
            public void onResponse(Call<DetailModelResponse> call, Response<DetailModelResponse> response) {
                if (response.isSuccessful()) {
                    DetailModelResponse body = response.body();

                    if (body != null) {
                        DetailModel.Detail detail = body.getDetail();
                        DetailModel.Related related = body.getRelated();

                        callback.onSuccess(detail, related);
                    } else {
                        callback.onError("Response body is null");
                    }
                } else {
                    callback.onError("Failed to load related news");
                }
            }

            @Override
            public void onFailure(Call<DetailModelResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }





    public interface NewsListCallback {
        void onSuccess(DetailModel.Detail detail, DetailModel.Related related);

        void onError(String error);
    }
}

