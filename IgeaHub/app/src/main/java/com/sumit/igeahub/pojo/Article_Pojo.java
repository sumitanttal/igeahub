package com.sumit.igeahub.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sumit on 13/11/17.
 */

public class Article_Pojo {
    @SerializedName("title")
    String title;
    @SerializedName("Articleid")
    int id;
    @SerializedName("Articleimageurl")
    String  Articleimageurl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticleimageurl() {
        return Articleimageurl;
    }

    public void setArticleimageurl(String articleimageurl) {
        Articleimageurl = articleimageurl;
    }
}
