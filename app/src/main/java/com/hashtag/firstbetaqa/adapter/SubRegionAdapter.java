package com.hashtag.firstbetaqa.adapter;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.hashtag.firstbetaqa.Model.SubRegionDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.RoundedCornersTransform;
import com.hashtag.firstbetaqa.activities.StoreDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by ebda3-mint on 9/18/17.
 */

public class SubRegionAdapter extends RecyclerView.Adapter<SubRegionAdapter.MyHolder> {


    Context context;
    List<SubRegionDataModel> list;

    public SubRegionAdapter(Context context, List<SubRegionDataModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.sub_region_row,parent,false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        holder.subRegionTitle.setText(list.get(position).getTitle());
        Picasso.with(context).load(list.get(position).getPhoto()).transform(new RoundedCornersTransform(10,0)).placeholder(R.drawable.placeholder_small).fit().into(holder.subRegionImage);



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        ImageView subRegionImage;
        TextView subRegionTitle;
        RelativeLayout relativeLayout;

        public MyHolder(View itemView) {
            super(itemView);

            subRegionImage=itemView.findViewById(R.id.subRegionImage);
            subRegionTitle=itemView.findViewById(R.id.subRegionTitle);
            relativeLayout=itemView.findViewById(R.id.relative);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, StoreDetailsActivity.class);
                    intent.putExtra("itemid",list.get(getAdapterPosition()).getItemid());
                    intent.putExtra("title",list.get(getAdapterPosition()).getCatName());
                    context.startActivity(intent);
                }
            });

        }
    }
}
