package com.master.androidx.mzitu;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MzituRetrofit {

    private static final String BASE_URL = "http://adr.meizitu.net/";

    private static class Instance {
        private static final MzituRetrofit INSTANCE = new MzituRetrofit();
    }

    public static MzituRetrofit instance() {
        return Instance.INSTANCE;
    }

    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;
    private MzituService userService;

    public MzituRetrofit() {
        mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();

        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        userService = mRetrofit.create(MzituService.class);
    }

    public MzituService mzituService() {
        return userService;
    }


}
