package com.sumit.igeahub.activity;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.sumit.igeahub.R;
import com.sumit.igeahub.constants.GlobalConstants;
import com.sumit.igeahub.databinding.ActivityChatBinding;

public class ChatActivity extends AppCompatActivity {
    ActivityChatBinding mbinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mbinding= DataBindingUtil.setContentView(this,R.layout.activity_chat);
        Bundle bundle=getIntent().getBundleExtra("bundle");
        String isNewChat=bundle.getString("isNewChat");
        if(isNewChat.equals(GlobalConstants.getInstance().NEW_CHAT)){

        }else{
            String thread_id=bundle.getString("thread_id");

        }

    }
    public void actionBack(View view){
        finish();
    }
}
