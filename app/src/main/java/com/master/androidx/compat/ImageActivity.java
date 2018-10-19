package com.master.androidx.compat;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.master.androidx.R;
import com.master.androidx.app.Colors;
import com.master.androidx.app.Drawables;
import com.master.androidx.base.BaseActivity;

public class ImageActivity extends BaseActivity {

    private AppCompatButton mPictureView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_image);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View resetView = findViewById(R.id.reset);
        resetView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView imageView = findViewById(R.id.image_view);
                imageView.setImageResource(R.drawable.ic_android);
            }
        });


        View downView = findViewById(R.id.camera);
        downView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setImageTint(Color.BLUE);
            }
        });

        mPictureView = findViewById(R.id.picture);
        mPictureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPictureClicked();
            }
        });

        View imageView = findViewById(R.id.image_view);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    private void setImageTint(int color) {
        ImageView imageView = findViewById(R.id.image_view);
        Drawable drawable = Drawables.getDrawable(this, R.drawable.ic_android);
        Drawable drawableWrapper = Drawables.wrapTintSelector(drawable, Colors.getPrimaryColor(this), Colors.getPrimaryDarkColor(this));
        imageView.setImageDrawable(drawableWrapper);
    }

    private void onPictureClicked() {
//        ColorStateList tintList = ColorStateList.valueOf(Colors.getPrimaryColor(this));
//        ViewCompat.setBackgroundTintList(mPictureView, tintList);
    }


}