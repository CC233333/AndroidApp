package com.master.android.app;

import android.app.Application;
import android.content.Context;

/**
 * Created by cenzen on 2017/12/13.
 */

public class App extends Application {

    private static App instance;

    public static App instance() {
        return instance;
    }

    public static Context context() {
        return instance().getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


}
