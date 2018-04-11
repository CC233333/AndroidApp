package com.master.android.meitu.user;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.master.android.R;
import com.master.android.meitu.UserModel;
import com.master.android.meitu.UserResponse;
import com.master.android.meitu.image.ImageSpaceActivity;

public class UserActivity extends AppCompatActivity {

    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;

    private UserViewModel userViewModel;
    private UserAdapter adapter;

    private String tailId = "0";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity_list);

        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRecyclerView = findViewById(R.id.recyclerView);

        adapter = new UserAdapter(null);
        adapter.setEnableLoadMore(true);
        adapter.setOnLoadMoreListener(this::addData, mRecyclerView);
        adapter.setOnItemClickListener((adapter, view, position) -> {
            UserModel model = (UserModel) adapter.getData().get(position);
            ImageSpaceActivity.start(this, model.ID);
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(adapter);
        mRefreshLayout.setOnRefreshListener(this::updateData);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.liveData().observe(this, this::handleResult);
        userViewModel.nextPageLiveData().observe(this, this::nextPageResult);

        mRefreshLayout.setRefreshing(true);
        updateData();
    }

    private void updateData() {
        tailId = "0";
        userViewModel.refresh(tailId);
    }

    private void addData() {
        tailId = adapter.getData().get(adapter.getData().size() -  1).ID;
        userViewModel.nextPage(tailId);
    }

    private void handleResult(UserResponse response) {
        mRefreshLayout.setRefreshing(false);
        if (response.result) {
            if (response.discoverList != null && response.discoverList.size() != 0){
                adapter.setNewData(response.discoverList);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void nextPageResult(UserResponse response) {
        if (response.result) {
            adapter.loadMoreComplete();
            if (response.discoverList != null && response.discoverList.size() != 0){
                adapter.addData(response.discoverList);
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
