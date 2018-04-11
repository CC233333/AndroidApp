package com.master.android.http;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by master_c on 2017/5/22.
 */

public class RxSchedulers {

    public static <T> ObservableTransformer<T, T> scheduler() {
        return upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
    }


//    public static ObservableTransformer<AppResponse, AppResponse> appTransformer() {
//        return upstream -> upstream.flatMap(response -> {
//            if (response.success) {
//                return Observable.just(response);
//            } else {
//                return Observable.error(new Throwable(response.errorCode));
//            }
//        });
//    }
//
//    public static <T> ObservableTransformer<BaseResponse<T>, T> baseTransformer() {
//        return upstream -> upstream.flatMap(response -> {
//            if (response.isSuccess()) {
//                if (response.data == null)
//                    return Observable.error(new Throwable("null"));
//                return Observable.just(response.data);
//            } else {
//                return Observable.error(new Throwable(response.errorCode));
//            }
//        });
//    }
//
//    public static <T> ObservableTransformer<ListResponse<T>, T> listTransformer() {
//        return upstream -> upstream.flatMap(response -> {
//            if (response.data == null)
//                return Observable.error(new Throwable("null"));
//            return Observable.just(response.data);
//        });
//    }

}
