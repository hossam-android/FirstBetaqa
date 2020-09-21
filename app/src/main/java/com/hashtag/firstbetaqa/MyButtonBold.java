package com.hashtag.firstbetaqa;

import android.content.Context;
import android.graphics.Typeface;

import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

/**
 * Created by ebda3-mint on 8/22/17.
 */

public class MyButtonBold extends AppCompatButton {

    public MyButtonBold(Context context) {
        super(context);
        Typeface typeface= Typeface.createFromAsset(context.getAssets(),"helveticaneueltarabic-bold.ttf");
        setTypeface(typeface);
    }

    public MyButtonBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface typeface= Typeface.createFromAsset(context.getAssets(),"helveticaneueltarabic-bold.ttf");
        setTypeface(typeface);
    }

    public MyButtonBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Typeface typeface= Typeface.createFromAsset(context.getAssets(),"helveticaneueltarabic-bold.ttf");
        setTypeface(typeface);
    }

}
