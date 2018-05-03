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
     * */

    private ImageView mImageViewAniLoading;
    private ImageView mImageViewText;
    private Button mBtnLoadingNext;
    private Button mBtnLoadingLogin;
    private Button mBtnThemePink;
    private Button mBtnThemeBlue;
    private Button mBtnThemeOk;
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

        // 데이터 초기화 메소드
        initData();

        // 버튼들에 setOnCLickListener 이벤트를 달아줌
        mBtnLoadingLogin.setOnClickListener(this);
        mBtnLoadingNext.setOnClickListener(this);
        mBtnThemePink.setOnClickListener(this);
        mBtnThemeBlue.setOnClickListener(this);
        mBtnThemeOk.setOnClickListener(this);

        // Gif를 페이드아웃함
        FadeOutGif();

        FadeOutGroupWidget();
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
        mBtnThemeOk = (Button) findViewById(R.id.btn_theme_ok);

        /* 이미지 뷰에 gif를 삽입하는 코드
         * implementation 'com.github.bumptech.glide:glide:3.7.0' 을 build.gradle에 포함해주어야 함.
         */
        mImageViewAniLoading = (ImageView) findViewById(R.id.iv_ani_loading);
        gifImage = new GlideDrawableImageViewTarget(mImageViewAniLoading);
        Glide.with(this).load(R.raw.ani_loading_logo).into(gifImage);

        mAnimationMoveYTextView = AnimationUtils.loadAnimation(this, R.anim.translate);
        mAnimationFadeoutGif = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        mAnimationFadeInWidget = AnimationUtils.loadAnimation(this, R.anim.fadein);
        mAnimationFadeoutWidget = AnimationUtils.loadAnimation(this, R.anim.fadeout_widget);
        mAnimationFadeInBtnTheme = AnimationUtils.loadAnimation(this, R.anim.fadein_themebtn);
    }

    // 페이드 아웃 메소드 + translate 애니메이션 구현
    private void FadeOutGif() {

        //setFillAfter(true)를 적용해주어야 애니메이션이 실행된 후에 변경사항이 적용됨.
        mAnimationMoveYTextView.setFillAfter(true);
        mAnimationFadeoutGif.setFillAfter(true);

        // mAnimationFadeoutGif에 애니메이션 리스너를 세팅한다.
        mAnimationFadeoutGif.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            
            @Override
            public void onAnimationEnd(Animation animation) {

                // 애니메이션이 끝나면 View를 INVISIBLE(안보이게) 한다.
                mImageViewAniLoading.setVisibility(View.INVISIBLE);
                //Widget들을 FadeIn하는 메소드를 호출한다.
                FadeInGroupWidget();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        // 5초 후 애니메이션을 시작함.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mImageViewAniLoading.startAnimation(mAnimationFadeoutGif);
                mImageViewText.startAnimation(mAnimationMoveYTextView);
            }
        }, 5000);
    }

    // 위젯들을 페이드 인 하는 메소드
    private void FadeInGroupWidget() {

        mAnimationFadeInWidget.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            // 애니메이션에 끝나면 mGroupLoadingWidget을 VISIBLE(보이게) 한다.
            @Override
            public void onAnimationEnd(Animation animation) {
                mGroupLoadingWidget.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        // Gif가 페이드아웃 되면 바로 시작
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                mGroupLoadingWidget.startAnimation(mAnimationFadeInWidget);
            }
        });
    }

    // Widget들을 페이드 아웃 하는 메소드
    private void FadeOutGroupWidget() {

        // setFillAfter(true)로 해주어야 애니메이션 실행 후 변경사항이 적용된다.
        mAnimationFadeoutWidget.setFillAfter(true);
        // mAnimationFadeoutWidget의 이벤트를 구성한다.
        mAnimationFadeoutWidget.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Fadeout 애니메이션이 끝나면 mBtnLoadingLogin의 상태를 INVISIBLE(안보이게) 한다.
                mBtnLoadingLogin.setVisibility(View.INVISIBLE);
                // 테마 선택 버튼을 페이드인한다.
                FadeInThemeButton();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    // 테마 선택 버튼을 페이드인하는 메소드
    private void FadeInThemeButton() {
        mAnimationFadeInBtnTheme.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            // 애니메이션이 끝나면 mGroupLoadingBtnTheme을 VISIBLE(보이게) 한다.
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
            finish();
        } else if (v.getId() == R.id.btn_theme_pink) {
            mActivityLoading.setBackgroundResource(R.color.colorPrimary_Pink);
        } else if (v.getId() == R.id.btn_theme_blue) {
            mActivityLoading.setBackgroundResource(R.color.colorPrimary_Blue);
        } else if (v.getId() == R.id.btn_theme_ok) {
            Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }
}

