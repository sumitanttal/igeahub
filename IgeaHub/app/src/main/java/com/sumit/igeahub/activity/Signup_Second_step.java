package com.sumit.igeahub.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.sumit.igeahub.R;
import com.sumit.igeahub.constants.ApiConstants;
import com.sumit.igeahub.databinding.ActivitySignupSecondStepBinding;
import com.sumit.igeahub.interfaces.CallBackRequestListener;
import com.sumit.igeahub.utils.MessageUtility;
import com.sumit.igeahub.utils.Validator;
import com.sumit.igeahub.utils.VolleyRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class Signup_Second_step extends ParentActivity {
    ActivitySignupSecondStepBinding mBinding;
    Activity mActivity;
    Context mContext;
    private final int LOGIN_ACT_REQ_CODE=200;
    private final int HOME_ACT_REQ_CODE=100;
    EditText et_fname,et_lname,et_jobtittle,et_company,et_country;
    String uname,email,pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_signup_second_step);
        mActivity=this;
        mContext=getApplicationContext();
        Bundle bundle=getIntent().getBundleExtra("bundle");
        uname=bundle.getString("username");
        email=bundle.getString("email");
        pass=bundle.getString("pass");
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

        TextInputLayout fname_layout= findViewById(R.id.vw_firstname).findViewById(R.id.text_input_layout);
        fname_layout.setHint(getResources().getString(R.string.prompt_firstname));
        et_fname=fname_layout.findViewById(R.id.edittext);
        TextInputLayout lanme_layout= findViewById(R.id.vw_lastname).findViewById(R.id.text_input_layout);
        lanme_layout.setHint(getResources().getString(R.string.prompt_lastname));
        et_lname=lanme_layout.findViewById(R.id.edittext);
        TextInputLayout job_layout= findViewById(R.id.vw_jobtittle).findViewById(R.id.text_input_layout);
        job_layout.setHint(getResources().getString(R.string.prompt_jobtitle));
        et_jobtittle=job_layout.findViewById(R.id.edittext);
        TextInputLayout company_layout= findViewById(R.id.vw_company).findViewById(R.id.text_input_layout);
        company_layout.setHint(getResources().getString(R.string.prompt_company));
        et_company=company_layout.findViewById(R.id.edittext);
        TextInputLayout country_layout= findViewById(R.id.vw_country).findViewById(R.id.text_input_layout);
        country_layout.setHint(getResources().getString(R.string.prompt_country));
        et_country=country_layout.findViewById(R.id.edittext);
    }
    public void gotologin(View view){

        Intent intent=new Intent(mActivity,LoginActivity.class);
        super.setOnActivityTrasfer(intent,LOGIN_ACT_REQ_CODE);
    }
    public void actionRegister(View view){
        String fname=et_fname.getText().toString().trim();
        String lname=et_lname.getText().toString().trim();
        String job_tittle=et_jobtittle.getText().toString().trim();
        String company=et_company.getText().toString().trim();
        String country=et_country.getText().toString().trim();
        if(Validator.isValidfields(mContext,fname,lname,job_tittle,company,country)){
            makeSignupreq(uname,email,pass,fname,lname,job_tittle,company,country);
        }
    }

    private void makeSignupreq(String uname, String email, String pass, String fname, String lname, String job_tittle, String company, String country) {

        HashMap<String, String> nMap=new HashMap<>();

        nMap.put("signup_username", uname);
        nMap.put("signup_email", email);
        nMap.put("signup_password", pass);
        nMap.put("firstname", fname);
        nMap.put("lastname", lname);
        nMap.put("jobtitle", job_tittle);
        nMap.put("company", company);
        nMap.put("country", country);


        MessageUtility.showLog("map", nMap + "");
        //getUrl(params);
        VolleyRequest.makePostRequest(ApiConstants.METHOD_POST,mActivity, nMap, ApiConstants.ACTION_REGISTER, new CallBackRequestListener() {
            @Override
            public void onSuccess(String result) {
                MessageUtility.showLog("success", result);
                try {
                    JSONObject reader=new JSONObject(result);
                    int success=reader.getInt("success");
                    String message=reader.getString("message");
                    if(success==1) {
                        Intent intent=new Intent(Signup_Second_step.this,LoginActivity.class);
                        Signup_Second_step.super.setOnActivityTrasfer(intent,LOGIN_ACT_REQ_CODE);
                        MessageUtility.showToast(mActivity,message);
                    }
                    else{
                        MessageUtility.showSnackBar(mActivity,message);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(String error) {
                MessageUtility.showLog("error", error);
            }
        });

    }

    public void actionBack(View view){
       finish();
    }

}
