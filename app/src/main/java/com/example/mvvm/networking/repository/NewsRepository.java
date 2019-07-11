package com.example.mvvm.networking.repository;

import com.example.mvvm.model.NewsResponse;
import com.example.mvvm.networking.BaseApiResponse;
import com.example.mvvm.networking.ResponseCallback;
import com.example.mvvm.networking.RetrofitService;
import com.example.mvvm.networking.service.NewsService;

import androidx.lifecycle.MutableLiveData;

/**
 * Created by kostadin.georgiev on 7/10/2019.
 */
public class NewsRepository {
    private static final String NEWS_SOURCE = "google-news";
    private static final String API_KEY = "bc7aa0189dc94427898e429a97e14b0c";
    private final NewsService mNewsService;
    private static NewsRepository newsRepository;

    private NewsRepository() {
        mNewsService = RetrofitService.createService(NewsService.class);
    }

    public static NewsRepository getInstance() {
        if (newsRepository == null) {
            newsRepository = new NewsRepository();
        }
        return newsRepository;
    }

    @SuppressWarnings("unchecked")
    public MutableLiveData<BaseApiResponse<NewsResponse>> getNews() {
        MutableLiveData<BaseApiResponse<NewsResponse>> newsData = new MutableLiveData<>();

        mNewsService.getNewsList(NEWS_SOURCE, API_KEY).enqueue(new ResponseCallback<NewsResponse>(newsData));
        return newsData;
    }
}
