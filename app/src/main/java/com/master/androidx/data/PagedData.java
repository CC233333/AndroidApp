package com.master.androidx.data;

import android.arch.lifecycle.LiveData;
import android.arch.paging.PagedList;

public class PagedData<T> {

    public LiveData<PagedList<T>> mData;
    public LiveData<AppState> mRefreshData;
    public LiveData<AppState> mMoreData;

    public PagedData(LiveData<PagedList<T>> data, LiveData<AppState> refreshData, LiveData<AppState> moreData) {
        mData = data;
        mRefreshData = refreshData;
        mMoreData = moreData;
    }

}
