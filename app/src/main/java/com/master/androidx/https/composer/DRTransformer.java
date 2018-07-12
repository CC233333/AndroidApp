package com.master.androidx.https.composer;

import com.master.androidx.data.DR;
import com.master.androidx.https.mapper.DRMapper;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

public class DRTransformer<T> implements ObservableTransformer<DR<T>, T> {

    @Override
    public ObservableSource<T> apply(Observable<DR<T>> upstream) {
        return upstream.flatMap(new DRMapper<>());
    }

}