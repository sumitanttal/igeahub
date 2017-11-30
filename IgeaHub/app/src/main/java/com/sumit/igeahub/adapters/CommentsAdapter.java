package com.sumit.igeahub.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sumit.igeahub.R;
import com.sumit.igeahub.pojo.Article_Pojo;
import com.sumit.igeahub.pojo.Comment_Pojo;

import java.util.List;


/**
 * Created by sumit on 6/11/17.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

        Context mContext;
        Activity    mActivity;
    List<Comment_Pojo> comments;
        RecyclerView recyclerview;
        RecyclerView mRecyclerViewAllUserListing;

        public CommentsAdapter(Context applicationContext, List<Comment_Pojo> articles) {
            mContext=applicationContext;
            this.mActivity=mActivity;
            this.comments=articles;
            this.recyclerview=recyclerview;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_comment_layout, parent, false);
            //   MessageUtility.showLog("view","on create view");
            return new ViewHolder(view, mContext);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {

                holder.username.setText(comments.get(position).getUser_name());
                holder.comment.setText(comments.get(position).getComnt_content());
             Glide.with(mContext)
                    .load(comments.get(position).getUser_image()).placeholder(mContext.getResources().getDrawable(R.drawable.ic_default_user))
                    .into(holder.image);
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);

            mRecyclerViewAllUserListing = recyclerView;
        }

        @Override
        public int getItemCount() {

            return comments.size();
        }




        class ViewHolder extends RecyclerView.ViewHolder {
            private Context mContext;
            ImageView image;
            TextView    username,comment;

            ViewHolder(View itemView, final Context mContext) {
                super(itemView);
                this.mContext = mContext;
                image = (ImageView) itemView.findViewById(R.id.profile_image);
                username = (TextView) itemView.findViewById(R.id.username);
                comment = (TextView) itemView.findViewById(R.id.comment);

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
