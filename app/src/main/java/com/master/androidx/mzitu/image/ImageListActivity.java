package com.master.androidx.mzitu.image;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.arch.paging.RxPagedListBuilder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.master.androidx.R;
import com.master.androidx.app.PagedListConfigs;
import com.master.androidx.vm.ResultState;
import com.master.androidx.https.PagedListObserver;
import com.master.androidx.util.Toasts;

import io.reactivex.Observable;

public class ImageListActivity extends AppCompatActivity {

    private RxPagedListBuilder<ImageBody, ImageObject> mRxPagedListBuilder;

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    private ImageAdapter mImageAdapter;

    private Observable<PagedList<ImageObject>> mObservable;

    private ImageViewModel mImageViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_mzitu_home);

        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRecyclerView = findViewById(R.id.recyclerView);

        mImageAdapter = new ImageAdapter(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mImageAdapter);

        createViewModel();
    }

    @Deprecated
    private void setupData() {
        ImageDataSourceFactory factory = new ImageDataSourceFactory(new ImageBody());
        mObservable =
                new RxPagedListBuilder<>(factory, PagedListConfigs.config())
                        .buildObservable();
        mObservable.subscribe(new PagedListObserver<PagedList<ImageObject>>() {
            @Override
            public void onNext(PagedList<ImageObject> imageObjects) {
                mImageAdapter.submitList(imageObjects);
            }
        });

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }
        });
    }

    @Deprecated
    private void refreshData() {
        ImageDataSourceFactory factory = new ImageDataSourceFactory(new ImageBody());
        mObservable = new RxPagedListBuilder<>(factory, PagedListConfigs.config())
                .buildObservable();
        mObservable.subscribe(new PagedListObserver<PagedList<ImageObject>>() {
            @Override
            public void onNext(PagedList<ImageObject> imageObjects) {
                mImageAdapter.submitList(imageObjects);
            }
        });
        factory.mDataSourceLiveData.observe(this, new Observer<ImageDataSource>() {
            @Override
            public void onChanged(@Nullable ImageDataSource mzituDataSource) {
                if (mzituDataSource != null) {
                    mzituDataSource.mLiveDataRefresh.observe(ImageListActivity.this, new Observer<ResultState>() {
                        @Override
                        public void onChanged(@Nullable ResultState networkState) {
                            if (networkState != null) {
                                mRefreshLayout.setRefreshing(networkState.isRunning());
                            }
                        }
                    });
                    mzituDataSource.mLiveDataMore.observe(ImageListActivity.this, new Observer<ResultState>() {
                        @Override
                        public void onChanged(@Nullable ResultState networkState) {
                            if (networkState != null) {
                                if (networkState.isFailed()) {
                                    Toasts.shortToast("已经到底了");
                                }
                            }
                        }
                    });
                }
            }
        });

    }

    private void createViewModel() {
        mImageViewModel = ViewModelProviders.of(this).get(ImageViewModel.class);
        mImageViewModel.init();

        mImageViewModel.getData().observe(this, new Observer<PagedList<ImageObject>>() {
            @Override
            public void onChanged(@Nullable PagedList<ImageObject> imageObjects) {
                mImageAdapter.submitList(imageObjects);
            }
        });

        mImageViewModel.getRefreshData().observe(this, new Observer<ResultState>() {
            @Override
            public void onChanged(@Nullable ResultState appState) {
                if (appState != null) {
                    mRefreshLayout.setRefreshing(appState.isRunning());
                }
            }
        });

        mImageViewModel.getMoreData().observe(this, new Observer<ResultState>() {
            @Override
            public void onChanged(@Nullable ResultState appState) {
                if (appState != null && appState.isFailed()) {
                    Toasts.shortToast("已经到底了");
                }
            }
        });

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mImageViewModel.refresh();
            }
        });

    }

    private void createFilter() {

    }

}
