package com.master.androidx.mzitu.image;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;

public class ImageDataSourceFactory extends DataSource.Factory<Integer, ImageObject> {

    public MutableLiveData<ImageDataSource> mDataSourceLiveData =
            new MutableLiveData<>();

    private ImageBody mImageBody;

    public ImageDataSourceFactory(ImageBody imageBody) {
        mImageBody = imageBody;
    }

    @Override
    public DataSource<Integer, ImageObject> create() {
        ImageDataSource dataSource = new ImageDataSource();
        mDataSourceLiveData.postValue(dataSource);
        return dataSource;
    }

}
