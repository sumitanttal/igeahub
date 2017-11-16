package com.sumit.igeahub.utils;

import android.app.Activity;
import android.content.Context;

import com.sumit.igeahub.R;

/**
 * Created by sumit on 30/10/17.
 */

public class Validator {
    static  String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public static boolean isValidfields(Context mContext, String email, String password){
        if(email.isEmpty()){
          //  MessageUtility.showToast(activity,mContext.getResources().getString(R.string.error_empty_email));
            MessageUtility.showSnackBar(mContext, mContext.getResources().getString(R.string.error_empty_email));
            return false;
        }
        else if(!email.matches(emailPattern)){
            MessageUtility.showSnackBar(mContext, mContext.getResources().getString(R.string.error_invalid_email));
            return false;
        }
        else if(password.isEmpty()){
            MessageUtility.showSnackBar(mContext, mContext.getResources().getString(R.string.error_empty_password));
            return false;
        }
        else if(password.length()<6){
            MessageUtility.showSnackBar(mContext, mContext.getResources().getString(R.string.error_invalid_password));
            return false;
        }
        else{
            return true;
        }



    }

    public static boolean isValidfields(Context mContext, String username, String email, String password, String cpass) {
        if(username.isEmpty()){
            //  MessageUtility.showToast(activity,mContext.getResources().getString(R.string.error_empty_email));
            MessageUtility.showSnackBar(mContext, mContext.getResources().getString(R.string.error_empty_username));
            return false;
        }
        if(email.isEmpty()){
            //  MessageUtility.showToast(activity,mContext.getResources().getString(R.string.error_empty_email));
            MessageUtility.showSnackBar(mContext, mContext.getResources().getString(R.string.error_empty_email));
            return false;
        }
        else if(!email.matches(emailPattern)){
            MessageUtility.showSnackBar(mContext, mContext.getResources().getString(R.string.error_invalid_email));
            return false;
        }
        else if(password.isEmpty()){
            MessageUtility.showSnackBar(mContext, mContext.getResources().getString(R.string.error_empty_password));
            return false;
        }
        else if(password.length()<6){
            MessageUtility.showSnackBar(mContext, mContext.getResources().getString(R.string.error_invalid_password));
            return false;
        }
        else if(!password.equalsIgnoreCase(cpass)){
            MessageUtility.showSnackBar(mContext, mContext.getResources().getString(R.string.error_password_not_matched));
            return false;
        }
        else{
            return true;
        }
    }

    public static boolean isValidfields(Context mContext, String fname, String lname, String job_tittle, String company, String country) {

        if(fname.isEmpty()){
            //  MessageUtility.showToast(activity,mContext.getResources().getString(R.string.error_empty_email));
            MessageUtility.showSnackBar(mContext, mContext.getResources().getString(R.string.error_empty_firstname));
            return false;
        }
        if(lname.isEmpty()){
            //  MessageUtility.showToast(activity,mContext.getResources().getString(R.string.error_empty_email));
            MessageUtility.showSnackBar(mContext, mContext.getResources().getString(R.string.error_empty_lastname));
            return false;
        }
        else if(job_tittle.isEmpty()){
            MessageUtility.showSnackBar(mContext, mContext.getResources().getString(R.string.error_empty_jobtittle));
            return false;
        }
        else if(company.isEmpty()){
            MessageUtility.showSnackBar(mContext, mContext.getResources().getString(R.string.error_empty_company));
            return false;
        }
        else if(country.isEmpty()){
            MessageUtility.showSnackBar(mContext, mContext.getResources().getString(R.string.error_empty_country));
            return false;
        }
        else{
            return true;
        }


    }
}
