package com.sumit.igeahub.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sumit on 13/11/17.
 */

public class Comment_Pojo {
    @SerializedName("user_name")
    String user_name;
    @SerializedName("user_id")
    int user_id;
   @SerializedName("user_image")
    String user_image;

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getComnt_content() {
        return comnt_content;
    }

    public void setComnt_content(String comnt_content) {
        this.comnt_content = comnt_content;
    }

    @SerializedName("comnt_content")
    String  comnt_content;



}
