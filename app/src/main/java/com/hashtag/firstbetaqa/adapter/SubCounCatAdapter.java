package com.hashtag.firstbetaqa.adapter;

import android.content.Context;
import android.content.Intent;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.hashtag.firstbetaqa.Model.SubCountCatsDataModel;
import com.hashtag.firstbetaqa.R;
import com.hashtag.firstbetaqa.activities.SubCatsActivity;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;


/**
 * Created by ebda3-mint on 12/12/17.
 */

public class SubCounCatAdapter extends RecyclerView.Adapter<SubCounCatAdapter.MyHolder> {

    Context context;
    List<SubCountCatsDataModel> list;
    String lang="";

    public SubCounCatAdapter(Context context, List<SubCountCatsDataModel> list) {
        this.context = context;
        this.list = list;
        lang=context.getResources().getConfiguration().locale.getLanguage();
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyHolder(LayoutInflater.from(context).inflate(R.layout.subcount_cats_row,parent,false));
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.title.setText(list.get(position).getCatName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView arrow;

        TextView title;

        LinearLayout linearLayout;

        public MyHolder(View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.subCountCatTitle);
            arrow=itemView.findViewById(R.id.leftArrow);
            linearLayout=itemView.findViewById(R.id.lin);

            if(lang.equals("ar")){
                linearLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
                arrow.setImageResource(R.drawable.leftarrow);
                title.setGravity(Gravity.RIGHT);

            }else if(lang.equals("en")){
                linearLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
                arrow.setImageResource(R.drawable.rightarrow);
                title.setGravity(Gravity.LEFT);
            }

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(context, SubCatsActivity.class);
            intent.putExtra("cid", list.get(getAdapterPosition()).getCatid());
            intent.putExtra("catName", list.get(getAdapterPosition()).getCatName());
            context.startActivity(intent);
        }
    }

}
