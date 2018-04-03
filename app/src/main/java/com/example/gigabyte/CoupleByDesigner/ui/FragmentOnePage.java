package com.example.gigabyte.CoupleByDesigner.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gigabyte.CoupleByDesigner.R;

/**
 * Created by gigabyte on 2018-03-29.
 * 메인 액티비티의 첫번째 프래그먼트
 */

public class FragmentOnePage extends Fragment {
    private String mTitle;
    private ViewPager mAdViewPager;
    private FragmentPagerAdapter mAdAdapter;
    private Fragment[] mAdFragment;

    public static FragmentOnePage getInstance() {
        FragmentOnePage mFragOnePage = new FragmentOnePage();
        mFragOnePage.mTitle = "Fragment_One_Page";
        return mFragOnePage;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fr_main_page_one, container, false);

        initData();
        mAdViewPager = view.findViewById(R.id.vp_fragment_page_one);
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

        mAdViewPager.setAdapter(mAdAdapter);

        TextView one_fragment_tv = (TextView) view.findViewById(R.id.tv_fragment_page_one);
        one_fragment_tv.setText(mTitle);

        return view;
    }

    private void initData() {
        mAdFragment = new Fragment[3];
        mAdFragment[0] = new FragmentAd1();
        mAdFragment[1] = new FragmentAd2();
        mAdFragment[2] = new FragmentAd3();
    }
}
