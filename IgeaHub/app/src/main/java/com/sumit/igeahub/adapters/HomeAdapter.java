package com.sumit.igeahub.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.sumit.igeahub.R;
import com.sumit.igeahub.activity.ArticleDetailActivity;


/**
 * Created by sumit on 6/11/17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

        Context mContext;
        Activity    mActivity;

        RecyclerView recyclerview;
        RecyclerView mRecyclerViewAllUserListing;

        public HomeAdapter(Context applicationContext) {
            mContext=applicationContext;
            this.mActivity=mActivity;

            this.recyclerview=recyclerview;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custome_home_items_layout, parent, false);
            //   MessageUtility.showLog("view","on create view");
            return new ViewHolder(view, mContext);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {



        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);

            mRecyclerViewAllUserListing = recyclerView;
        }

        @Override
        public int getItemCount() {

            return 4;
        }




        class ViewHolder extends RecyclerView.ViewHolder {
            private Context mContext;
            ImageView image;
            TextView    text;

            ViewHolder(View itemView, final Context mContext) {
                super(itemView);
                this.mContext = mContext;
                image = (ImageView) itemView.findViewById(R.id.image);
                text = (TextView) itemView.findViewById(R.id.text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getPosition();
                    //mActivity.startActivity(new Intent(mActivity, ArticleDetailActivity.class));
                }
            });



            }
        }


    }
