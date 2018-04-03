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

public class LoadingActivity extends Activity {

    /**
     * 변수 선언
     * private 위주
     * */

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

        /* 이미지 뷰에 gif를 삽입하는 코드
         * implementation 'com.github.bumptech.glide:glide:3.7.0' 을 build.gradle에 포함해주어야 함.
         */
        mImageViewAniLoading = (ImageView) findViewById(R.id.iv_ani_loading);
        gifImage = new GlideDrawableImageViewTarget(mImageViewAniLoading);
        Glide.with(this).load(R.drawable.ani_loading_logo).into(gifImage);

        // '다음에 할게요 '버튼 클릭시 MainActivity로 넘어가는 이벤트를 발생시킨다.
        mBtnLoadingNext = (Button) findViewById(R.id.btn_loading_next);
        mBtnLoadingNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 클릭 시 넘어감.
                Intent intent = new Intent(LoadingActivity.this, MainActivity.class);
                startActivity(intent);
                // 이 액티비티를 닫음.
                finish();
            }
        });

        // 5초간 로딩 후에 페이드 아웃한다.
        FadeOutAni();
    }

    // 페이드 아웃 메소드 + translate 애니메이션 구현
    private void FadeOutAni() {
        // ImageView 연결
        mImageViewText = (ImageView) findViewById(R.id.iv_text_loading);
        // XML에서 짜둔 애니메이션이랑 Animation객체를 연결, 여기에서는 translate
        mAnimationMoveY = AnimationUtils.loadAnimation(this, R.anim.translate);
        // setFillAfter(true)를 해줘야 애니메이션이 끝난 후에 원래로 돌아가지 않고 그대로 둠.
        mAnimationMoveY.setFillAfter(true);
        // Animation객체랑 fadeout.xml를 연결
        mAnimationFadeout = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        mAnimationFadeout.setFillAfter(true);
        mAnimationFadeout.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            
            // FadeOut 애니메이션이 끝나면 gif를 사라지게 한다. 아래 값을 무조건 넣어줘야댐.
            @Override
            public void onAnimationEnd(Animation animation) {
                mImageViewAniLoading.setVisibility(View.INVISIBLE);
                mImageViewAniLoading.setAlpha(0.0f);
                // gif 이미지가 사라지면 FadeInGroupWidget메소드 실행
                FadeinGroupWidget();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        
        // 5초 후 페이드 아웃 시작
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mImageViewAniLoading.startAnimation(mAnimationFadeout);
                mImageViewText.startAnimation(mAnimationMoveY);
            }
        }, 5000);
    }

    // 위젯을 페이드 인 해줄 메소드 구현
    private void FadeinGroupWidget() {
        mGroupLoadingWidget = (RelativeLayout) findViewById(R.id.group_loading_widget);
        mAnimationFadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);
        mAnimationFadeIn.setFillAfter(true);
        mAnimationFadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            // 페이드 인 하면 위젯들이 들어가있는 mGroupLoadingWidget(ReleativeLayout임)을 visible해줌.
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
                // 페이드 인 시작
                mGroupLoadingWidget.startAnimation(mAnimationFadeIn);
            }
        });
    }
}

