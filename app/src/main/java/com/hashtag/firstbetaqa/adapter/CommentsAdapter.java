package com.hashtag.firstbetaqa.adapter;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;



import com.hashtag.firstbetaqa.Model.CommentDataModel;
import com.hashtag.firstbetaqa.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by ebda3-mint on 9/19/17.
 */

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.MyHolder>{

    Context context;
    List<CommentDataModel> list;

    public CommentsAdapter(Context context, List<CommentDataModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.comment_row,parent,false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        holder.commentDate.setText(list.get(position).getStringtime());
        holder.commentName.setText(list.get(position).getUsername());
        holder.commentDetails.setText(list.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{


        TextView commentName,commentDetails,commentDate;
        RelativeLayout relativeLayout;

        public MyHolder(View itemView) {
            super(itemView);

            commentName=itemView.findViewById(R.id.commentName);
            commentDetails=itemView.findViewById(R.id.commentDetails);
            commentDate=itemView.findViewById(R.id.commentDate);
            relativeLayout=itemView.findViewById(R.id.relative);

        }
    }

}
