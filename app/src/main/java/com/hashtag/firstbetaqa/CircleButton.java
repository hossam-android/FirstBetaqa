package com.hashtag.firstbetaqa;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

/**
 * Created by ebda3-mint on 9/20/17.
 */

public class CircleButton extends CircularProgressButton {


    public CircleButton(Context context) {
        super(context);
        Typeface typeface= Typeface.createFromAsset(context.getAssets(),"helveticaneueltarabic-bold.ttf");
        setTypeface(typeface);
    }

    public CircleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface typeface= Typeface.createFromAsset(context.getAssets(),"helveticaneueltarabic-bold.ttf");
        setTypeface(typeface);
    }

    public CircleButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Typeface typeface= Typeface.createFromAsset(context.getAssets(),"helveticaneueltarabic-bold.ttf");
        setTypeface(typeface);
    }
}
