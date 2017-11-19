package com.sumit.igeahub.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sumit.igeahub.R;
import com.sumit.igeahub.constants.GlobalConstants;
import com.sumit.igeahub.utils.MessageUtility;
import com.sumit.igeahub.utils.SharedPreference;

import java.util.HashMap;

public class InitialOpenActivity extends ParentActivity {
    private Activity    mActivity;
    private Context mContext;
    private final int LOGIN_ACT_REQ_CODE=101;
    private final int HOME_ACT_REQ_CODE=101;
    public  final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 123;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext=getBaseContext();
        MessageUtility.showLog("isUserLoggedIn()",isUserLoggedIn()+"");
        if(isUserLoggedIn()){
            Intent intent = new Intent(mActivity, HomeActivity.class);
            setOnActivityTrasfer(intent, HOME_ACT_REQ_CODE);
        }else {
            setContentView(R.layout.activity_initial_open);
            mActivity = this;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        boolean result = PermissionActivity.checkPermission(mActivity);
                        if (result) {
                            Thread.sleep(1000);
                            Intent intent = new Intent(mActivity, HomeActivity.class);
                            setOnActivityTrasfer(intent, LOGIN_ACT_REQ_CODE);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private boolean isUserLoggedIn() {
        SharedPreference mPref=SharedPreference.getInstance();
        HashMap<String,String> map=mPref.getLoggedInUser(mContext);
        if(map.get(GlobalConstants.getInstance().USER_ID)!=null)
            return true;
        else
            return false;

    }

    @Override
    public void onTransfer(int code) {
        finish();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
        {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(mActivity, LoginActivity.class);
            setOnActivityTrasfer(intent, LOGIN_ACT_REQ_CODE);
        }
    }


}
