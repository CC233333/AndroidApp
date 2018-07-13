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
import com.master.androidx.vm.ResultState;
import com.master.androidx.util.Toasts;

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
        mDetailViewModel.xData.observe(this, new Observer<ResultState>() {
            @Override
            public void onChanged(@Nullable ResultState appState) {
                if (appState != null) {
                    if (appState.isRunning()) {
                        mRefreshView.setVisibility(View.VISIBLE);
                    } else if (appState.isSuccess()) {
                        mRefreshView.setVisibility(View.GONE);
                    } else if (appState.isSuccess()) {
                        mRefreshView.setVisibility(View.GONE);
                        Toasts.shortToast(appState.getMessage());
                    }
                }
            }
        });
    }

    private void getImageDetail() {
        mDetailViewModel.detailCopy(mImageId);
    }

}
