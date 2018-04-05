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

/** 로딩 액티비티를 구현*/

public class LoadingActivity extends Activity implements View.OnClickListener {

    /**
     * 변수 선언
     * private 위주
     * */

    private ImageView mImageViewAniLoading;
    private ImageView mImageViewText;
    private Button mBtnLoadingNext;
    private Button mBtnLoadingLogin;
    private Button mBtnThemePink;
    private Button mBtnThemeBlue;
    private RelativeLayout mActivityLoading;
    private RelativeLayout mGroupLoadingWidget;
    private RelativeLayout mGroupLoadingBtnTheme;
    private GlideDrawableImageViewTarget gifImage;
    private Animation mAnimationFadeoutGif;
    private Animation mAnimationFadeoutWidget;
    private Animation mAnimationMoveYTextView;
    private Animation mAnimationFadeInWidget;
    private Animation mAnimationFadeInBtnTheme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        initData();

        mBtnLoadingLogin.setOnClickListener(this);
        mBtnLoadingNext.setOnClickListener(this);
        mBtnThemePink.setOnClickListener(this);
        mBtnThemeBlue.setOnClickListener(this);

        FadeOutGif();

        mAnimationFadeoutWidget = AnimationUtils.loadAnimation(this, R.anim.fadeout_widget);
        mAnimationFadeoutWidget.setFillAfter(true);
        mAnimationFadeoutWidget.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mBtnLoadingLogin.setVisibility(View.INVISIBLE);
                FadeInThemeButton();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void initData() {
        mActivityLoading = (RelativeLayout) findViewById(R.id.layout_activity_loading);
        mGroupLoadingBtnTheme = (RelativeLayout) findViewById(R.id.group_loading_btn_theme) ;
        mGroupLoadingWidget = (RelativeLayout) findViewById(R.id.group_loading_widget);
        mImageViewText = (ImageView) findViewById(R.id.iv_text_loading);
        mBtnLoadingLogin = (Button) findViewById(R.id.btn_loading_login);
        mBtnLoadingNext = (Button) findViewById(R.id.btn_loading_next);
        mBtnThemePink = (Button) findViewById(R.id.btn_theme_pink);
        mBtnThemeBlue = (Button) findViewById(R.id.btn_theme_blue);

        /* 이미지 뷰에 gif를 삽입하는 코드
         * implementation 'com.github.bumptech.glide:glide:3.7.0' 을 build.gradle에 포함해주어야 함.
         */
        mImageViewAniLoading = (ImageView) findViewById(R.id.iv_ani_loading);
        gifImage = new GlideDrawableImageViewTarget(mImageViewAniLoading);
        Glide.with(this).load(R.drawable.ani_loading_logo).into(gifImage);

        mAnimationMoveYTextView = AnimationUtils.loadAnimation(this, R.anim.translate);
        mAnimationFadeoutGif = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        mAnimationFadeInWidget = AnimationUtils.loadAnimation(this, R.anim.fadein);
    }

    // 페이드 아웃 메소드 + translate 애니메이션 구현
    private void FadeOutGif() {

        mAnimationMoveYTextView.setFillAfter(true);
        mAnimationFadeoutGif.setFillAfter(true);

        mAnimationFadeoutGif.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            
            @Override
            public void onAnimationEnd(Animation animation) {
                mImageViewAniLoading.setVisibility(View.INVISIBLE);
                FadeInGroupWidget();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mImageViewAniLoading.startAnimation(mAnimationFadeoutGif);
                mImageViewText.startAnimation(mAnimationMoveYTextView);
            }
        }, 5000);
    }

    private void FadeInGroupWidget() {

        mAnimationFadeInWidget.setAnimationListener(new Animation.AnimationListener() {
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
                mGroupLoadingWidget.startAnimation(mAnimationFadeInWidget);
            }
        });
    }

    private void FadeInThemeButton() {
        mAnimationFadeInBtnTheme = AnimationUtils.loadAnimation(this, R.anim.fadein_themebtn);
        mAnimationFadeInBtnTheme.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mGroupLoadingBtnTheme.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                // 페이드 인 시작
                mGroupLoadingBtnTheme.startAnimation(mAnimationFadeInBtnTheme);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_loading_login) {
            mGroupLoadingWidget.startAnimation(mAnimationFadeoutWidget);
        } else if (v.getId() == R.id.btn_loading_next) {
            Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
            startActivity(intent);
            // 이 액티비티를 닫음.
            finish();
        } else if (v.getId() == R.id.btn_theme_pink) {
            mActivityLoading.setBackgroundResource(R.color.colorPrimary_Pink);
        } else if (v.getId() == R.id.btn_theme_blue) {
            mActivityLoading.setBackgroundResource(R.color.colorPrimary_Blue);
        }
    }
}

