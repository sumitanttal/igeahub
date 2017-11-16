package com.sumit.igeahub.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sumit.igeahub.R;
import com.sumit.igeahub.activity.ArticleDetailActivity;
import com.sumit.igeahub.adapters.HomeAdapter;
import com.sumit.igeahub.constants.ApiConstants;
import com.sumit.igeahub.databinding.FragmentNewsBinding;
import com.sumit.igeahub.databinding.FragmentRecentBinding;
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


public class News_frag extends Fragment {

FragmentNewsBinding mBinding;
    RecyclerView recycler_view;
    View view;
    Activity mActivity;
    private Gson gson;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding= DataBindingUtil.inflate(inflater,
                R.layout.fragment_news, container, false);
        recycler_view=mBinding.recyclerView;
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

        makeArticleReq();

        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recycler_view.setLayoutManager(layoutManager);

        return view;
    }
    private void makeArticleReq() {
        // Intent intent=new Intent(mActivity,HomeActivity.class);
        //super.setOnActivityTrasfer(intent,HOME_ACT_REQ_CODE);
        /////////////////////////////////////


        HashMap<String, String> nMap=new HashMap<>();
       /* JSONObject mainObject = new JSONObject();
        try {
            mainObject.put("result", "true");
            mainObject.put("question_id", post_id);


        } catch (JSONException e) {
            MessageUtility.showLog("exception", e.toString());
        }*/


        MessageUtility.showLog("map", nMap + "");
        //getUrl(params);
        VolleyRequest.makePostRequest(ApiConstants.METHOD_POST,mActivity, nMap, ApiConstants.ACTION_NEWS, new CallBackRequestListener() {
            @Override
            public void onSuccess(String result) {
                MessageUtility.showLog("success", result);
                try {
                    JSONObject reader=new JSONObject(result);
                    int success=reader.getInt("success");
                    String  message=reader.getString("message");
                    JSONArray article=reader.getJSONArray("article");
                    if(success==1) {
                        final    List<Article_Pojo> articles = Arrays.asList(gson.fromJson(article.toString(), Article_Pojo[].class));
                        MessageUtility.showLog("list size", articles.size() + "");

                        HomeAdapter mAdapter=new HomeAdapter(getActivity(),articles);
                        recycler_view.setAdapter(mAdapter);
                        recycler_view.addOnItemTouchListener(
                                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override public void onItemClick(View view, int position) {
                                        // TODO Handle item click

                                        Intent intent=new Intent(getActivity(), ArticleDetailActivity.class);
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
