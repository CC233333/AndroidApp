package com.master.androidx.mzitu.image;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import com.master.androidx.app.PagedListConfigs;
import com.master.androidx.data.AppState;

import static android.arch.lifecycle.Transformations.switchMap;

public class ImageViewModel extends ViewModel {

    private ImageDataSourceFactory mFactory;
    private LiveData<PagedList<ImageObject>> mData;
    private LiveData<AppState> mRefreshData;
    private LiveData<AppState> mMoreData;

    public LiveData<PagedList<ImageObject>> getData() {
        return mData;
    }

    public LiveData<AppState> getRefreshData() {
        return mRefreshData;
    }

    public LiveData<AppState> getMoreData() {
        return mMoreData;
    }

    public void init() {
        /*额外的参数*/
//        LiveData<ImageBody> body = new MutableLiveData<>();
//        mPagedData = Transformations.map(body, MzituRepository::create);
//        mData = Transformations.switchMap(mPagedData, input -> input.mData);
//        mRefreshData = Transformations.switchMap(mPagedData, input -> input.mRefreshData);
//        mMoreData = Transformations.switchMap(mPagedData, input -> input.mMoreData);

        ImageBody body = new ImageBody();
        mFactory = new ImageDataSourceFactory(body);

        mData = new LivePagedListBuilder<>(mFactory, PagedListConfigs.config()).build();
        mRefreshData = switchMap(mFactory.mDataSourceLiveData, input -> input.mLiveDataRefresh);
        mMoreData = switchMap(mFactory.mDataSourceLiveData, input -> input.mLiveDataMore);

    }

    public void refresh() {
        if (mFactory != null
                && mFactory.mDataSourceLiveData != null
                && mFactory.mDataSourceLiveData.getValue() != null) {
            mFactory.mDataSourceLiveData.getValue().invalidate();
        }
    }

    public void filter(ImageBody body) {

    }

//    public MutableLiveData<ImageBody> mBodyData = new MutableLiveData<>();
//
//    public void setBodyData(ImageBody body) {
//        mBodyData.setValue(body);
//    }
//
//    public LiveData<ImageDataSourceFactory> mFactoryData =
//            map(mBodyData, ImageDataSourceFactory::new);
//
//    public LiveData<PagedData<ImageObject>> mPagedData =
//            map(mFactoryData, ImageRepository::create);
//
//    public LiveData<PagedList<ImageObject>> mData2 =
//            new LivePagedListBuilder<>(mFactory, PagedListConfigs.config()).build();
//
//    public LiveData<AppState> mRefreshData2;
//    public LiveData<AppState> mMoreData2;

}
