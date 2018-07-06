package com.master.androidx.https;

import com.master.androidx.data.BR;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

public class BRTransformer<T> implements ObservableTransformer<BR<T>, T> {

    @Override
    public ObservableSource<T> apply(Observable<BR<T>> upstream) {
        return upstream.flatMap(new BRMapper<>());
    }

}
