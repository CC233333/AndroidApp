package com.master.androidx.app;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

public class Selectors {

    public static Drawable create(Context context) {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Colors.getPrimaryColor(context));
        drawable.setCornerRadius(16);
        drawable.setStroke(1, Colors.getPrimaryDarkColor(context));
        return drawable;
    }

}
