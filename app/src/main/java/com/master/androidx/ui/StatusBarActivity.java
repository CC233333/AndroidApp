package com.master.androidx.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.master.androidx.R;
import com.master.androidx.base.BaseActivity;
import com.master.promise.utils.StatusBarUtils;

public class StatusBarActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_status_bar);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        View downView = findViewById(R.id.camera);
        downView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatusBarUtils.setStatusBarColor(StatusBarActivity.this, Color.WHITE);
            }
        });

        View pictureView = findViewById(R.id.picture);
        pictureView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StatusBarUtils.setStatusBarColor(StatusBarActivity.this, Color.GRAY);
            }
        });
    }

}
