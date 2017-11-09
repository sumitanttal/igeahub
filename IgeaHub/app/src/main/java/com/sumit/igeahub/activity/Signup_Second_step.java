package com.sumit.igeahub.activity;

import android.databinding.DataBindingUtil;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sumit.igeahub.R;
import com.sumit.igeahub.databinding.ActivitySignupSecondStepBinding;

public class Signup_Second_step extends AppCompatActivity {
    ActivitySignupSecondStepBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_signup_second_step);
        init_Views();
    }


    private void init_Views() {

        TextInputLayout fname_layout= findViewById(R.id.vw_firstname).findViewById(R.id.text_input_layout);
        fname_layout.setHint(getResources().getString(R.string.prompt_firstname));

        TextInputLayout lanme_layout= findViewById(R.id.vw_lastname).findViewById(R.id.text_input_layout);
        lanme_layout.setHint(getResources().getString(R.string.prompt_lastname));

        TextInputLayout job_layout= findViewById(R.id.vw_jobtittle).findViewById(R.id.text_input_layout);
        job_layout.setHint(getResources().getString(R.string.prompt_jobtitle));

        TextInputLayout company_layout= findViewById(R.id.vw_company).findViewById(R.id.text_input_layout);
        company_layout.setHint(getResources().getString(R.string.prompt_company));

        TextInputLayout country_layout= findViewById(R.id.vw_country).findViewById(R.id.text_input_layout);
        country_layout.setHint(getResources().getString(R.string.prompt_country));

    }
}
