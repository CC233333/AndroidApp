package com.master.android.api;

import com.master.android.meitu.ImageResponse;
import com.master.android.meitu.ImageUrlResponse;
import com.master.android.meitu.UserResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MeituService {

    @GET("meitu/list")
    Observable<UserResponse>
    getUserResponse(@Query("tailId") String id);

    @GET("meitu/image")
    Observable<ImageResponse>
    getImageResponse(@Query("count") Integer count,
                     @Query("modelID") String id,
                     @Query("maxTime") Long maxTime);


    @GET("meitu/image/html")
    Observable<ImageUrlResponse>
    getImageUrlResponse(@Query("url") String url);

}
