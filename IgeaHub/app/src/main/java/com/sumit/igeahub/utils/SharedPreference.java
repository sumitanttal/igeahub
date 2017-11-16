package com.sumit.igeahub.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.sumit.igeahub.constants.GlobalConstants;

import java.util.HashMap;

/**
 * Created by sumit on 15/11/17.
 */

public class SharedPreference {

    SharedPreferences userPref;
    GlobalConstants globalConstants;

    public static SharedPreference getInstance(){

        return new SharedPreference();
    }

    public void saveUser(Context context,String firstname,String lastname,String user_image,String user_id,String email,String jobtitle
    ,String company,String country){
        globalConstants=GlobalConstants.getInstance();

        SharedPreferences pref = context.getSharedPreferences(globalConstants.USER_TABLE, 0); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();

        editor.putString(globalConstants.FIRST_NAME,firstname);
        editor.putString(globalConstants.LAST_NAME,lastname);
        editor.putString(globalConstants.USER_IMAGE,user_image);
        editor.putString(globalConstants.EMAIL,email);
        editor.putString(globalConstants.USER_ID,user_id);
        editor.putString(globalConstants.JOBTITLE,jobtitle);
        editor.putString(globalConstants.COMPANY,company);
        editor.putString(globalConstants.COUNTRY,country);
        editor.commit();


    }
    public HashMap<String, String> getLoggedInUser(Context context){
        globalConstants=GlobalConstants.getInstance();

        SharedPreferences pref = context.getSharedPreferences(globalConstants.USER_TABLE, 0); // 0 - for private mode
        HashMap<String,String> map=new HashMap<>();
        map.put(globalConstants.FIRST_NAME,pref.getString(globalConstants.FIRST_NAME,null));
        map.put(globalConstants.LAST_NAME,pref.getString(globalConstants.LAST_NAME,null));
        map.put(globalConstants.USER_IMAGE,pref.getString(globalConstants.USER_IMAGE,null));
        map.put(globalConstants.EMAIL,pref.getString(globalConstants.EMAIL,null));
        map.put(globalConstants.USER_ID,pref.getString(globalConstants.USER_ID,null));
        map.put(globalConstants.JOBTITLE,pref.getString(globalConstants.JOBTITLE,null));
        map.put(globalConstants.COMPANY,pref.getString(globalConstants.COMPANY,null));
        map.put(globalConstants.COUNTRY,pref.getString(globalConstants.COUNTRY,null));
        return map;


    }
}
