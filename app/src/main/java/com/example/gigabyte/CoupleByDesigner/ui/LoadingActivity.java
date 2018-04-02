package com.example.gigabyte.CoupleByDesigner.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.gigabyte.CoupleByDesigner.R;

public class LoadingActivity extends Activity {

    private ImageView mImageViewAniLoading;
    private GlideDrawableImageViewTarget gifImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        mImageViewAniLoading = (ImageView) findViewById(R.id.iv_ani_loading);
        gifImage = new GlideDrawableImageViewTarget(mImageViewAniLoading);
        Glide.with(this).load(R.drawable.ani_loading_logo).into(gifImage);
        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                startActivity(new Intent(LoadingActivity.this, MainActivity.class));
                finish();
            }
        };
        handler.sendEmptyMessageDelayed(0, 5000);
    }
}
