package com.master.androidx.camera;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.master.androidx.R;
import com.master.androidx.app.Paths;
import com.master.androidx.base.BaseActivity;

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
                openPictures();
            }
        });
    }

    private CameraCompat mCameraCompat;

    private void openCamera() {
        if (mCameraCompat == null) {
            mCameraCompat = new CameraCompat(this);
        }
        mCameraCompat.dispatchCaptureIntent(this, 1024);
    }

    private void showImage() {
        Uri uri = mCameraCompat.getCurrentPhotoUri();
        String path = mCameraCompat.getCurrentPhotoPath();

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

        if (requestCode == 1000 && data != null) {

            // content://media/external/images/media/17207
            Uri uri = data.getData();
            Log.d("AppCamera", "onActivityResult: " + uri);

            ImageView imageView = findViewById(R.id.image_view);
            imageView.setImageURI(uri);

            // /storage/emulated/0/DCIM/Camera/IMG_20180804_113049.jpg
            String picturePath = Paths.getPath(this, uri);
            Log.d("AppCamera", "onActivityResult: " + picturePath);

            // /external/images/media/17207
            String path = uri.getPath();
            Log.d("AppCamera", "onActivityResult: " + path);
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    private void openPictures() {
        Intent starter = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(starter, 1000);
    }

}
