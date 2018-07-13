package com.master.androidx.mzitu.image;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.master.androidx.app.PagedListConfigs;
import com.master.androidx.vm.ResultState;
import com.master.androidx.vm.PagedData;

public class ImageRepository {

    public static PagedData<ImageObject> create(ImageBody body) {
        ImageDataSourceFactory factory = new ImageDataSourceFactory(body);

        LiveData<PagedList<ImageObject>> data =
                new LivePagedListBuilder<>(factory, PagedListConfigs.config()).build();

        LiveData<ResultState> refreshData =
                Transformations.switchMap(
                        factory.mDataSourceLiveData, input -> input.mLiveDataRefresh);

        LiveData<ResultState> moreData =
                Transformations.switchMap(
                        factory.mDataSourceLiveData, input -> input.mLiveDataMore);

        return new PagedData<>(data, refreshData, moreData);

    }

    public static PagedData<ImageObject> create(ImageDataSourceFactory factory) {
        LiveData<PagedList<ImageObject>> data =
                new LivePagedListBuilder<>(factory, PagedListConfigs.config()).build();

        LiveData<ResultState> refreshData =
                Transformations.switchMap(
                        factory.mDataSourceLiveData, input -> input.mLiveDataRefresh);

        LiveData<ResultState> moreData =
                Transformations.switchMap(
                        factory.mDataSourceLiveData, input -> input.mLiveDataMore);

        return new PagedData<>(data, refreshData, moreData);

    }

}
