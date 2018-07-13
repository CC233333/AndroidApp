package com.master.androidx.mzitu.image;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;

import com.master.androidx.vm.ResultState;
import com.master.androidx.https.PagedListObserver;
import com.master.androidx.mzitu.MzituRetrofit;

import java.util.List;

public class ImageDataSource extends PageKeyedDataSource<Integer, ImageObject> {

    public MutableLiveData<ResultState> mLiveDataRefresh
            = new MutableLiveData<>();

    public MutableLiveData<ResultState> mLiveDataMore
            = new MutableLiveData<>();

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params,
                            @NonNull LoadInitialCallback<Integer, ImageObject> callback) {
        mLiveDataRefresh.postValue(ResultState.running());
        MzituRetrofit.instance()
                .mzituService()
                .news(params.requestedLoadSize, 1)
                .subscribe(new PagedListObserver<List<ImageObject>>() {
                    @Override
                    public void onNext(List<ImageObject> imageObjects) {
                        callback.onResult(imageObjects, null, 51);
                        mLiveDataRefresh.postValue(ResultState.success());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mLiveDataRefresh.postValue(ResultState.failed());
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params,
                           @NonNull LoadCallback<Integer, ImageObject> callback) {
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params,
                          @NonNull LoadCallback<Integer, ImageObject> callback) {
        mLiveDataMore.postValue(ResultState.running());
        MzituRetrofit.instance()
                .mzituService()
                .news(params.requestedLoadSize, params.key)
                .subscribe(new PagedListObserver<List<ImageObject>>() {
                    @Override
                    public void onNext(List<ImageObject> imageObjects) {
                        callback.onResult(imageObjects, params.key + 50);
                        mLiveDataMore.postValue(ResultState.success());
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        mLiveDataMore.postValue(ResultState.failed());
                    }
                });
    }

}
