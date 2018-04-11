package com.master.android.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.master.android.app.App;


/**
 * Created by cenzen on 2018/2/12.
 */

public class NetUtils {

    public static boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) App.instance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = null;
        if (manager != null) {
            info = manager.getActiveNetworkInfo();
        }
        return info != null && info.isConnectedOrConnecting();
    }

}
