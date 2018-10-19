package com.master.androidx.app;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.master.androidx.R;

public class Colors {

    public static int getColor(Context context, int colorRes) {
        return ContextCompat.getColor(context, colorRes);
    }

    public static int getPrimaryColor(Context context) {
        return ContextCompat.getColor(context, R.color.colorPrimary);
    }

    public static int getPrimaryDarkColor(Context context) {
        return ContextCompat.getColor(context, R.color.colorPrimaryDark);
    }

}
