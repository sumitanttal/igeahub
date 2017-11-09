package com.sumit.igeahub.utils;

import android.content.Context;

import com.sumit.igeahub.R;

/**
 * Created by sumit on 30/10/17.
 */

public class Validator {
    static  String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    public static boolean isValidfields(Context mContext, String email, String password){
        if(email.isEmpty()){
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
}
