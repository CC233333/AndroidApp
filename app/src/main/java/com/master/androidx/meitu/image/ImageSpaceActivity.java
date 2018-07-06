package com.master.androidx.meitu.image;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.master.androidx.R;
import com.master.androidx.meitu.entity.ImageModel;
import com.master.androidx.meitu.entity.ImageResponse;

public class ImageSpaceActivity extends AppCompatActivity {

    public static void start(Context context, String modelId) {
        Intent starter = new Intent(context, ImageSpaceActivity.class);
        starter.putExtra("model_id", modelId);
        context.startActivity(starter);
    }

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    private ImageViewModel userViewModel;
    private ImageSpaceAdapter adapter;

    private String modelId;
    private Long maxTime;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_list);

        if (getIntent() != null) {
            modelId = getIntent().getStringExtra("model_id");
        }

        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRecyclerView = findViewById(R.id.recyclerView);

        adapter = new ImageSpaceAdapter(null);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(this::addData, mRecyclerView);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            ImageModel model = (ImageModel) adapter.getData().get(position);
            ImageDetailActivity.start(this, model.browseUrl);
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        mRefreshLayout.setOnRefreshListener(this::updateData);

        userViewModel = ViewModelProviders.of(this).get(ImageViewModel.class);
        userViewModel.liveData().observe(this, this::handleResult);
        userViewModel.nextPageLiveData().observe(this, this::nextPageResult);

        mRefreshLayout.setRefreshing(true);
        updateData();
    }

    private void updateData() {
        maxTime = 0L;
        userViewModel.refresh(modelId);
    }

    private void addData() {
        maxTime = adapter.getData().get(adapter.getData().size() -  1).postTime;
        userViewModel.nextPage(modelId, maxTime);
    }

    private void handleResult(ImageResponse response) {
        mRefreshLayout.setRefreshing(false);
        if (response.result) {
            if (response.albumList != null && response.albumList.size() != 0){
                adapter.setNewData(response.albumList);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void nextPageResult(ImageResponse response) {
        if (response.result) {
            adapter.loadMoreComplete();
            if (response.albumList != null && response.albumList.size() != 0){
                adapter.addData(response.albumList);
                adapter.notifyDataSetChanged();
            }
        } else {
            adapter.loadMoreFail();
        }

        if (response.isEnd) {
            adapter.loadMoreEnd();
        }

    }
}
