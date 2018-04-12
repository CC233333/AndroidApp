package com.master.android.meitu.user;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.master.android.datasource.MeituSource;
import com.master.android.meitu.UserResponse;

public class UserViewModel extends ViewModel {

    private MutableLiveData<UserResponse> liveData = new MutableLiveData<>();

    public MutableLiveData<UserResponse> liveData() {
        return liveData;
    }

    private MutableLiveData<UserResponse> nextPageLiveData = new MutableLiveData<>();

    public MutableLiveData<UserResponse> nextPageLiveData() {
        return nextPageLiveData;
    }

    public void refresh(String id) {
        MeituSource.requestUserResponse(id, liveData);
    }

    public void nextPage(String id) {
        MeituSource.requestUserResponse(id, nextPageLiveData);
    }

}
