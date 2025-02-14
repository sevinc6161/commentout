package com.example.denemecomment.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientRelated {

    private static Retrofit retrofit = null;

    public static Retrofit getReleatedClient( ) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://demo6216114.mockable.io/")
                    .client(UnsafeOkHttpClient.getUnsafeOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;

    }

    public static Retrofit getReleatedTwoClient( ) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://demo6216114.mockable.io/")
                    .client(UnsafeOkHttpClient.getUnsafeOkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        return retrofit;

    }




}
