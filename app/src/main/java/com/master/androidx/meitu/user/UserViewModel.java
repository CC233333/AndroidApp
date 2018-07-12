package com.master.androidx.meitu.user;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.master.androidx.meitu.MeituRepository;
import com.master.androidx.meitu.entity.UserResponse;

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
        MeituRepository.requestUserResponse(id, liveData);
    }

    public void nextPage(String id) {
        MeituRepository.requestUserResponse(id, nextPageLiveData);
    }

}
