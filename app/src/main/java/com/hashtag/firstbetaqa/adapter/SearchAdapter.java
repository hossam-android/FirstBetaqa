package com.hashtag.firstbetaqa.adapter;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.hashtag.firstbetaqa.Model.SearchDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.RoundedCornersTransform;
import com.hashtag.firstbetaqa.activities.StoreDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by ebda3-mint on 11/15/17.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyHolder>{

    Context context;
    List<SearchDataModel> list;
    String lang;
    Locale current;

    public SearchAdapter(Context context, List<SearchDataModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.search_row,parent,false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        Picasso.with(context).load(list.get(position).getPhoto()).transform(new RoundedCornersTransform(5,0)).placeholder(R.drawable.placeholder_small).fit().into(holder.imageView);
        holder.title.setText(list.get(position).getTitle());
        holder.percentage.setText(list.get(position).getPercentage());
        holder.city.setText(list.get(position).getCity());
        holder.area.setText(list.get(position).getDistrict());
        holder.cateogry.setText(list.get(position).getCatName());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView title,cateogry,city,area,percentage;
        ImageView imageView;

        public MyHolder(View itemView) {
            super(itemView);
            current=context.getResources().getConfiguration().locale;
            lang=current.getLanguage();
            if(lang.equals("ar")){
                itemView.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

            }else if(lang.equals("en")){
                itemView.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            }
            imageView=itemView.findViewById(R.id.searchImage);
            title=itemView.findViewById(R.id.title);
            city=itemView.findViewById(R.id.city);
            area=itemView.findViewById(R.id.area);
            cateogry=itemView.findViewById(R.id.cateogry);
            percentage=itemView.findViewById(R.id.percentage);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            Intent intent=new Intent(context, StoreDetailsActivity.class);
            intent.putExtra("itemid",list.get(getAdapterPosition()).getItemid());
            intent.putExtra("title",list.get(getAdapterPosition()).getCatName());
            context.startActivity(intent);
        }
    }
}
