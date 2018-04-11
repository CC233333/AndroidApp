package com.master.android.http;


import com.master.android.api.HttpApi;
import com.master.android.api.MeituService;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cenzen on 2018/3/6.
 */

public final class HttpRetrofit {

    private static final String BASE_URL = "http://192.168.199.199:8080/";
//    private static final String BASE_URL = "https://api.youguoquan.com/";

    private static class Instance {
        private static final HttpRetrofit sInstance = new HttpRetrofit();
    }

    public static HttpRetrofit instance() {
        return Instance.sInstance;
    }

    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;
    private HttpApi mHttpApi;
    private MeituService userService;

    public HttpRetrofit() {
        mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        mHttpApi = mRetrofit.create(HttpApi.class);
        userService = mRetrofit.create(MeituService.class);
    }

    public HttpApi getHttpApi() {
        return mHttpApi;
    }

    public HttpApi createHttpApi() {
        return mRetrofit.create(HttpApi.class);
    }


    public MeituService getUserService() {
        return userService;
    }
}
