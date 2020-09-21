package com.hashtag.firstbetaqa.adapter;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.hashtag.firstbetaqa.Model.NewsDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.RoundedCornersTransform;
import com.hashtag.firstbetaqa.activities.StoreDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by ebda3-mint on 9/18/17.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyHolder>{


    Context context;
    List<NewsDataModel> list;

    public NewsAdapter(Context context, List<NewsDataModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.news_row,parent,false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        holder.newsComments.setText(list.get(position).getNumberOfComments()+"");
        holder.newsViews.setText(list.get(position).getViews()+"");
        Picasso.with(context).load(list.get(position).getPhoto()).transform(new RoundedCornersTransform(10,0)).placeholder(R.drawable.placeholder_small).fit().into(holder.newsImage);
        holder.newsDate.setText(list.get(position).getAr_date().getDay()+" "+list.get(position).getAr_date().getNamemonth()+" "+list.get(position).getAr_date().getYear());
        holder.newsDetails.setText(list.get(position).getDescription());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        ImageView newsImage;
        TextView newsViews,newsComments,newsDate,newsDetails;
        LinearLayout linearLayout;

        public MyHolder(View itemView) {
            super(itemView);
            newsImage=itemView.findViewById(R.id.newsImage);
            newsViews=itemView.findViewById(R.id.newsViews);
            newsComments=itemView.findViewById(R.id.newsComments);
            newsDate=itemView.findViewById(R.id.newsDate);
            newsDetails=itemView.findViewById(R.id.newsDetails);
            linearLayout=itemView.findViewById(R.id.lin);
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, StoreDetailsActivity.class);
                    intent.putExtra("itemid",list.get(getAdapterPosition()).getNetworkid());
                    context.startActivity(intent);

                }
            });


        }
    }

}
