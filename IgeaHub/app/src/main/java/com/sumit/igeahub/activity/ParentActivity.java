package com.sumit.igeahub.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sumit.igeahub.interfaces.OnActivityTransfer;

/**
 * Created by sumit on 28/10/17.
 */

public abstract class ParentActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public void setOnActivityTrasfer(Intent intent,int req_code){
        startActivity(intent);
        onTransfer(req_code);

    }
    public abstract void onTransfer(int code);




}
