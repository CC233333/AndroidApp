package com.master.androidx.app;

import android.arch.paging.PagedList;

public class PagedListConfigs {

    public static PagedList.Config config() {
        return new PagedList.Config.Builder()
                .setInitialLoadSizeHint(10)
                .setPageSize(10)
                .setPrefetchDistance(10)
                .build();
    }

}
