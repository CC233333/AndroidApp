package com.master.androidx.mzitu.image;

public class ImageBody {

    public int id;
    public int page;
    public int count;

    public ImageBody() {
    }

    public ImageBody(int page) {
        this.page = page;
        this.count = 10;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
