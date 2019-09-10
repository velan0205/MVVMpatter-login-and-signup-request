package com.example.disystestapplication.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PlayloadModel implements Serializable {

    @SerializedName("payload")
    private List<NewsModel> newsModels;

    public List<NewsModel> getNewsModels() {
        return newsModels;
    }

    @SerializedName("success")
    private String success;

    public String getSuccess() {
        return success;
    }
}
