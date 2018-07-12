package com.master.androidx.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.widget.Toast;

import com.master.androidx.app.App;


public class Toasts {

    public static void shortToast(@StringRes int resource) {
        showToast(App.instance(), resource, Toast.LENGTH_SHORT);
    }

    public static void shortToast(String string) {
        showToast(App.instance(), string, Toast.LENGTH_SHORT);
    }

    public static void longToast(@StringRes int resource) {
        showToast(App.instance(), resource, Toast.LENGTH_SHORT);
    }

    public static void longToast(String string) {
        showToast(App.instance(), string, Toast.LENGTH_SHORT);
    }

    private static void showToast(Context context, @StringRes int resId, int duration) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        String text = context.getString(resId);
        if (!TextUtils.isEmpty(text) && !"".equals(text.trim())) {
            Toast.makeText(context, text, duration).show();

        }
    }

    private static void showToast(Context context, String text, int duration) {
        if (context == null) {
            return;
        }
        if (context instanceof Activity) {
            if (((Activity) context).isFinishing()) {
                return;
            }
        }
        if (!TextUtils.isEmpty(text) && !"".equals(text.trim())) {
            Toast.makeText(context, text, duration).show();
        }
    }

}
