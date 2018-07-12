package com.master.androidx.https.mapper;

import com.master.androidx.data.DR;
import com.master.androidx.https.EmptyException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

public class DRMapper<T> implements Function<DR<T>, ObservableSource<? extends T>> {

    @Override
    public ObservableSource<? extends T> apply(DR<T> tdr) throws Exception {
        if (tdr.data == null) {
            return Observable.error(new EmptyException());
        }
        return Observable.just(tdr.data);
    }

}