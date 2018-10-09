package com.master.androidx.ui;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.master.androidx.R;
import com.master.androidx.base.BaseActivity;
import com.master.androidx.rx.NextObserver;
import com.master.androidx.util.Prefs;
import com.tbruyelle.rxpermissions2.RxPermissions;

public class DownloadActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_download);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View downView = findViewById(R.id.download);
        downView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                broadcast();
            }
        });

    }

    private void broadcast() {
        Intent intent = new Intent(this, DownloadReceiver.class);
        intent.setAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        intent.putExtra(DownloadManager.EXTRA_DOWNLOAD_ID, Prefs.apkId());
        sendBroadcast(intent);
    }

    private void checkPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new NextObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        downloadApk();
                    }
                });
    }


    private void downloadApk() {
        String url = "http://epso.dragra.com/and2/cd.apk";
        Uri uri = Uri.parse(url);

        DownloadManager.Request request = null;
        try {
            request = new DownloadManager.Request(uri);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        request.setTitle("app");
        request.setDescription("apk");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        }

        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "cd.apk");
        Context context = getApplicationContext();
        DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        if (dm != null) {
            long apkId = dm.enqueue(request);
            Prefs.apkId(apkId);
        }


    }

}
