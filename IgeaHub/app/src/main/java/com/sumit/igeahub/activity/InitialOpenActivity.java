package com.sumit.igeahub.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.sumit.igeahub.R;

public class InitialOpenActivity extends ParentActivity {
    private Activity    mActivity;
    private final int LOGIN_ACT_REQ_CODE=101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_open);
        mActivity=this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    Intent intent=new Intent(mActivity,LoginActivity.class);
                    setOnActivityTrasfer(intent,LOGIN_ACT_REQ_CODE);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onTransfer(int code) {

    }
}
