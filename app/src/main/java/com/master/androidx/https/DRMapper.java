package com.master.androidx.https;

import com.master.androidx.data.DR;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class DRMapper<T> implements Function<DR<T>, ObservableSource<? extends T>> {

    @Override
    public ObservableSource<? extends T> apply(DR<T> tdr) throws Exception {
        if (tdr.data == null) {
            return Observable.error(new AppException("null"));
        }
        return Observable.just(tdr.data);
    }

}