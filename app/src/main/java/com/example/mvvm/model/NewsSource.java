package com.example.mvvm.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kostadin.georgiev on 7/10/2019.
 */
public class NewsSource {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
