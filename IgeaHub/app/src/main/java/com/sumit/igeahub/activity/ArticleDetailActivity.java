package com.sumit.igeahub.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sumit.igeahub.R;
import com.sumit.igeahub.constants.ApiConstants;
import com.sumit.igeahub.databinding.ActivityArticleDetailBinding;
import com.sumit.igeahub.interfaces.CallBackRequestListener;
import com.sumit.igeahub.pojo.Article_Detail_Pojo;
import com.sumit.igeahub.pojo.Article_Pojo;
import com.sumit.igeahub.utils.MessageUtility;
import com.sumit.igeahub.utils.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ArticleDetailActivity extends ParentActivity {
    ActivityArticleDetailBinding mBinding;
    Activity mActivity;
    Context mApplicationContext;
    Context mContext;
    String article_id;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_article_detail);
        mActivity=this;
        mContext=getBaseContext();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();
        Bundle bundle=getIntent().getBundleExtra("bundle");
         article_id=bundle.getString("article_id");
        MessageUtility.showLog("article_id",article_id);
        makeArticleDetailReq(article_id);
    }

    @Override
    public void onTransfer(int code) {

    }

    public void actionComments(View view){
        Intent intent=new Intent(mActivity,CommentListingActivity.class);
        Bundle bundle=new Bundle();
        bundle.putString("article_id",article_id);
        intent.putExtra("bundle",bundle);
        super.startActivity(intent);
    }
    public void actionBack(View view){
        finish();
    }

    private void makeArticleDetailReq(String article_id) {
        // Intent intent=new Intent(mActivity,HomeActivity.class);
        //super.setOnActivityTrasfer(intent,HOME_ACT_REQ_CODE);
        /////////////////////////////////////


        HashMap<String, String> nMap=new HashMap<>();

       // nMap.put("articleid", article_id);

        MessageUtility.showLog("map", nMap + "");
        //getUrl(params);
        VolleyRequest.makePostRequest(ApiConstants.METHOD_POST,mActivity, nMap, ApiConstants.ACTION_ARTICLE_DETAIL+"articleid="+article_id, new CallBackRequestListener() {
            @Override
            public void onSuccess(String result) {
                MessageUtility.showLog("success", result);
                try {
                    JSONObject reader=new JSONObject(result);
                    int success=reader.getInt("success");
                    String message=reader.getString("message");
                    if(success==1) {
                        JSONArray   article=reader.getJSONArray("article");
                        final List<Article_Detail_Pojo> articles = Arrays.asList(gson.fromJson(article.toString(), Article_Detail_Pojo[].class));
                        if(articles.size()>0){
                            Glide.with(mContext)
                                    .load(articles.get(0).getImage_url())
                                    .into(mBinding.articleImage);
                            mBinding.webview.getSettings().setJavaScriptEnabled(true);
                            mBinding.webview.loadData(articles.get(0).getDescription(), "text/html; charset=utf-8", "UTF-8");
                        }
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
