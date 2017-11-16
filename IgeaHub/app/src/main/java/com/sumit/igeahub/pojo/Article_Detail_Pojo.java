package com.sumit.igeahub.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sumit on 13/11/17.
 */

public class Article_Detail_Pojo {
    @SerializedName("title")
    String title;
    @SerializedName("id")
    int id;
    @SerializedName("image_url")
    String  image_url;

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

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getComent_count() {
        return coment_count;
    }

    public void setComent_count(String coment_count) {
        this.coment_count = coment_count;
    }

    public String getPost_create_date() {
        return post_create_date;
    }

    public void setPost_create_date(String post_create_date) {
        this.post_create_date = post_create_date;
    }

    public String getAuthor_name() {
        return author_name;
    }

    public void setAuthor_name(String author_name) {
        this.author_name = author_name;
    }

    public String getViews_count() {
        return views_count;
    }

    public void setViews_count(String views_count) {
        this.views_count = views_count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @SerializedName("coment_count")
    String  coment_count;
    @SerializedName("post_create_date")
    String  post_create_date;
    @SerializedName("author_name")
    String  author_name;
    @SerializedName("views_count")
    String  views_count;
    @SerializedName("description")
    String  description;

}
