package com.master.androidx.meitu.image;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.master.androidx.meitu.MeituRepository;
import com.master.androidx.meitu.entity.ImageResponse;

public class ImageViewModel extends ViewModel{

    private MutableLiveData<ImageResponse> liveData = new MutableLiveData<>();

    public MutableLiveData<ImageResponse> liveData() {
        return liveData;
    }

    private MutableLiveData<ImageResponse> nextPageLiveData = new MutableLiveData<>();

    public MutableLiveData<ImageResponse> nextPageLiveData() {
        return nextPageLiveData;
    }

    public void refresh(String id) {
        MeituRepository.requestImageResponse(id, 0L, liveData);
    }

    public void nextPage(String id, Long maxTime) {
        MeituRepository.requestImageResponse(id, maxTime, nextPageLiveData);
    }

}
