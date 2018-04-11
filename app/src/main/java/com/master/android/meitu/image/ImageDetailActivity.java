package com.master.android.meitu.image;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.master.android.R;
import com.master.android.http.HttpRetrofit;
import com.master.android.http.RxSchedulers;
import com.master.android.meitu.ImageUrlResponse;

public class ImageDetailActivity extends AppCompatActivity {
    
    public static void start(Context context, String browserUrl) {
        Intent starter = new Intent(context, ImageDetailActivity.class);
        starter.putExtra("browser_url", browserUrl);
        context.startActivity(starter);
    }

    private ViewPager viewPager;
    private String browserUrl;

    private ImageDetailAdapter imageDetailAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meitu_activity_image_detail);

        if (getIntent() != null) {
            browserUrl = getIntent().getStringExtra("browser_url");
        }

        viewPager = findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(5);
        request(browserUrl);
    }

    @SuppressLint("CheckResult")
    private void request(String url) {
        HttpRetrofit.instance()
                .getUserService()
                .getImageUrlResponse(url)
                .compose(RxSchedulers.scheduler())
                .onErrorReturn(ImageUrlResponse::error)
                .subscribe(this::handleResult);
    }

    private void handleResult(ImageUrlResponse response) {
        if (response.r) {
            if (response.i != null && response.i.size() != 0){
                viewPager.setAdapter(new ImageDetailAdapter(getSupportFragmentManager(), response.i));
            }
        }
    }

}
