package com.hashtag.firstbetaqa.adapter;

import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;


import com.hashtag.firstbetaqa.Model.MainCatsDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.RoundedCornersTransform;
import com.hashtag.firstbetaqa.activities.ContactUsActivity;
import com.hashtag.firstbetaqa.activities.NewsActivity;
import com.hashtag.firstbetaqa.activities.OffersActivity;
import com.hashtag.firstbetaqa.activities.PageActivity;
import com.hashtag.firstbetaqa.activities.PartnersActivity;
import com.hashtag.firstbetaqa.activities.RequestCardActivity;
import com.hashtag.firstbetaqa.activities.RequestJoinActivity;
import com.hashtag.firstbetaqa.activities.SubCatCountActivity;
import com.hashtag.firstbetaqa.activities.SubCatsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by ebda3-mint on 9/17/17.
 */

public class MainCatsAdapter extends RecyclerView.Adapter<MainCatsAdapter.MyHolder>{

    Context context;
    List<MainCatsDataModel> list;

    public MainCatsAdapter(Context context, List<MainCatsDataModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == 1){
            return new MyHolder(LayoutInflater.from(context).
                    inflate(R.layout.main_cat_row_full,parent,false));

        }else{
            return new MyHolder(LayoutInflater.from(context).
                    inflate(R.layout.main_cat_row,parent,false));

        }
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        Picasso.with(context).load(list.get(position).getPhoto())
                .transform(new RoundedCornersTransform(5,0))
                .placeholder(R.drawable.placeholder_small).into(holder.catImage);
        holder.catName.setText(list.get(position).getTitle());
        Log.v("sadsad",list.get(position).getTitle());

    }

    @Override
    public int getItemViewType(int position) {

        if(list.get(position).getType().equals("cats") || list.get(position).getType().equals("partners")){
            return  1;
        }else{
            return 2;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        ImageView catImage;

        TextView catName;
        FrameLayout frameLayout;

        public MyHolder(View itemView) {
            super(itemView);

            catImage=itemView.findViewById(R.id.catImage);
            catName=itemView.findViewById(R.id.catName);
            frameLayout=itemView.findViewById(R.id.frame);
            frameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(list.get(getAdapterPosition()).getType().equals("cats")) {
                        if(list.get(getAdapterPosition()).getSubcategoriesCount() > 0){
                            Intent intent = new Intent(context, SubCatCountActivity.class);

                            intent.putExtra("url", list.get(getAdapterPosition()).getSubcategories());
                            intent.putExtra("catName", list.get(getAdapterPosition()).getTitle());
                            context.startActivity(intent);
                        }else {
                            Intent intent = new Intent(context, SubCatsActivity.class);
                            intent.putExtra("cid", list.get(getAdapterPosition()).getCatid());
                            intent.putExtra("catName", list.get(getAdapterPosition()).getTitle());
                            context.startActivity(intent);
                        }
                    }else if (list.get(getAdapterPosition()).getType().equals("category-news")){
                        Intent intent = new Intent(context, NewsActivity.class);
                        intent.putExtra("cid", list.get(getAdapterPosition()).getCatid());
                        intent.putExtra("catName", list.get(getAdapterPosition()).getTitle());
                        context.startActivity(intent);
                    }else if(list.get(getAdapterPosition()).getType().equals("page")){
                        Intent intent = new Intent(context, PageActivity.class);
                        intent.putExtra("url", list.get(getAdapterPosition()).getUrl());
                        intent.putExtra("title", list.get(getAdapterPosition()).getTitle());
                        context.startActivity(intent);
                    }else if(list.get(getAdapterPosition()).getType().equals("feedback")){
                        Intent intent = new Intent(context, ContactUsActivity.class);
                        intent.putExtra("title", list.get(getAdapterPosition()).getTitle());
                        context.startActivity(intent);
                    }else if(list.get(getAdapterPosition()).getType().equals("category-news-offers")){
                        Intent intent = new Intent(context, OffersActivity.class);
                        intent.putExtra("title", list.get(getAdapterPosition()).getTitle());
                        intent.putExtra("cid", list.get(getAdapterPosition()).getCatid());
                        context.startActivity(intent);
                    }else if(list.get(getAdapterPosition()).getType().equals("requestcard")){
                        Intent intent = new Intent(context, RequestCardActivity.class);
                        intent.putExtra("title", list.get(getAdapterPosition()).getTitle());
                        context.startActivity(intent);
                    }else if(list.get(getAdapterPosition()).getType().equals("storejoin")){
                        Intent intent = new Intent(context, RequestJoinActivity.class);
                        intent.putExtra("title", list.get(getAdapterPosition()).getTitle());
                        context.startActivity(intent);
                    }else if(list.get(getAdapterPosition()).getType().equals("partners")){
                        Intent intent = new Intent(context, PartnersActivity.class);
                        intent.putExtra("title", list.get(getAdapterPosition()).getTitle());
                        intent.putExtra("url", list.get(getAdapterPosition()).getUrl());
                        context.startActivity(intent);
                    }
                }
            });
        }
    }

}
