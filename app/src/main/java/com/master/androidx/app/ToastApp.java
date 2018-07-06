package com.master.androidx.app;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

/**
 * Created by cenzen on 2018/2/9.
 */

public final class ToastApp {

    private ToastApp() {
    }

    public static void toast(CharSequence aText) {
        toast(App.instance(), aText, Toast.LENGTH_SHORT);
    }

    public static void toast(int aResId) {
        toast(App.instance(), aResId, Toast.LENGTH_SHORT);
    }

    public static void toast(Context aContext, CharSequence aText) {
        toast(aContext, aText, Toast.LENGTH_SHORT);
    }

    public static void toast(Context aContext, int aResId) {
        toast(aContext, aResId, Toast.LENGTH_SHORT);
    }

    public static void toast(Context aContext, CharSequence aText, int aDuration) {
        if (aContext == null)
            return;

        if (TextUtils.isEmpty(aText))
            return;

        Toast.makeText(aContext, aText, aDuration).show();
    }

    public static void toast(Context aContext, int aResId, int aDuration) {
        if (aContext == null)
            return;

        Toast.makeText(aContext, aResId, aDuration).show();
    }

}
