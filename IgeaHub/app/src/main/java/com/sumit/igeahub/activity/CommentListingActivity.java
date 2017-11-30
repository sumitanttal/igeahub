package com.sumit.igeahub.activity;

import android.app.Activity;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sumit.igeahub.R;
import com.sumit.igeahub.adapters.CommentsAdapter;
import com.sumit.igeahub.constants.ApiConstants;
import com.sumit.igeahub.constants.GlobalConstants;
import com.sumit.igeahub.databinding.ActivityCommentListingBinding;
import com.sumit.igeahub.interfaces.CallBackRequestListener;
import com.sumit.igeahub.pojo.Comment_Pojo;
import com.sumit.igeahub.utils.MessageUtility;
import com.sumit.igeahub.utils.SharedPreference;
import com.sumit.igeahub.utils.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CommentListingActivity extends ParentActivity {
    ActivityCommentListingBinding mBinding;
    Activity mActivity;
    Context mContext;
    private Gson gson;
    String article_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding= DataBindingUtil.setContentView(this,R.layout.activity_comment_listing);
        mActivity=this;
        mContext=getBaseContext();
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();
        Bundle bundle=getIntent().getBundleExtra("bundle");
        article_id=bundle.getString("article_id");
        makeCommentListReq();

    }

    public void actionBack(View view){
        finish();
    }

    private void makeCommentListReq() {


        HashMap<String, String> nMap=new HashMap<>();

        // nMap.put("articleid", article_id);

        MessageUtility.showLog("map", nMap + "");
        //getUrl(params);
        VolleyRequest.makePostRequest(ApiConstants.METHOD_POST,mActivity, nMap, ApiConstants.ACTION_GETCOMMENTS+"articleid="+article_id, new CallBackRequestListener() {
            @Override
            public void onSuccess(String result) {
                MessageUtility.showLog("success", result);
                try {
                    JSONObject reader=new JSONObject(result);
                    int success=reader.getInt("success");
                    String message=reader.getString("message");
                    if(success==1) {
                        JSONArray comments=reader.getJSONArray("comments");
                        final List<Comment_Pojo> comments_list = Arrays.asList(gson.fromJson(comments.toString(), Comment_Pojo[].class));
                        final List<Comment_Pojo> mList = new ArrayList<Comment_Pojo>();
                        for(int i=comments_list.size()-1;i>=0;i--){
                            mList.add(comments_list.get(i));
                        }

                        LinearLayoutManager layoutManager=new LinearLayoutManager(mActivity);
                        layoutManager.setReverseLayout(true);
                        mBinding.recyclerView.setLayoutManager(layoutManager);
                        CommentsAdapter mAdapter=new CommentsAdapter(mContext,mList);
                        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mBinding.recyclerView.getContext(),
                                layoutManager.getOrientation());
                        mBinding.recyclerView.addItemDecoration(dividerItemDecoration);
                        mBinding.recyclerView.setAdapter(mAdapter);

                        mBinding.recyclerView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                   /* Intent intent=new Intent(mActivity,CommentListingActivity.class);
                                    Bundle bundle=new Bundle();
                                    bundle.putString("article_id",article_id);
                                    intent.putExtra("bundle",bundle);
                                    ArticleDetailActivity.super.setOnActivityTrasfer(intent,COMMENTS_LISTING_ACT_REQ_CODE);*/
                                }
                            });
                        mBinding.ivPostcomment.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String str_comment=mBinding.etComment.getText().toString().trim();
                                if(!str_comment.isEmpty()){
                                    SharedPreference mPref=SharedPreference.getInstance();
                                    HashMap<String,String> map=mPref.getLoggedInUser(mContext);
                                    String userid=map.get(GlobalConstants.getInstance().USER_ID);
                                    String user_name=map.get(GlobalConstants.getInstance().FIRST_NAME)+map.get(GlobalConstants.getInstance().LAST_NAME);

                                    makePostCommentReq(str_comment,userid,user_name);
                                }
                            }
                        });

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

    private void makePostCommentReq(String str_comment,String userid,String user_name) {
        HashMap<String, String> nMap=new HashMap<>();
         nMap.put("articleid", article_id);
         nMap.put("comment", str_comment);
         nMap.put("userid", userid);
         nMap.put("user_name", user_name);

        MessageUtility.showLog("map", nMap + "");
        //getUrl(params);
        VolleyRequest.makePostRequest(ApiConstants.METHOD_POST,mActivity, nMap, ApiConstants.ACTION_POSTCOMMENT, new CallBackRequestListener() {
            @Override
            public void onSuccess(String result) {
                MessageUtility.showLog("success", result);
                try {
                    JSONObject reader=new JSONObject(result);
                    int success=reader.getInt("success");
                    String message=reader.getString("message");
                    if(success==1) {
                        mBinding.etComment.setText("");
                        makeCommentListReq();
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

    @Override
    public void onTransfer(int code) {

    }
}
