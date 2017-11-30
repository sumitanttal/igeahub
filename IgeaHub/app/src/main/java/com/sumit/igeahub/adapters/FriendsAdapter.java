package com.sumit.igeahub.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sumit.igeahub.R;
import com.sumit.igeahub.activity.ChatActivity;
import com.sumit.igeahub.constants.ApiConstants;
import com.sumit.igeahub.constants.GlobalConstants;
import com.sumit.igeahub.interfaces.CallBackRequestListener;
import com.sumit.igeahub.interfaces.CallbackRefreshListener;
import com.sumit.igeahub.pojo.Member_Pojo;
import com.sumit.igeahub.utils.MessageUtility;
import com.sumit.igeahub.utils.SharedPreference;
import com.sumit.igeahub.utils.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;


/**
 * Created by sumit on 6/11/17.
 */

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {

        Context mContext;
        Activity    mActivity;
    List<Member_Pojo> members;
        RecyclerView recyclerview;
        RecyclerView mRecyclerViewAllUserListing;
    CallbackRefreshListener callBackRefreshListener;

        public FriendsAdapter(Context applicationContext, List<Member_Pojo> members, CallbackRefreshListener callBackRefreshListener) {
            mContext=applicationContext;
            this.mActivity=mActivity;
            this.members=members;
            this.recyclerview=recyclerview;
            this.callBackRefreshListener=callBackRefreshListener;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_friendlist_layout, parent, false);
            //   MessageUtility.showLog("view","on create view");
            return new ViewHolder(view, mContext);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

                holder.username.setText(members.get(position).getUser_name());
            holder.action.setText("Unfriend");
            holder.action.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String status = "10";

                    makeFriendActionReq(status,members.get(position));
                }
            });
            holder.message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(mActivity, ChatActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putString("isNewChat",GlobalConstants.getInstance().NEW_CHAT);
                    intent.putExtra("bundle",bundle);
                    mActivity.startActivity(intent);
                }
            });
             Glide.with(mContext)
                    .load(members.get(position).getUser_image()).placeholder(mContext.getResources().getDrawable(R.drawable.ic_default_user))
                    .into(holder.image);
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);

            mRecyclerViewAllUserListing = recyclerView;
        }

        @Override
        public int getItemCount() {

            return members.size();
        }




        class ViewHolder extends RecyclerView.ViewHolder {
            private Context mContext;
            ImageView image;
            TextView    username,action,message;

            ViewHolder(View itemView, final Context mContext) {
                super(itemView);
                this.mContext = mContext;
                image = (ImageView) itemView.findViewById(R.id.profile_image);
                username = (TextView) itemView.findViewById(R.id.username);
                action = (TextView) itemView.findViewById(R.id.action);
                message = (TextView) itemView.findViewById(R.id.message);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getPosition();
                    //mActivity.startActivity(new Intent(mActivity, ArticleDetailActivity.class));
                }
            });



            }
        }


    private void makeFriendActionReq(String status, Member_Pojo member_pojo) {
        // Intent intent=new Intent(mActivity,HomeActivity.class);
        //super.setOnActivityTrasfer(intent,HOME_ACT_REQ_CODE);
        /////////////////////////////////////
        SharedPreference mPref=SharedPreference.getInstance();
        HashMap<String,String> map=mPref.getLoggedInUser(mActivity);
        String userID=map.get(GlobalConstants.getInstance().USER_ID);

        HashMap<String, String> nMap=new HashMap<>();
        nMap.put("initiator_user_id",userID);
        nMap.put("friend_user_id",member_pojo.getUser_id()+"");
        nMap.put("Status",status);
       /* JSONObject mainObject = new JSONObject();
        try {
            mainObject.put("result", "true");
            mainObject.put("question_id", post_id);


        } catch (JSONException e) {
            MessageUtility.showLog("exception", e.toString());
        }*/


        MessageUtility.showLog("map", nMap + "");
        //getUrl(params);
        VolleyRequest.makePostRequest(ApiConstants.METHOD_POST,mActivity, nMap, ApiConstants.ACTION_ADD_FRIEND, new CallBackRequestListener() {
            @Override
            public void onSuccess(String result) {
                MessageUtility.showLog("success", result);
                try {
                    JSONObject reader=new JSONObject(result);
                    int success=reader.getInt("success");
                    String  message=reader.getString("message");
                    JSONArray article=reader.getJSONArray("friend_list");
                    if(success==1) {
                        callBackRefreshListener.onRefresh();
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
