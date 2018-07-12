package com.master.androidx.https;

import android.support.annotation.NonNull;

import com.master.androidx.util.NetUtils;
import com.master.androidx.util.Toasts;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class AppObserver<T> implements Observer<T> {

    @Override
    public void onSubscribe(@NonNull Disposable disposable) {
        if (!NetUtils.isNetworkAvailable()) {
            if (!disposable.isDisposed())
                disposable.dispose();
            onError(new IOException());
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        if (e instanceof IOException) {
            Toasts.shortToast("请检查网络");
        } else if (e instanceof HttpException) {
            HttpException exception = (HttpException) e;
            Toasts.shortToast(exception.message());
        } else if (e instanceof EmptyException) {
            onDataEmpty();
        } else if (e instanceof AppException) {
            AppException exception = (AppException) e;
            Toasts.shortToast(exception.getMessage());
        } else {
            Toasts.shortToast(e.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }

    public void onDataEmpty() {

    }

}

