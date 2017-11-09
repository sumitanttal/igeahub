package com.sumit.igeahub.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sumit.igeahub.R;
import com.sumit.igeahub.activity.ArticleDetailActivity;
import com.sumit.igeahub.adapters.HomeAdapter;
import com.sumit.igeahub.databinding.FragHomeBinding;
import com.sumit.igeahub.utils.RecyclerItemClickListener;

/**
 * Created by sumit on 1/11/17.
 */

public class Home_Fragment extends Fragment {
    FragHomeBinding mBinding;
    RecyclerView    recycler_view;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mBinding= DataBindingUtil.inflate(inflater,
                R.layout.frag_home, container, false);
        recycler_view=mBinding.recyclerView;
        view=mBinding.getRoot();
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

        HomeAdapter mAdapter=new HomeAdapter(getActivity());
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recycler_view.setLayoutManager(layoutManager);
        recycler_view.setAdapter(mAdapter);
        recycler_view.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // TODO Handle item click

                        startActivity(new Intent(getActivity(), ArticleDetailActivity.class));
                    }
                })
        );
            return view;
    }
}
