package com.sumit.igeahub.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.databinding.DataBindingUtil;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sumit.igeahub.R;
import com.sumit.igeahub.databinding.ActivitySignupFirstStepBinding;

public class Signup_FirstStep extends ParentActivity {
    ActivitySignupFirstStepBinding mBinding;
    private final int SIGNUP_SECOND_STEP_REQ_CODE=100;
    Activity mActivity;
    Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=this;
        mContext=getApplicationContext();
       mBinding= DataBindingUtil.setContentView(this,R.layout.activity_signup__first_step);
        init_Views();
    }

    @Override
    public void onTransfer(int code) {

    }

    private void init_Views() {

        TextInputLayout uname_layout= findViewById(R.id.vw_username).findViewById(R.id.text_input_layout);
        uname_layout.setHint(getResources().getString(R.string.prompt_username));

        TextInputLayout email_layout= findViewById(R.id.vw_email).findViewById(R.id.text_input_layout);
        email_layout.setHint(getResources().getString(R.string.prompt_email));

        TextInputLayout pass_layout= findViewById(R.id.vw_pass).findViewById(R.id.text_input_layout);
        pass_layout.setHint(getResources().getString(R.string.prompt_password));

        TextInputLayout cpass_layout= findViewById(R.id.vw_con).findViewById(R.id.text_input_layout);
        cpass_layout.setHint(getResources().getString(R.string.prompt_cpassword));

    }
    public void actionNext(View view){
        Intent intent=new Intent(mActivity,Signup_Second_step.class);
        super.setOnActivityTrasfer(intent,SIGNUP_SECOND_STEP_REQ_CODE);
    }

}
