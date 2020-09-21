package com.hashtag.firstbetaqa;

import android.content.Context;
import android.graphics.Typeface;

import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * Created by samir on 3/20/17.
 */

public class MyTextView extends AppCompatTextView {


    public MyTextView(Context context) {
        super(context);
        Typeface typeface= Typeface.createFromAsset(context.getAssets(),"helveticaneueltarabic.ttf");
        setTypeface(typeface);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface typeface= Typeface.createFromAsset(context.getAssets(),"helveticaneueltarabic.ttf");
        setTypeface(typeface);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Typeface typeface= Typeface.createFromAsset(context.getAssets(),"helveticaneueltarabic.ttf");
        setTypeface(typeface);
    }


}
