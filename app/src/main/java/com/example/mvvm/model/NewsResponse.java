package com.example.mvvm.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kostadin.georgiev on 7/10/2019.
 */
public class NewsResponse {

    @SerializedName("status")
    private String mStatus;

    @SerializedName("totalResults")
    private int mTotalResults;

    @SerializedName("articles")
    private List<NewsArticle> mArticles = null;


    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        this.mStatus = status;
    }

    public int getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.mTotalResults = totalResults;
    }

    public List<NewsArticle> getArticles() {
        return mArticles;
    }

    public void setArticles(List<NewsArticle> articles) {
        this.mArticles = articles;
    }
}
