package com.master.androidx.http;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.master.androidx.app.Toasts;
import com.master.androidx.util.NetUtils;

import org.apache.http.conn.ConnectTimeoutException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

/**
 * Created by cenzen on 2018/1/19.
 */
public abstract class RxObserver<T> implements Observer<T> {

    private Disposable mDisposable;
    private Context mContext;

    public RxObserver(Context context) {
        mContext = context;
    }

    @Override
    public void onSubscribe(@NonNull Disposable disposable) {
        mDisposable = disposable;
        if (!NetUtils.isNetworkAvailable()) {
            if (!disposable.isDisposed())
                disposable.dispose();
            Toasts.toast("请检查网络");
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
            Toasts.toast(httpException.getMessage());
            onError();
            return;
        }

        if (exception instanceof ConnectException) {
            Toasts.toast("网络连接异常");
            onError();
            return;
        }

        if (exception instanceof SocketTimeoutException) {
            Toasts.toast("网络连接超时");
            onError();
            return;
        }

        if (exception instanceof ConnectTimeoutException) {
            Toasts.toast("网络连接超时");
            onError();
            return;
        }

        if (exception instanceof JsonIOException) {
            Toasts.toast("数据解析错误");
            onError();
            return;
        }

        if (exception instanceof JsonParseException) {
            Toasts.toast("数据解析错误");
            onError();
            return;
        }

        Toasts.toast("Error");

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
