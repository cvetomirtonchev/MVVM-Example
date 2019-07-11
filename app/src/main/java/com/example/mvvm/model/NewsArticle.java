package com.example.mvvm.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kostadin.georgiev on 7/10/2019.
 */
public class NewsArticle {

    @SerializedName("source")
    private NewsSource mSource;

    @SerializedName("author")
    private String mAuthor;

    @SerializedName("title")
    private String mTitle;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("url")
    private String mUrl;

    @SerializedName("urlToImage")
    private String mImageUrl;

    @SerializedName("publishedAt")
    private String mPublishedAt;

    @SerializedName("content")
    private String mContent;

    public NewsSource getSource() {
        return mSource;
    }

    public void setSource(NewsSource source) {
        this.mSource = source;
    }

    public String getAuthor() {
        return mAuthor;
    }

    public void setAuthor(String author) {
        this.mAuthor = author;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String url) {
        this.mUrl = url;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.mImageUrl = imageUrl;
    }

    public String getPublishedAt() {
        return mPublishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.mPublishedAt = publishedAt;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String content) {
        this.mContent = content;
    }
}
