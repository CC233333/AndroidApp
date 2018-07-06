package com.master.androidx.http;


import com.master.androidx.meitu.MeituService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cenzen on 2018/3/6.
 */

public final class RxRetrofit {
//    private static final String BASE_URL = "http://192.168.31.104:8080/";
    private static final String BASE_URL = "http://192.168.199.201:8080/";
//    private static final String BASE_URL = "http://47.75.87.7:8080/";

    private static class Instance {
        private static final RxRetrofit sInstance = new RxRetrofit();
    }

    public static RxRetrofit instance() {
        return Instance.sInstance;
    }

    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;
    private MeituService userService;

    public RxRetrofit() {
        mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        userService = mRetrofit.create(MeituService.class);
    }

    public MeituService getUserService() {
        return userService;
    }
}
