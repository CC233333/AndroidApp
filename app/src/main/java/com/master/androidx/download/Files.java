package com.master.androidx.download;

import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import okhttp3.ResponseBody;

public class Files {

    public static String getDownloadsDirectory() {
        File parent = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        return parent.getAbsolutePath();
    }

    public static void saveFile(ResponseBody responseBody) {
        InputStream inputStream;
        FileOutputStream fileOutputStream;

        byte[] buffer = new byte[2048];
        int len;

        try {
            File file = new File(getDownloadsDirectory(), "cdlife2.apk");
            inputStream = responseBody.byteStream();
            fileOutputStream = new FileOutputStream(file);
            while ((len = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
            inputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String save(ResponseBody responseBody) {
        InputStream inputStream;
        FileOutputStream fileOutputStream;

        byte[] buffer = new byte[2048];
        int len;

        try {
            File file = new File(getDownloadsDirectory(), "cdlife3.apk");
            inputStream = responseBody.byteStream();
            fileOutputStream = new FileOutputStream(file);
            while ((len = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
            inputStream.close();
            fileOutputStream.close();

            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
