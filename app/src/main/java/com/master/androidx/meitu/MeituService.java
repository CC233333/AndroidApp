package com.master.androidx.meitu;

import com.master.androidx.meitu.entity.ImageResponse;
import com.master.androidx.meitu.entity.ImageUrlResponse;
import com.master.androidx.meitu.entity.UserResponse;

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
