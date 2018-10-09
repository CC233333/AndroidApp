package com.master.androidx.download;

public interface ProgressListener {

    void update(long bytesRead, long contentLength, boolean done);

}
