package com.sumit.igeahub.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.sumit.igeahub.R;
import com.sumit.igeahub.constants.ApiConstants;
import com.sumit.igeahub.databinding.ActivityLoginBinding;
import com.sumit.igeahub.interfaces.CallBackRequestListener;
import com.sumit.igeahub.utils.MessageUtility;
import com.sumit.igeahub.utils.SharedPreference;
import com.sumit.igeahub.utils.Validator;
import com.sumit.igeahub.utils.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class LoginActivity extends ParentActivity {
    Activity mActivity;
    Context mApplicationContext;
    Context mContext;
    ActivityLoginBinding mBinding;
    private final int SIGNUP_FIRST_STEP_REQ_CODE=100;
    private final int HOME_ACT_REQ_CODE=200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_login);
        /***
         * create databinding instance.
         */

        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        mActivity=this;
        mContext=getBaseContext();
        /**
         * Initialize facebook login
         */
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        init_Views();

    }

    private void init_Views() {

        TextInputLayout email_layout= findViewById(R.id.vw_email).findViewById(R.id.text_input_layout);
        email_layout.setHint(getResources().getString(R.string.prompt_email));

        TextInputLayout pas_layout= findViewById(R.id.vw_pass).findViewById(R.id.text_input_layout);
        pas_layout.setHint(getResources().getString(R.string.prompt_password));
        EditText et_password=findViewById(R.id.vw_pass).findViewById(R.id.edittext);
        et_password.setInputType(InputType.TYPE_CLASS_TEXT |
                InputType.TYPE_TEXT_VARIATION_PASSWORD);
        et_password.setSelection(et_password.getText().length());

    }

    @Override
    public void onTransfer(int code) {

    }


    public void actionLogin(View view){
        MessageUtility.showLog("login","clicked");
        EditText email_vw=mBinding.vwEmail.findViewById(R.id.edittext);
        EditText pass_vw=mBinding.vwPass.findViewById(R.id.edittext);
        String str_email=email_vw.getText().toString().trim();
        String str_pass=pass_vw.getText().toString().trim();
        if(Validator.isValidfields(mActivity,str_email,str_pass)){
            makeLoginReq(str_email,str_pass);
        }

    }
    public void createNewAcc(View view){
        Intent intent=new Intent(mActivity,Signup_FirstStep.class);
        super.setOnActivityTrasfer(intent,SIGNUP_FIRST_STEP_REQ_CODE);

    }
    private void makeLoginReq(String str_email, String str_pass) {
       // Intent intent=new Intent(mActivity,HomeActivity.class);
        //super.setOnActivityTrasfer(intent,HOME_ACT_REQ_CODE);
        /////////////////////////////////////


        HashMap<String, String> nMap=new HashMap<>();

        nMap.put(ApiConstants.UNAME, str_email);
        nMap.put(ApiConstants.UPASS, str_pass);


        MessageUtility.showLog("map", nMap + "");
        //getUrl(params);
        VolleyRequest.makePostRequest(ApiConstants.METHOD_POST,mActivity, nMap, ApiConstants.ACTION_LOGIN, new CallBackRequestListener() {
            @Override
            public void onSuccess(String result) {
                MessageUtility.showLog("success", result);
                try {
                    JSONObject  reader=new JSONObject(result);
                    int success=reader.getInt("success");
                    String message=reader.getString("message");
                    if(success==1) {
                        String userid=reader.getString("userid");
                        String firstname=reader.getString("firstname");
                        String last_name=reader.getString("last_name");
                        String emailaddress=reader.getString("emailaddress");
                        String jobtitle=reader.getString("jobtitle");
                        String company=reader.getString("company");
                        String country=reader.getString("country");

                        SharedPreference userPref=SharedPreference.getInstance();
                        userPref.saveUser(mContext,firstname,last_name,"",userid,emailaddress,jobtitle,company,country);
                        Intent intent=new Intent(LoginActivity.this,HomeActivity.class);
                        LoginActivity.super.setOnActivityTrasfer(intent,HOME_ACT_REQ_CODE);
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
}
