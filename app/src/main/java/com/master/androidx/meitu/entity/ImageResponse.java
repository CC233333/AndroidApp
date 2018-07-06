package com.master.androidx.meitu.entity;

import java.util.List;

public class ImageResponse {

    public boolean result;
    public boolean isEnd;
    public long realTime;

    public List<ImageModel> albumList;

    public ImageResponse(boolean result) {
        this.result = result;
    }

    public static ImageResponse error(Throwable throwable) {
        return new ImageResponse(false);
    }

}
