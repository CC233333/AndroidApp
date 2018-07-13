package com.master.androidx.mzitu.image;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.PagedList;
import android.arch.paging.RxPagedListBuilder;

import com.master.androidx.app.PagedListConfigs;
import com.master.androidx.vm.ResultState;
import com.master.androidx.vm.PagedData;

import io.reactivex.Observable;

public class RxImageViewModel extends ViewModel {

    private ImageDataSourceFactory mFactory;

    private LiveData<PagedData<ImageObject>> mPagedData;
    private LiveData<PagedList<ImageObject>> mData;
    private LiveData<ResultState> mRefreshData;
    private LiveData<ResultState> mMoreData;


    public LiveData<PagedList<ImageObject>> getData() {
        return mData;
    }

    public LiveData<ResultState> getRefreshData() {
        return mRefreshData;
    }

    public LiveData<ResultState> getMoreData() {
        return mMoreData;
    }

    private Observable<PagedList<ImageObject>> mObservable;

    public void init() {
        mFactory = new ImageDataSourceFactory(new ImageBody());
        mObservable =
                new RxPagedListBuilder<>(mFactory, PagedListConfigs.config())
                        .buildObservable();
    }

    public void refresh() {
        if (mFactory != null
                && mFactory.mDataSourceLiveData != null
                && mFactory.mDataSourceLiveData.getValue() != null) {
            mFactory.mDataSourceLiveData.getValue().invalidate();
        }
    }

}
