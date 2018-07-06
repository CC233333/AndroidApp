package com.master.androidx.meitu.image;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.master.androidx.meitu.entity.ImageUrl;

import java.util.List;

public class ImageDetailAdapter extends FragmentStatePagerAdapter {

    private List<ImageUrl> urls;

    public ImageDetailAdapter(FragmentManager fm, List<ImageUrl> urls) {
        super(fm);
        this.urls = urls;
    }

    @Override
    public Fragment getItem(int position) {
        return ImageDetailFragment.newInstance((position + 1), getCount(), urls.get(position).url);
    }

    @Override
    public int getCount() {
        return urls.size();
    }
}
