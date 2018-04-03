package com.example.gigabyte.CoupleByDesigner.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.gigabyte.CoupleByDesigner.R;

public class LoadingActivity extends Activity {

    private ImageView mImageViewAniLoading;
    private ImageView mImageViewText;
    private Button mBtnLoadingNext;
    private RelativeLayout mGroupLoadingWidget;
    private GlideDrawableImageViewTarget gifImage;
    private Animation mAnimationFadeout;
    private Animation mAnimationMoveY;
    private Animation mAnimationFadeIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        mImageViewAniLoading = (ImageView) findViewById(R.id.iv_ani_loading);
        gifImage = new GlideDrawableImageViewTarget(mImageViewAniLoading);
        Glide.with(this).load(R.drawable.ani_loading_logo).into(gifImage);

        mBtnLoadingNext = (Button) findViewById(R.id.btn_loading_next);
        mBtnLoadingNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
//        Handler handler = new Handler() {
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                aniFadeout();
//                startActivity(new Intent(LoadingActivity.this, MainActivity.class));
//                finish();
//            }
//        };
//        handler.sendEmptyMessageDelayed(0, 5000);

        FadeOutAni();
    }

    private void FadeOutAni() {
        mImageViewText = (ImageView) findViewById(R.id.iv_text_loading);
        mAnimationMoveY = AnimationUtils.loadAnimation(this, R.anim.translate);
        mAnimationMoveY.setFillAfter(true);
        mAnimationFadeout = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        mAnimationFadeout.setFillAfter(true);
        mAnimationFadeout.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mImageViewAniLoading.setVisibility(View.INVISIBLE);
                mImageViewAniLoading.setAlpha(0.0f);
                FadeInEditText();
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

    private void FadeInEditText() {
        mGroupLoadingWidget = (RelativeLayout) findViewById(R.id.group_loading_widget);
        mAnimationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
        mAnimationFadeIn.setFillAfter(true);
        mAnimationFadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mGroupLoadingWidget.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                mGroupLoadingWidget.startAnimation(mAnimationFadeIn);
            }
        });
    }
}

