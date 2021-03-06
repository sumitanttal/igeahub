package com.sumit.igeahub.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sumit.igeahub.R;
import com.sumit.igeahub.activity.ArticleDetailActivity;
import com.sumit.igeahub.activity.PrespectivesArticlesActivity;
import com.sumit.igeahub.adapters.HomeAdapter;
import com.sumit.igeahub.adapters.PrespectiveCategoriesAdapter;
import com.sumit.igeahub.constants.ApiConstants;
import com.sumit.igeahub.databinding.FragmentPerspectivesBinding;
import com.sumit.igeahub.databinding.FragmentRecentBinding;
import com.sumit.igeahub.interfaces.CallBackRequestListener;
import com.sumit.igeahub.pojo.Article_Pojo;
import com.sumit.igeahub.utils.MessageUtility;
import com.sumit.igeahub.utils.RecyclerItemClickListener;
import com.sumit.igeahub.utils.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class Perspectives_Fragment extends Fragment {

FragmentPerspectivesBinding mBinding;
    RecyclerView recycler_view;
    View view;
    Activity mActivity;
    private Gson gson;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding= DataBindingUtil.inflate(inflater,
                R.layout.fragment_perspectives, container, false);
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

        makePrespectivescatReq();

        LinearLayoutManager layoutManager=new LinearLayoutManager(mActivity);
        recycler_view.setLayoutManager(layoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mBinding.recyclerView.getContext(),
                layoutManager.getOrientation());
        recycler_view.addItemDecoration(dividerItemDecoration);

        return view;
    }
    private void makePrespectivescatReq() {
        HashMap<String, String> nMap=new HashMap<>();

        MessageUtility.showLog("map", nMap + "");
        //getUrl(params);
        VolleyRequest.makePostRequest(ApiConstants.METHOD_POST,mActivity, nMap, ApiConstants.ACTION_PRESPECTIVES_CATEGORIES, new CallBackRequestListener() {
            @Override
            public void onSuccess(String result) {
                MessageUtility.showLog("success", result);
                try {
                    JSONObject reader=new JSONObject(result);
                    int success=reader.getInt("success");
                    String  message=reader.getString("message");

                    if(success==1) {
                      final  List<String> mList = new ArrayList<String>();
                        JSONArray subcategories=reader.getJSONArray("subcategories");
                        MessageUtility.showLog("list size", mList.size() + "");
                        for(int i=0;i<subcategories.length();i++){
                            mList.add(subcategories.getJSONObject(i).getString("name"));
                        }
                        PrespectiveCategoriesAdapter mAdapter=new PrespectiveCategoriesAdapter(getActivity(),mList);
                        recycler_view.setAdapter(mAdapter);
                        recycler_view.addOnItemTouchListener(
                                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                                    @Override public void onItemClick(View view, int position) {
                                        // TODO Handle item click

                                        Intent intent=new Intent(getActivity(), PrespectivesArticlesActivity.class);
                                        Bundle bundle=new Bundle();
                                        bundle.putString("category",mList.get(position));
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
