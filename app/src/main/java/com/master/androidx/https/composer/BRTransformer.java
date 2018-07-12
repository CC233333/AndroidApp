package com.master.androidx.https.composer;

import com.master.androidx.data.BR;
import com.master.androidx.https.mapper.BRMapper;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;

public class BRTransformer<T> implements ObservableTransformer<BR<T>, T> {

    @Override
    public ObservableSource<T> apply(Observable<BR<T>> upstream) {
        return upstream.flatMap(new BRMapper<>());
    }

}
