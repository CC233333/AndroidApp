package com.master.android.meitu;

import java.util.List;

public class ImageUrlResponse {

    public boolean r;
    public List<ImageUrl> i;

    public ImageUrlResponse(boolean r) {
        this.r = r;
    }

    public static ImageUrlResponse error(Throwable throwable) {
        return new ImageUrlResponse(false);
    }

}
