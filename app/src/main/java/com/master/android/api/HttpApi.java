package com.master.android.api;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by cenzen on 2018/3/6.
 */

public interface HttpApi {

    @GET("hello")
    Observable<String> hello();

}
