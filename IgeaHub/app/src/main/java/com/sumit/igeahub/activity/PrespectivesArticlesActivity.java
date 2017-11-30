package com.sumit.igeahub.activity;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sumit.igeahub.R;
import com.sumit.igeahub.adapters.HomeAdapter;
import com.sumit.igeahub.constants.ApiConstants;
import com.sumit.igeahub.databinding.ActivityPrespectivesArticlesBinding;
import com.sumit.igeahub.interfaces.CallBackRequestListener;
import com.sumit.igeahub.pojo.Article_Pojo;
import com.sumit.igeahub.utils.MessageUtility;
import com.sumit.igeahub.utils.RecyclerItemClickListener;
import com.sumit.igeahub.utils.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PrespectivesArticlesActivity extends ParentActivity {
    ActivityPrespectivesArticlesBinding mBinding;
    RecyclerView recycler_view;
    View view;
    Activity mActivity;
    private Gson gson;
    String cat_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prespectives_articles);
        mActivity=this;
        mBinding= DataBindingUtil.setContentView(mActivity,R.layout.activity_prespectives_articles);
        recycler_view=mBinding.recyclerView;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();
        view=mBinding.getRoot();
        Bundle bundle=getIntent().getBundleExtra("bundle");
        cat_name=bundle.getString("category");
        // Inflate the layout for this fragment


        mBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();

            }
        });

        makeArticleReq();

        GridLayoutManager layoutManager = new GridLayoutManager(mActivity, 2);
        recycler_view.setLayoutManager(layoutManager);

    }

    @Override
    public void onTransfer(int code) {

    }
    private void makeArticleReq() {
        HashMap<String, String> nMap=new HashMap<>();

        MessageUtility.showLog("map", nMap + "");
        String temp_url=ApiConstants.ACTION_PERSPECTIVES+"category="+cat_name;
        temp_url = temp_url.replaceAll(" ", "%20");
        MessageUtility.showLog("url",temp_url);
        //getUrl(params);
        VolleyRequest.makePostRequest(ApiConstants.METHOD_POST,mActivity, nMap, temp_url, new CallBackRequestListener() {
            @Override
            public void onSuccess(String result) {
                MessageUtility.showLog("success", result);
                try {
                    JSONObject reader=new JSONObject(result);
                    int success=reader.getInt("success");
                    String  message=reader.getString("message");
                    JSONArray article=reader.getJSONArray("article");
                    if(success==1) {
                        final List<Article_Pojo> articles = Arrays.asList(gson.fromJson(article.toString(), Article_Pojo[].class));
                        MessageUtility.showLog("list size", articles.size() + "");

                        HomeAdapter mAdapter=new HomeAdapter(mActivity,articles);
                        recycler_view.setAdapter(mAdapter);
                        recycler_view.addOnItemTouchListener(
                                new RecyclerItemClickListener(mActivity, new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override public void onItemClick(View view, int position) {
                                        // TODO Handle item click

                                        Intent intent=new Intent(mActivity, ArticleDetailActivity.class);
                                        Bundle bundle=new Bundle();
                                        bundle.putString("article_id",articles.get(position).getId()+"");
                                        intent.putExtra("bundle",bundle);
                                        startActivity(intent);
                                    }
                                })
                        );
                    }else{
                        MessageUtility.showSnackBar(mActivity,message);
                    }
                } catch (Exception e) {
                    MessageUtility.showLog("exception",e.toString()+"");
                }




            }

            @Override
            public void onError(String error) {
                MessageUtility.showLog("error", error);
            }
        });

    }


}
