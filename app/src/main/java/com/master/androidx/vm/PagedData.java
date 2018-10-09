package com.master.androidx.vm;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

public class PagedData<T> {

    public LiveData<PagedList<T>> mData;
    public LiveData<ResultState> mRefreshData;
    public LiveData<ResultState> mMoreData;

    public PagedData(LiveData<PagedList<T>> data,
                     LiveData<ResultState> refreshData,
                     LiveData<ResultState> moreData) {
        mData = data;
        mRefreshData = refreshData;
        mMoreData = moreData;
    }

}
