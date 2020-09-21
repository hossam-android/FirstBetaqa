package com.hashtag.firstbetaqa;

import android.content.Context;
import android.graphics.Typeface;

import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

/**
 * Created by samir on 3/21/17.
 */

public class MyEditText extends AppCompatEditText {

    public MyEditText(Context context) {
        super(context);
        Typeface typeface= Typeface.createFromAsset(context.getAssets(),"helveticaneueltarabic.ttf");
        setTypeface(typeface);

    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface typeface= Typeface.createFromAsset(context.getAssets(),"helveticaneueltarabic.ttf");
        setTypeface(typeface);
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Typeface typeface= Typeface.createFromAsset(context.getAssets(),"helveticaneueltarabic.ttf");
        setTypeface(typeface);
    }


}
