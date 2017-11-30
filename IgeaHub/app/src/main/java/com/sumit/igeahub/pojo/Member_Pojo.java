package com.sumit.igeahub.pojo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sumit on 13/11/17.
 */

public class Member_Pojo {
    @SerializedName("username")
    String user_name;
    @SerializedName("userid")
    int user_id;
   @SerializedName("userimage")
    String user_image;
    @SerializedName("status")
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFriend_status() {
        return friend_status;
    }

    public void setFriend_status(int friend_status) {
        this.friend_status = friend_status;
    }

    @SerializedName("friend_status")
    int friend_status;

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






}
