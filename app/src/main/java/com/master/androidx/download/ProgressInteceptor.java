package com.master.androidx.download;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class ProgressInteceptor implements Interceptor {

    private final ProgressListener mProgressListener;

    public ProgressInteceptor(ProgressListener progressListener) {
        mProgressListener = progressListener;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());
        return response.newBuilder()
                .body(new ProgressResponseBody(response.body(), mProgressListener))
                .build();
    }

}
