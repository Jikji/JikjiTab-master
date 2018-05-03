package com.example.gigabyte.CoupleByDesigner.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.gigabyte.CoupleByDesigner.R;

import java.util.HashMap;

/**
 * Created by gigabyte on 2018-03-29.
 * 메인 액티비티의 첫번째 프래그먼트
 */

public class FragmentOnePage extends Fragment implements View.OnClickListener
        , ViewPagerEx.OnPageChangeListener, BaseSliderView.OnSliderClickListener {


    /**
     *  변수 선언
     */
    private SliderLayout mSliderLayout;
    private int[] mImageButtonID = { R.id.ib_home_best, R.id.ib_home_sale, R.id.ib_home_md };
    private ImageButton imageButton;
    private View mRootView;
    private static Context mContext;

    // FragmentOnePage 생성자. 이렇게 만들면 객체를 한번 만들어두기 때문에 메모리 효율이 좋아진다.
    public static FragmentOnePage getInstance(Context context) {
        mContext = context;
        FragmentOnePage mFragOnePage = new FragmentOnePage();
        return mFragOnePage;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        // fr_main_page_one.xml을 인플레이터하여 mRootView에 전달
        mRootView = inflater.inflate(R.layout.fr_main_page_one, container, false);

        // 각종 변수 초기화
        initData();
        // 광고 스타트
        settingSliderLayout();

        // mRootView를 반환하여 화면에 뿌릴 수 있다.
        return mRootView;
    }

    private void initData() {

        // 반복문을 이용한 imageButton에 setOnClickListener 달아주기.
        for (int id : mImageButtonID) {
            imageButton = mRootView.findViewById(id);
            imageButton.setOnClickListener(this);
        }

        // mSliderLayout에 sliderlayout_ad를 바인딩
        mSliderLayout = mRootView.findViewById(R.id.sliderlayout_ad);
    }

    // 광고 이미지 슬라이더 라이브러리 메소드
    private void settingSliderLayout() {

        HashMap<String,Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("1",R.drawable.image_one);
        file_maps.put("2",R.drawable.image_two);
        file_maps.put("3",R.drawable.image_three);

        for(String name : file_maps.keySet()){
            DefaultSliderView defaultSliderView = new DefaultSliderView(mContext);
            // initialize a SliderLayout
            defaultSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            defaultSliderView.bundle(new Bundle());
            defaultSliderView.getBundle()
                    .putString("extra",name);

            mSliderLayout.addSlider(defaultSliderView);
        }

        mSliderLayout.setPresetTransformer(SliderLayout.Transformer.Default);
        mSliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSliderLayout.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Visible);
        mSliderLayout.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.ib_home_best) {
            HomeGroupActivity.mPositionStartTab = 0;
            Intent intent = new Intent(getActivity(), HomeGroupActivity.class);
            startActivity(intent);
            Toast.makeText(getActivity(), "HomeButton 클릭", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.ib_home_sale) {
            HomeGroupActivity.mPositionStartTab = 1;
            Intent intent = new Intent(getActivity(), HomeGroupActivity.class);
            startActivity(intent);
            Toast.makeText(getActivity(), "SaleButton 클릭", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.ib_home_md) {
            HomeGroupActivity.mPositionStartTab = 2;
            Intent intent = new Intent(getActivity(), HomeGroupActivity.class);
            startActivity(intent);
            Toast.makeText(getActivity(), "MdButton 클릭", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
