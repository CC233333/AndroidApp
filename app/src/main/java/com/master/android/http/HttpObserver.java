package com.master.android.http;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.master.android.app.ToastApp;
import com.master.android.util.NetUtils;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by cenzen on 2018/1/19.
 */

public abstract class HttpObserver<T> implements Observer<T> {

    private Disposable mDisposable;
    private Context mContext;

    public HttpObserver(Context context) {
        mContext = context;
    }

    @Override
    public void onSubscribe(@NonNull Disposable disposable) {
        mDisposable = disposable;
        if (!NetUtils.isNetworkAvailable()) {
            if (!disposable.isDisposed())
                disposable.dispose();
            ToastApp.toast("请检查网络");
            onError();
        }
    }

    @Override
    public void onNext(@NonNull T aResponse) {
    }

    @Override
    public void onError(@NonNull Throwable exception) {
        if (exception instanceof HttpException) {
            HttpException httpException = (HttpException) exception;
            ToastApp.toast("服务器错误" + httpException.code());
            onError();
            return;
        }

        if (exception instanceof ConnectException) {
            ToastApp.toast("网络连接异常");
            onError();
            return;
        }

        if (exception instanceof SocketTimeoutException) {
            ToastApp.toast("网络连接超时");
            onError();
            return;
        }

        if (exception instanceof ConnectTimeoutException) {
            ToastApp.toast("网络连接超时");
            onError();
            return;
        }

        if (exception instanceof JsonIOException) {
            ToastApp.toast("数据解析错误");
            onError();
            return;
        }

        if (exception instanceof JsonParseException) {
            ToastApp.toast("数据解析错误");
            onError();
            return;
        }

        ToastApp.toast("Error");

        onError();
    }

    @Override
    public void onComplete() {
        if (!mDisposable.isDisposed())
            mDisposable.dispose();
        onDispose();
    }

    public void onNone() {
        if (!mDisposable.isDisposed())
            mDisposable.dispose();
        onDispose();
    }

    public void onError() {
        if (!mDisposable.isDisposed())
            mDisposable.dispose();
        onDispose();
    }

    public void onDispose() {

    }

}
