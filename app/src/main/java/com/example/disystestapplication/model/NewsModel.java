package com.example.disystestapplication.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class NewsModel implements Serializable {
    @SerializedName("title")
    private String strTilte;
    @SerializedName("description")
    private String strDescription;
    @SerializedName("date")
    private String strDate;
    @SerializedName("image")
    private String strImage;


    public String getStrTilte() {
        return strTilte;
    }

    public void setStrTilte(String strTilte) {
        this.strTilte = strTilte;
    }

    public String getStrDescription() {
        return strDescription;
    }

    public void setStrDescription(String strDescription) {
        this.strDescription = strDescription;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public String getStrImage() {
        return strImage;
    }

    public void setStrImage(String strImage) {
        this.strImage = strImage;
    }
}
