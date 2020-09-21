package com.hashtag.firstbetaqa;

import android.content.Context;
import android.graphics.Typeface;

import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatButton;

/**
 * Created by Digial2 on 6/21/17.
 */

public class MyButton extends AppCompatButton {
    public MyButton(Context context) {
        super(context);
        Typeface typeface= Typeface.createFromAsset(context.getAssets(),"helveticaneueltarabic.ttf");
        setTypeface(typeface);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface typeface= Typeface.createFromAsset(context.getAssets(),"helveticaneueltarabic.ttf");
        setTypeface(typeface);
    }

    public MyButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Typeface typeface= Typeface.createFromAsset(context.getAssets(),"helveticaneueltarabic.ttf");
        setTypeface(typeface);
    }
}
