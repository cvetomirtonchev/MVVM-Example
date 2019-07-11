package com.example.mvvm.networking.service;

import com.example.mvvm.model.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kostadin.georgiev on 7/10/2019.
 */
public interface NewsService {

    @GET("top-headlines")
    Call<NewsResponse> getNewsList(@Query("sources") String newsSource, @Query("apiKey") String apiKey);

}
