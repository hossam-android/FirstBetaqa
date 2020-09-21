package com.hashtag.firstbetaqa.adapter;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.hashtag.firstbetaqa.Model.PartnersDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.activities.StoreDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by ebda3-mint on 12/11/17.
 */

public class PartnersGridAdapter extends RecyclerView.Adapter<PartnersGridAdapter.MyHolder>{

    Context context;
    List<PartnersDataModel> list;

    public PartnersGridAdapter(Context context, List<PartnersDataModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.partner_row,parent,false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Picasso.with(context).load(list.get(position).getPhoto()).placeholder(R.drawable.placeholder_small).into(holder.partnerImage);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView partnerImage;

        public MyHolder(View itemView) {
            super(itemView);
            partnerImage=itemView.findViewById(R.id.partnerImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            //WebView webView = new WebView(context);
           // webView.loadUrl(list.get(getAdapterPosition()).getUrl());

            Intent intent=new Intent(context, StoreDetailsActivity.class);
           intent.putExtra("itemid",list.get(getAdapterPosition()).getNetworkid());
            context.startActivity(intent);
          //  intent.putExtra("itemid",list.get(getAdapterPosition()).getDalilid());
        }
    }

}
