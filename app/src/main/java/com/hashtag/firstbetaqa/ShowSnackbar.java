package com.hashtag.firstbetaqa;

import android.graphics.Color;
import android.graphics.Typeface;

import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

/**
 * Created by ebda3-mint on 8/15/17.
 */

public class ShowSnackbar {


    private ShowSnackbar() {
    }

    public static void showSnack(View layout, String msg){

        Typeface typeface= Typeface.createFromAsset(layout.getContext().getAssets(),"helveticaneueltarabic.ttf");

        Snackbar snackbar=Snackbar.make(layout,msg, Snackbar.LENGTH_LONG);
        snackbar.show();
        View view = snackbar.getView();
        view.setBackgroundColor(Color.parseColor("#FBB040"));
        TextView txtv = (TextView) view.findViewById(R.id.snackbar_text);
        txtv.setTypeface(typeface);
        txtv.setTextColor(Color.parseColor("#ffffff"));
        txtv.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);


//        FrameLayout.LayoutParams params =(FrameLayout.LayoutParams)view.getLayoutParams();
//        params.gravity = Gravity.TOP;
//        view.setLayoutParams(params);
    }

}
