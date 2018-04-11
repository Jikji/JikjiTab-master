package com.example.gigabyte.CoupleByDesigner.ui;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.gigabyte.CoupleByDesigner.Adapter.AutoScrollViewPager;
import com.example.gigabyte.CoupleByDesigner.Adapter.InfinitePagerAdapter;
import com.example.gigabyte.CoupleByDesigner.R;

/**
 * Created by gigabyte on 2018-03-29.
 * 메인 액티비티의 첫번째 프래그먼트
 */

public class FragmentOnePage extends Fragment implements View.OnClickListener {

    private AutoScrollViewPager mAdViewPager;
    private FragmentPagerAdapter mAdAdapter;
    private Fragment[] mAdFragment;
    private int[] mImageButtonID = {R.id.ib_home_best, R.id.ib_home_best1, R.id.ib_home_best2
            , R.id.ib_home_best3, R.id.ib_home_sale, R.id.ib_home_sale1, R.id.ib_home_sale2
            , R.id.ib_home_sale3, R.id.ib_home_md, R.id.ib_home_md1, R.id.ib_home_md2
            , R.id.ib_home_md3};
    private ImageButton imageButton;
    private View mRootView;

    public static FragmentOnePage getInstance() {
        FragmentOnePage mFragOnePage = new FragmentOnePage();
        return mFragOnePage;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mRootView = inflater.inflate(R.layout.fr_main_page_one, container, false);

        initData();
        mAdViewPager = mRootView.findViewById(R.id.vp_fragment_page_one);
        mAdAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                if (mAdFragment != null && position < mAdFragment.length) {
                    return mAdFragment[position];
                } else {
                    return null;
                }
            }

            @Override
            public int getCount() {
                if (mAdFragment != null) {
                    return mAdFragment.length;
                } else {
                    return 0;
                }
            }
        };

        mAdViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        /* 광고 뷰 페이저 구현 */

        PagerAdapter infinitePagerAdapter = new InfinitePagerAdapter(mAdAdapter);
        mAdViewPager.setAdapter(infinitePagerAdapter);
        mAdViewPager.setAutoScrollDurationFactor(3);
        mAdViewPager.setSwipeScrollDurationFactor(3);
        mAdViewPager.setInterval(3000);
        mAdViewPager.setCycle(true);
        mAdViewPager.startAutoScroll();

        return mRootView;
    }

    private void initData() {
        mAdFragment = new Fragment[3];
        mAdFragment[0] = new FragmentAd1();
        mAdFragment[1] = new FragmentAd2();
        mAdFragment[2] = new FragmentAd3();

        for (int id : mImageButtonID) {
            imageButton = mRootView.findViewById(id);
            imageButton.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ib_home_best1 || v.getId() == R.id.ib_home_best2 || v.getId() == R.id.ib_home_best3) {
            Toast.makeText(getActivity(), "bestButton 구현", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.ib_home_sale1) {
            Toast.makeText(getActivity(), "saleButton 구현", Toast.LENGTH_SHORT).show();
        }
    }
}
