package com.hashtag.firstbetaqa.adapter;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.hashtag.firstbetaqa.Model.SubCatsDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.RoundedCornersTransform;
import com.hashtag.firstbetaqa.activities.RegionsInSubCat;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by ebda3-mint on 9/18/17.
 */

public class SubCatsAdapter extends RecyclerView.Adapter<SubCatsAdapter.MyHolder>{

    Context context;
    List<SubCatsDataModel> list;


    public SubCatsAdapter(Context context, List<SubCatsDataModel> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.sub_cat_row,parent,false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        holder.subCatName.setText(list.get(position).getName());
        holder.centerNum.setText(list.get(position).getTotal()+"");
        Picasso.with(context).load(list.get(position).getPhoto()).transform(new RoundedCornersTransform(5,0)).placeholder(R.drawable.placeholder_small).fit().into(holder.subCatImageView);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class  MyHolder extends RecyclerView.ViewHolder

    {

        ImageView subCatImageView;
        TextView centerNum,subCatName;
        RelativeLayout relativeLayout;

        public MyHolder(View itemView) {
            super(itemView);
            subCatImageView=itemView.findViewById(R.id.subCatImage);
            centerNum=itemView.findViewById(R.id.centerNum);
            subCatName=itemView.findViewById(R.id.subCatName);
            relativeLayout=itemView.findViewById(R.id.relative);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, RegionsInSubCat.class);
                    intent.putExtra("cid", String.valueOf(list.get(getAdapterPosition()).getCatid()));
                    intent.putExtra("id",list.get(getAdapterPosition()).getId());
                    intent.putExtra("photo",list.get(getAdapterPosition()).getPhoto());
                    intent.putExtra("title",list.get(getAdapterPosition()).getName());
                    context.startActivity(intent);
                }
            });
        }
    }
}
