package com.sumit.igeahub.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sumit.igeahub.R;
import com.sumit.igeahub.pojo.Comment_Pojo;

import java.util.List;


/**
 * Created by sumit on 6/11/17.
 */

public class PrespectiveCategoriesAdapter extends RecyclerView.Adapter<PrespectiveCategoriesAdapter.ViewHolder> {

        Context mContext;
        Activity    mActivity;
        List<String> categories;
        RecyclerView recyclerview;
        RecyclerView mRecyclerViewAllUserListing;

        public PrespectiveCategoriesAdapter(Context applicationContext, List<String> categories) {
            mContext=applicationContext;
            this.mActivity=mActivity;
            this.categories=categories;
            this.recyclerview=recyclerview;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_prespectives_cat_layout, parent, false);
            //   MessageUtility.showLog("view","on create view");
            return new ViewHolder(view, mContext);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

                holder.cate_name.setText(categories.get(position));

            /* Glide.with(mContext)
                    .load(comments.get(position).get)
                    .into(holder.image);*/

        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);

            mRecyclerViewAllUserListing = recyclerView;
        }

        @Override
        public int getItemCount() {

            return categories.size();
        }




        class ViewHolder extends RecyclerView.ViewHolder {
            private Context mContext;

            TextView    cate_name;

            ViewHolder(View itemView, final Context mContext) {
                super(itemView);
                this.mContext = mContext;

                cate_name = (TextView) itemView.findViewById(R.id.cate_name);


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
