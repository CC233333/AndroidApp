package com.master.androidx.ui;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.master.androidx.R;
import com.master.androidx.app.Infos;
import com.master.androidx.base.BaseActivity;
import com.master.promise.camera.CameraEngine;
import com.master.promise.utils.PathUtils;

public class CameraActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_camera);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View downView = findViewById(R.id.camera);
        downView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCamera();
            }
        });

        View pictureView = findViewById(R.id.picture);
        pictureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert();
            }
        });
    }

    private void alert() {
        new AlertDialog.Builder(this)
                .setTitle("Title")
                .setMessage("Message")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .create()
                .show();
    }

    private CameraEngine mCameraEngine;

    private void openCamera() {
        if (mCameraEngine == null) {
            mCameraEngine = new CameraEngine(this);
        }
        mCameraEngine.dispatchCaptureIntent(this, 1024, Infos.authority);
    }

    private void showImage() {
        Uri uri = mCameraEngine.getCurrentPhotoUri();
        String path = mCameraEngine.getCurrentPhotoPath();

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            revokeUriPermission(uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
        }

        ImageView imageView = findViewById(R.id.image_view);
        Glide.with(this)
                .load(uri)
                .apply(RequestOptions.fitCenterTransform())
                .into(imageView);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == 1024) {
            showImage();
            return;
        }

        if (requestCode == 1000) {
            showImage(data);
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void openPictures() {
        Intent starter = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(starter, 1000);
    }

    private void showImage(Intent data) {
        if (data != null && data.getData() != null) {
            Uri uri = data.getData();
            String path = PathUtils.getPath(this, uri);
            ImageView imageView = findViewById(R.id.image_view);
            Glide.with(this)
                    .load(uri)
                    .apply(RequestOptions.fitCenterTransform())
                    .into(imageView);
        }
    }

}
