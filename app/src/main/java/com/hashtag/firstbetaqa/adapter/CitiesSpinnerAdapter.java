package com.hashtag.firstbetaqa.adapter;

import android.content.Context;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.hashtag.firstbetaqa.Model.CitiesDataModel;
import com.hashtag.firstbetaqa.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


/**
 * Created by ebda3-mint on 12/11/17.
 */

public class CitiesSpinnerAdapter extends ArrayAdapter<CitiesDataModel> {

    Context context;
    int resource;
    List<CitiesDataModel> list;
    String lang="";

    public CitiesSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<CitiesDataModel> list) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
        this.list=list;
        lang=context.getResources().getConfiguration().locale.getLanguage();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view= LayoutInflater.from(context).inflate(resource , parent,false);

        TextView text=view.findViewById(R.id.spinnerText);

        text.setText(list.get(position).getCityname());

        if(lang.equals("ar")){
            text.setGravity(Gravity.RIGHT);
        }else if(lang.equals("en")){
            text.setGravity(Gravity.LEFT);
        }

        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(context).inflate(resource , parent,false);

        TextView text=view.findViewById(R.id.spinnerText);

        text.setText(list.get(position).getCityname());

        if(lang.equals("ar")){
            text.setGravity(Gravity.RIGHT);
        }else if(lang.equals("en")){
            text.setGravity(Gravity.LEFT);
        }

        return view;
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
