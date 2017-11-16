package com.sumit.igeahub.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sumit.igeahub.R;
import com.sumit.igeahub.constants.ApiConstants;
import com.sumit.igeahub.databinding.FragAboutBinding;
import com.sumit.igeahub.databinding.FragLucaDezzaniBinding;
import com.sumit.igeahub.interfaces.CallBackRequestListener;
import com.sumit.igeahub.utils.MessageUtility;
import com.sumit.igeahub.utils.VolleyRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class Luca_dezzani_Fragment extends Fragment {

FragLucaDezzaniBinding mBinding;

    View view;
    Activity mActivity;
    private Gson gson;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding= DataBindingUtil.inflate(inflater,
                R.layout.frag_luca_dezzani, container, false);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();
        view=mBinding.getRoot();
        // Inflate the layout for this fragment
        mActivity=getActivity();

        mBinding.drawerIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout drawer_layout= getActivity().findViewById(R.id.drawer_layout);
                if(drawer_layout.isDrawerOpen(Gravity.RIGHT))
                    drawer_layout.closeDrawer(Gravity.RIGHT);
                else
                    drawer_layout.openDrawer(Gravity.LEFT);

            }
        });

        makeAboutContentReq();


        return view;
    }

    private void makeAboutContentReq() {
        // Intent intent=new Intent(mActivity,HomeActivity.class);
        //super.setOnActivityTrasfer(intent,HOME_ACT_REQ_CODE);
        /////////////////////////////////////


        HashMap<String, String> nMap=new HashMap<>();

        // nMap.put("articleid", article_id);

        MessageUtility.showLog("map", nMap + "");
        //getUrl(params);
        VolleyRequest.makePostRequest(ApiConstants.METHOD_POST,mActivity, nMap, ApiConstants.ACTION_LUCADEZZANI, new CallBackRequestListener() {
            @Override
            public void onSuccess(String result) {
                MessageUtility.showLog("success", result);
                try {
                    JSONObject reader=new JSONObject(result);
                    int success=reader.getInt("success");
                    String message=reader.getString("message");
                    if(success==1) {
                            String about_content=reader.getString("Page_content");
                            mBinding.webview.getSettings().setJavaScriptEnabled(true);
                            mBinding.webview.loadData(about_content, "text/html; charset=utf-8", "UTF-8");

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
