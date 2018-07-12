package com.master.androidx.https.mapper;

import com.master.androidx.data.BR;
import com.master.androidx.https.AppException;
import com.master.androidx.https.EmptyException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class BRMapper<T> implements Function<BR<T>, ObservableSource<? extends T>> {

    @Override
    public ObservableSource<? extends T> apply(BR<T> tbr) throws Exception {
        if (tbr.isSuccessful()) {
            if (tbr.data == null) {
                return Observable.error(new EmptyException());
            }
            return Observable.just(tbr.data);
        } else {
            return Observable.error(new AppException(tbr.code, tbr.message));
        }
    }

}