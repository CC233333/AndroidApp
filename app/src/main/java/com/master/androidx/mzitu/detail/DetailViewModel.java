package com.master.androidx.mzitu.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.master.androidx.data.AppState;
import com.master.androidx.data.HttpState;
import com.master.androidx.https.AppObserver;
import com.master.androidx.https.MainScheduler;
import com.master.androidx.mzitu.MzituRetrofit;
import com.master.androidx.mzitu.image.ImageBody;

public class DetailViewModel extends ViewModel {

    public MutableLiveData<HttpState> mHttpData = new MutableLiveData<>();
    public MutableLiveData<AppState> mAppData = new MutableLiveData<>();
    public MutableLiveData<DetailObject> mDetailData = new MutableLiveData<>();

    public MutableLiveData<Boolean> mRefreshData = new MutableLiveData<>();

    /*请求服务器*/
    public void detail(int id) {
        mRefreshData.setValue(true);
        MzituRetrofit.instance()
                .mzituService()
                .detail(id)
                .compose(new MainScheduler<>())
                .subscribe(new AppObserver<DetailObject>() {
                    @Override
                    public void onNext(DetailObject detailObject) {
                        mDetailData.setValue(detailObject);
                        mRefreshData.setValue(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        super.onError(e);
                        mAppData.setValue(AppState.failed());
                        mRefreshData.setValue(false);
                    }
                });
    }


    /*根据参数自动请求服务器, 不可行啊？？？？*/
    private MutableLiveData<ImageBody> mBodyData = new MutableLiveData<>();

    public void setBodyData(ImageBody body) {
        mBodyData.setValue(body);
    }

    private LiveData<DetailObject> mDetailData2 =
            Transformations.map(mBodyData, input -> null);


}
