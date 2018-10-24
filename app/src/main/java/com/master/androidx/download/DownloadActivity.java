package com.master.androidx.download;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.master.androidx.R;
import com.master.androidx.base.BaseActivity;
import com.master.androidx.https.NextObserver;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.io.File;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

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
                checkPermission();
            }
        });
    }

    private void checkPermission() {
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe(new NextObserver<Boolean>() {
                    @Override
                    public void onNext(Boolean aBoolean) {
                        attemptInstall();
                    }
                });
    }

    private ProgressListener mProgressListener = new ProgressListener() {
        @Override
        public void update(long bytesRead, long contentLength, boolean done) {
            Log.d("AndroidDemo", Thread.currentThread().getName());
        }
    };

    private void downloadApk() {
        String url = "http://epso.dragra.com/and2/cd.apk";

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new ProgressInteceptor(mProgressListener))
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://epso.dragra.com/")
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        DownloadApi api = retrofit.create(DownloadApi.class);
        api.download(url)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .doOnNext(new Consumer<ResponseBody>() {
                    @Override
                    public void accept(ResponseBody responseBody) throws Exception {
                        Files.saveFile(responseBody);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Log.d("AndroidDemo", "onNext");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("AndroidDemo", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("AndroidDemo", "onComplete");
                    }
                });

    }

    private void simpleDownload() {
        String url = "http://epso.dragra.com/and2/cd.apk";

        OkHttpClient client = new OkHttpClient.Builder().build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://epso.dragra.com/")
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        DownloadApi api = retrofit.create(DownloadApi.class);
        api.download(url)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .map(new Function<ResponseBody, String>() {
                    @Override
                    public String apply(ResponseBody responseBody) throws Exception {
                        return Files.save(responseBody);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String string) {
                        Log.d("AndroidDemo", "onNext");
                        onInstallAction(string);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("AndroidDemo", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("AndroidDemo", "onComplete");
                    }
                });
    }

    private void attemptInstall() {
        try {
            File file = new File(Files.getDownloadsDirectory(), "cdlife.apk");
            onInstallAction(file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onInstallAction(String fileSavePath) {
        File file = new File(fileSavePath);
        Intent starter = new Intent(Intent.ACTION_VIEW);
        starter.addCategory(Intent.CATEGORY_DEFAULT);
        starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri data;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            data = FileProvider.getUriForFile(
                    this,
                    "com.master.android.fileprovider",
                    file);
            starter.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        } else {
            data = Uri.fromFile(file);
        }
        starter.setDataAndType(data, "application/vnd.android.package-archive");

        if (getPackageManager().queryIntentActivities(starter, 0).size() > 0) {
            startActivity(starter);
        }
    }

}