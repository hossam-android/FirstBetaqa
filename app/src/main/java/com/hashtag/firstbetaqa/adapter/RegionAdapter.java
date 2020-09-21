package com.hashtag.firstbetaqa.adapter;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.hashtag.firstbetaqa.Model.RegionDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.activities.SubRegionActivity;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by ebda3-mint on 9/18/17.
 */

public class RegionAdapter extends RecyclerView.Adapter<RegionAdapter.MyHolder> {

    Context context;
    List<RegionDataModel> list;

    public RegionAdapter(Context context, List<RegionDataModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.region_row,parent,false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {

        holder.centerNum.setText(list.get(position).getTotal()+"");
        holder.regionName.setText(list.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder{

        TextView regionName,centerNum;
        RelativeLayout relativeLayout;

        public MyHolder(View itemView) {
            super(itemView);
            regionName=itemView.findViewById(R.id.regionName);
            centerNum=itemView.findViewById(R.id.centerNum);
            relativeLayout=itemView.findViewById(R.id.relative);
            relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, SubRegionActivity.class);
                    intent.putExtra("cid", String.valueOf(list.get(getAdapterPosition()).getCatid()));
                    intent.putExtra("cityId",list.get(getAdapterPosition()).getId());
                    intent.putExtra("title",list.get(getAdapterPosition()).getName());
                    context.startActivity(intent);
                }

            });
        }
    }

}
