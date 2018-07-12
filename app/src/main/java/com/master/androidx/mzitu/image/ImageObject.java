package com.master.androidx.mzitu.image;

import java.util.Random;

public class ImageObject {

    public String thumb_src_min;
    public int id;
    public String title;
    public int img_num;
    public String thumb_src;

    public ImageObject() {

        this.title = "Title " + new Random().nextInt();
    }

    @Override
    public String toString() {
        return "MzituImage{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }

}
