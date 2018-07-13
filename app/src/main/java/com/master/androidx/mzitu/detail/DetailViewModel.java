package com.master.androidx.mzitu.detail;

import android.arch.lifecycle.MutableLiveData;

import com.master.androidx.mzitu.MzituRetrofit;
import com.master.androidx.vm.BaseViewModel;

public class DetailViewModel extends BaseViewModel {

    public MutableLiveData<DetailObject> mDetailData = new MutableLiveData<>();

    public void detailCopy(int id) {
        fromObservable(
                MzituRetrofit.instance().mzituService().detail(id),
                mDetailData);
    }


    /*根据参数自动请求服务器, 不可行啊？？？？*/
//    private MutableLiveData<ImageBody> mBodyData = new MutableLiveData<>();
//
//    public void setBodyData(ImageBody body) {
//        mBodyData.setValue(body);
//    }
//
//    private LiveData<DetailObject> mDetailData2 =
//            Transformations.map(mBodyData, input -> null);


}
