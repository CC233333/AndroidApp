package com.master.androidx.util;

import android.content.SharedPreferences;

import com.master.androidx.app.App;

import static android.content.Context.MODE_PRIVATE;

public class Prefs {

    private static final String FILE_NAME = "demo_prefs";

    private static SharedPreferences pref() {
        return App.instance().getSharedPreferences(FILE_NAME, MODE_PRIVATE);
    }

    public static long apkId() {
        return pref().getLong("apk_id", 0);
    }

    public static void apkId(long apkId) {
        pref().edit().putLong("apk_id", apkId).apply();
    }

}
