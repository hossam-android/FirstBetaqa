package com.hashtag.firstbetaqa.adapter;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.hashtag.firstbetaqa.Model.OfferDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.RoundedCornersTransform;
import com.hashtag.firstbetaqa.activities.StoreDetailsActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by ebda3-mint on 9/20/17.
 */

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.MyHolder>{


    Context context;
    List<OfferDataModel> list;

    public OfferAdapter(Context context, List<OfferDataModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.offers_row,parent,false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

      //  holder.title.setText(list.get(position).getCatName());
        holder.offername.setText(list.get(position).getTitle());
        Picasso.with(context).load(list.get(position).getPhoto()).transform
                (new RoundedCornersTransform(10,0)).
                placeholder(R.drawable.placeholder_small).fit().into(holder.offerImage);
        holder.offerContent.setText(list.get(position).getDescription());



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        TextView title,offername,offerContent;
        ImageView offerImage;
        RelativeLayout relativeLayout;

        public MyHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            offerImage=itemView.findViewById(R.id.offerImage);
            offername=itemView.findViewById(R.id.offertitle);
            offerContent=itemView.findViewById(R.id.offerContent);
            relativeLayout=itemView.findViewById(R.id.relative);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, StoreDetailsActivity.class);
                  //  intent.putExtra("title",context.getResources().getString(R.string.offerDetailsTitle));
                    intent.putExtra("itemid",list.get(getAdapterPosition()).getNetworkid());
                    context.startActivity(intent);
                }
            });

        }
    }

}
