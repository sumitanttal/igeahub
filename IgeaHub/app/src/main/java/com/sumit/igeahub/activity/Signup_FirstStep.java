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
import android.widget.EditText;

import com.sumit.igeahub.R;
import com.sumit.igeahub.databinding.ActivitySignupFirstStepBinding;
import com.sumit.igeahub.utils.Validator;

public class Signup_FirstStep extends ParentActivity {
    ActivitySignupFirstStepBinding mBinding;
    private final int SIGNUP_SECOND_STEP_REQ_CODE=100;
    private final int LOGIN_ACT_REQ_CODE=200;
    Activity mActivity;
    Context mContext;
    EditText   et_uname,et_email,et_pass,et_cpass;

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
        switch (code){
            case LOGIN_ACT_REQ_CODE:
                finish();
                break;
        }
    }

    private void init_Views() {

        TextInputLayout uname_layout= findViewById(R.id.vw_username).findViewById(R.id.text_input_layout);
        uname_layout.setHint(getResources().getString(R.string.prompt_username));
        et_uname=uname_layout.findViewById(R.id.edittext);
        TextInputLayout email_layout= findViewById(R.id.vw_email).findViewById(R.id.text_input_layout);
        email_layout.setHint(getResources().getString(R.string.prompt_email));
        et_email=email_layout.findViewById(R.id.edittext);
        TextInputLayout pass_layout= findViewById(R.id.vw_pass).findViewById(R.id.text_input_layout);
        pass_layout.setHint(getResources().getString(R.string.prompt_password));
        et_pass=pass_layout.findViewById(R.id.edittext);
        TextInputLayout cpass_layout= findViewById(R.id.vw_con).findViewById(R.id.text_input_layout);
        cpass_layout.setHint(getResources().getString(R.string.prompt_cpassword));
        et_cpass=cpass_layout.findViewById(R.id.edittext);
    }
    public void actionNext(View view){
        String username=et_uname.getText().toString().trim();
        String email=et_email.getText().toString().trim();
        String pass=et_pass.getText().toString().trim();
        String cpass=et_cpass.getText().toString().trim();
        if(Validator.isValidfields(mContext,username,email,pass,cpass)){
            Intent intent=new Intent(mActivity,Signup_Second_step.class);
            Bundle bundle=new Bundle();
            bundle.putString("username",username);
            bundle.putString("email",email);
            bundle.putString("pass",pass);
            intent.putExtra("bundle",bundle);
            super.setOnActivityTrasfer(intent,SIGNUP_SECOND_STEP_REQ_CODE);
        }

    }
    public void gotologin(View view){
        Intent intent=new Intent(mActivity,LoginActivity.class);
        super.setOnActivityTrasfer(intent,LOGIN_ACT_REQ_CODE);
    }

}
