package com.master.androidx.mzitu.detail;

import android.arch.lifecycle.ViewModel;

import com.master.androidx.https.MainScheduler;
import com.master.androidx.mzitu.MzituRetrofit;

import io.reactivex.Observable;

public class RxDetailViewModel extends ViewModel {

    public Observable<DetailObject> detail(int id) {
        return MzituRetrofit.instance()
                .mzituService()
                .detail(id)
                .compose(new MainScheduler<>());
    }

}
