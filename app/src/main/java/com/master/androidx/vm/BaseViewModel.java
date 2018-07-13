package com.master.androidx.vm;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.master.androidx.https.AppException;
import com.master.androidx.https.EmptyException;
import com.master.androidx.https.MainScheduler;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public class BaseViewModel extends ViewModel {

    public MutableLiveData<ResultState> xData = new MutableLiveData<>();
    public MutableLiveData<ResultState> yData = new MutableLiveData<>();

    public <T> MutableLiveData<T> fromObservable(Observable<T> observable, MutableLiveData<T> data) {
        observable
                .compose(new MainScheduler<>())
                .subscribe(new Observer<T>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        xData.setValue(ResultState.running());
                        yData.setValue(ResultState.running());
                    }

                    @Override
                    public void onNext(T t) {
                        data.setValue(t);
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof IOException) {
                            xData.setValue(ResultState.failed(e.getMessage()));
                            yData.setValue(ResultState.failed(e.getMessage()));
                        }
                        //HTTP
                        else if (e instanceof HttpException) {
                            HttpException ee = (HttpException) e;
                            xData.setValue(ResultState.failed(ee.getMessage()));
                            yData.setValue(ResultState.failed(ee.getMessage()));

                        }
                        //APP
                        else if (e instanceof AppException) {
                            AppException ee = (AppException) e;
                            xData.setValue(ResultState.failed(ee.getMessage()));
                            yData.setValue(ResultState.failed(ee.getMessage()));
                        }
                        //Empty
                        else if (e instanceof EmptyException) {
                            data.setValue(null);
                            xData.setValue(ResultState.success());
                            yData.setValue(ResultState.success());
                        }
                        //未定义错误
                        else {
                            xData.setValue(ResultState.failed());
                            yData.setValue(ResultState.failed());
                        }
                    }

                    @Override
                    public void onComplete() {
                        xData.setValue(ResultState.success());
                        yData.setValue(ResultState.success());
                    }
                });

        return data;
    }
}
