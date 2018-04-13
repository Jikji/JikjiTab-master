package com.example.gigabyte.CoupleByDesigner.Adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class CircleAnimIndicator extends LinearLayout {

    private Context mContext;
    private int mItemMargin = 10;
    private int mAnimDuration = 250;
    private int mDefaultCircle;
    private int mSelectCircle;
    private ImageView[] mImageDots;

    public CircleAnimIndicator(Context context) {

        super(context);

        mContext = context;
    }

    public CircleAnimIndicator(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);

        mContext = context;
    }

    public void setmAnimDuration(int mAnimDuration) {
        this.mAnimDuration = mAnimDuration;
    }

    public void setmItemMargin(int mItemMargin) {
        this.mItemMargin = mItemMargin;
    }

    public void createDotPanel(int count, int defaultCircle, int selectCircle) {

        mDefaultCircle = defaultCircle;
        mSelectCircle = selectCircle;

        mImageDots = new ImageView[count];

        for (int i = 0; i < count; i++) {

            mImageDots[i] = new ImageView(mContext);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                    (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.topMargin = mItemMargin;
            params.bottomMargin = mItemMargin;
            params.leftMargin = mItemMargin;
            params.rightMargin = mItemMargin;
            params.gravity = Gravity.CENTER;

            mImageDots[i].setLayoutParams(params);
            mImageDots[i].setImageResource(defaultCircle);
            mImageDots[i].setTag(mImageDots[i].getId(), false);
            this.addView(mImageDots[i]);
        }

        //첫인덱스 선택
        selectDot(0);
    }

    public void selectDot(int position) {

        for (int i = 0; i < mImageDots.length; i++) {
            if (i == position) {
                mImageDots[i].setImageResource(mSelectCircle);
                selectScaleAnim(mImageDots[i], 1f, 1.5f);
            } else {

                if ((boolean) mImageDots[i].getTag(mImageDots[i].getId()) == true) {
                    mImageDots[i].setImageResource(mDefaultCircle);
                    defaultScaleAnim(mImageDots[i], 1.5f, 1f);
                }
            }
        }
    }

    public void selectScaleAnim(View view, float startScale, float endScale) {
        Animation anim = new ScaleAnimation(
                startScale, endScale,
                startScale, endScale,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setFillAfter(true);
        anim.setDuration(mAnimDuration);
        view.startAnimation(anim);
        view.setTag(view.getId(), true);
    }

    public void defaultScaleAnim(View view, float startScale, float endScale) {
        Animation anim = new ScaleAnimation(
                startScale, endScale,
                startScale, endScale,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setFillAfter(true);
        anim.setDuration(mAnimDuration);
        view.startAnimation(anim);
        view.setTag(view.getId(), false);
    }
}
