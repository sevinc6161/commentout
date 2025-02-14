// FilteredRelatedController.java
package com.example.denemecomment.controller;

import android.content.Context;

import com.example.denemecomment.model.DetailModel;
import com.example.denemecomment.model.DetailModelResponse;
import com.example.denemecomment.service.ApiClientRelated;
import com.example.denemecomment.service.NewsRelatedService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilteredRelatedController {
    private NewsRelatedService apiService;


    public FilteredRelatedController() {
        apiService = ApiClientRelated.getReleatedClient().create(NewsRelatedService.class); // ApiClient burada Retrofit client'ını temsil eder
    }

    // targetUuid ile haberleri filtreliyoruz
    public void getContentByUuid(final String uuid, final ContentCallback callback) {
        Call<DetailModelResponse> call = apiService.getReleatedList();
        call.enqueue(new Callback<DetailModelResponse>() {
            @Override
            public void onResponse(Call<DetailModelResponse> call, Response<DetailModelResponse> response) {
                if (response.isSuccessful()) {
                    DetailModel.Detail item = response.body().getDetail();
                    if (String.valueOf(item.getUuid()).equals(uuid)) {
                        callback.onSuccess(item.getContent(),item.getShare_url());
                    } else {
                        callback.onError("UUID not found");
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


    public interface ContentCallback {
        void onSuccess(String content,String shareUrl);
        void onError(String error);
    }
}

