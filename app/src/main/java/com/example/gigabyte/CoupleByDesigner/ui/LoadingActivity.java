package com.example.gigabyte.CoupleByDesigner.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.gigabyte.CoupleByDesigner.R;

public class LoadingActivity extends Activity {

    private ImageView mImageViewAniLoading;
    private ImageView mImageViewText;
    private GlideDrawableImageViewTarget gifImage;
    private Animation mAnimationFadeout;
    private Animation mAnimationMoveY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        mImageViewAniLoading = (ImageView) findViewById(R.id.iv_ani_loading);
        gifImage = new GlideDrawableImageViewTarget(mImageViewAniLoading);
        Glide.with(this).load(R.drawable.ani_loading_logo).into(gifImage);
//        Handler handler = new Handler() {
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                aniFadeout();
//                startActivity(new Intent(LoadingActivity.this, MainActivity.class));
//                finish();
//            }
//        };
//        handler.sendEmptyMessageDelayed(0, 5000);

        aniFadeout();
    }

    private void aniFadeout() {
//        mImageViewAniLoading.setAlpha(1.0f);
        mImageViewText = (ImageView) findViewById(R.id.iv_text_loading);
        mAnimationMoveY = AnimationUtils.loadAnimation(this, R.anim.translate);
        mAnimationFadeout = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        mAnimationFadeout.setFillAfter(true);
        mAnimationMoveY.setFillAfter(true);
        mAnimationFadeout.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mImageViewAniLoading.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mImageViewAniLoading.startAnimation(mAnimationFadeout);
                mImageViewText.startAnimation(mAnimationMoveY);
                }
        }, 5000);
    }
}
