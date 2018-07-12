package com.master.androidx.mzitu.detail;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.master.androidx.R;
import com.master.androidx.data.AppStateObserver;
import com.master.androidx.https.AppObserver;

public class ImageDetailActivity extends AppCompatActivity {

    private View mRefreshView;

    private TextView mResultView;

    private RxDetailViewModel mViewModel;

    private DetailViewModel mDetailViewModel;

    public static void start(Context context, int imageId) {
        Intent starter = new Intent(context, ImageDetailActivity.class);
        starter.putExtra("image_id", imageId);
        context.startActivity(starter);
    }

    private int mImageId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_mzitu_detail);

        if (getIntent() != null) {
            mImageId = getIntent().getIntExtra("image_id", -1);
        }

        mRefreshView = findViewById(R.id.refreshView);
        findViewById(R.id.requestBtn)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getImageDetail();
                    }
                });
        mResultView = findViewById(R.id.resultView);

        createViewModel();

    }

    private void createViewModel() {
        mDetailViewModel = ViewModelProviders.of(this).get(DetailViewModel.class);
        mDetailViewModel.mDetailData.observe(this, new Observer<DetailObject>() {
            @Override
            public void onChanged(@Nullable DetailObject detailObject) {
                if (detailObject != null) {
                    mResultView.setText(detailObject.content);
                }
            }
        });
        mDetailViewModel.mAppData.observe(this, new AppStateObserver());
        mDetailViewModel.mRefreshData.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean != null) {
                    mRefreshView.setVisibility(aBoolean ? View.VISIBLE : View.GONE);
                }
            }
        });
    }

    private void getImageDetail() {
        mDetailViewModel.detail(mImageId);
    }


    private void rxViewModel() {
        mViewModel = ViewModelProviders.of(this).get(RxDetailViewModel.class);
    }

    private void getDetail() {
        mViewModel.detail(137510)
                .subscribe(new AppObserver<DetailObject>() {
                    @Override
                    public void onNext(DetailObject detailObject) {

                    }
                });
    }


}
