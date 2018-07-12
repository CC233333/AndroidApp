package com.master.androidx.mzitu;

import com.master.androidx.mzitu.detail.DetailObject;
import com.master.androidx.mzitu.image.ImageObject;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MzituService {

    @GET("wp-json/wp/v2/posts")
    Observable<List<ImageObject>>
    news(@Query("per_page") int count,
         @Query("page") int page);

    @GET("wp-json/wp/v2/i")
    Observable<DetailObject>
    detail(@Query("id") int id);

}
