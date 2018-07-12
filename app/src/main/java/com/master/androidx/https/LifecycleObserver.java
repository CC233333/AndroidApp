package com.master.androidx.https;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LifecycleObserver<T> implements Observer<T> {

    private LifecycleOwner mOwner;
    private Disposable mDisposable;

    public LifecycleObserver(LifecycleOwner owner) {
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;
    }

    @Override
    public void onNext(T t) {
        if (mOwner.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
           onDispose();
           return;
        }
    }

    @Override
    public void onError(Throwable e) {
        if (mOwner.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
            onDispose();
            return;
        }
    }

    @Override
    public void onComplete() {
        if (mOwner.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
            onDispose();
            return;
        }
    }

    private void onDispose() {
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }


}
