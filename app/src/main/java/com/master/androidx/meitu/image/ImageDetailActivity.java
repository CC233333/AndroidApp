package com.master.androidx.meitu.image;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import com.master.androidx.R;
import com.master.androidx.base.BaseActivity;
import com.master.androidx.http.RxRetrofit;
import com.master.androidx.http.RxJavas;
import com.master.androidx.meitu.entity.ImageUrlResponse;

import io.reactivex.functions.Consumer;

public class ImageDetailActivity extends BaseActivity {
    
    public static void start(Context context, String browserUrl) {
        Intent starter = new Intent(context, ImageDetailActivity.class);
        starter.putExtra("browser_url", browserUrl);
        context.startActivity(starter);
    }

    private ViewPager viewPager;
    private String browserUrl;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meitu_activity_image_detail);

        if (getIntent() != null) {
            browserUrl = getIntent().getStringExtra("browser_url");
        }

        viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(5);
        request(browserUrl, this::handleResult);
    }

    private void handleResult(ImageUrlResponse response) {
        if (response.r) {
            if (response.i != null && response.i.size() != 0){
                viewPager.setAdapter(new ImageDetailAdapter(getSupportFragmentManager(), response.i));
            }
        }
    }

    @SuppressLint("CheckResult")
    private void request(String url, Consumer<ImageUrlResponse> onNext) {
        RxRetrofit.instance()
                .getUserService()
                .getImageUrlResponse(url)
                .compose(RxJavas.scheduler())
                .compose(bindToLifecycle())
                .onErrorReturn(ImageUrlResponse::error)
                .subscribe(onNext);
    }

}
