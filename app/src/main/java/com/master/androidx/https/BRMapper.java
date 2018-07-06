package com.master.androidx.https;

import com.master.androidx.data.BR;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class BRMapper<T> implements Function<BR<T>, ObservableSource<? extends T>> {

    @Override
    public ObservableSource<? extends T> apply(BR<T> tbr) throws Exception {
        if (tbr.isSuccessful()) {
            if (tbr.data == null) {
                return Observable.error(new AppException("null"));
            }
            return Observable.just(tbr.data);
        } else {
            return Observable.error(new AppException(tbr.message));
        }
    }

}