package com.master.androidx.ui;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;

import com.master.androidx.util.Prefs;

public class DownloadReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction() != null) {
            if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                long apkId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                onInstallAction(context, apkId);
            }
        }
    }

    private void installApk(Context context, long apkId) {
        long id = Prefs.apkId();
        if (id == apkId) {
            DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            if (dm != null) {
                Uri uri = dm.getUriForDownloadedFile(apkId);
                if (uri != null) {
                    intent.setDataAndType(uri, "application/vnd.android.package-archive");
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    if (Build.VERSION.SDK_INT >= 24) {
                        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                    context.startActivity(intent);
                }
            }
        }
    }

    private void onInstallAction(Context context, long apkId) {
        long id = Prefs.apkId();
        if (id == apkId) {
            DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            if (dm != null) {
                Uri uri = dm.getUriForDownloadedFile(apkId);
                if (uri != null) {
                    Intent starter = new Intent(Intent.ACTION_VIEW);
                    starter.addCategory(Intent.CATEGORY_DEFAULT);
                    starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    starter.setDataAndType(uri, "application/vnd.android.package-archive");
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        starter.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    }
                    if (context.getPackageManager().queryIntentActivities(starter, 0).size() > 0) {
                        context.startActivity(starter);
                    }
                }
            }
        }
    }


}
