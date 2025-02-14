package com.example.denemecomment.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://demo6216114.mockable.io/")
                    .client(UnsafeOkHttpClient.getUnsafeOkHttpClient()) // Unsafe client
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;

    }


}

