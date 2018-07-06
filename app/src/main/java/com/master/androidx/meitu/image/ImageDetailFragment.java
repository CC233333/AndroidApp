package com.master.androidx.meitu.image;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.master.androidx.R;

public class ImageDetailFragment extends Fragment {

    public static ImageDetailFragment newInstance(int position, int size, String url) {
        Bundle args = new Bundle();
        args.putInt("position", position);
        args.putInt("size", size);
        args.putString("url", url);
        ImageDetailFragment fragment = new ImageDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private int position;
    private int size;
    private String url;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            url = getArguments().getString("url");
            position = getArguments().getInt("position");
            size = getArguments().getInt("size");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.meitu_fragment_image_detail, container, false);
        ImageView imageView = rootView.findViewById(R.id.image_view);
        TextView positionView = rootView.findViewById(R.id.position_view);
        TextView sizeView = rootView.findViewById(R.id.size_view);
        Glide.with(this).load(url).dontAnimate().into(imageView);
        positionView.setText(String.valueOf(position));
        sizeView.setText(String.valueOf(size));
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }
}
