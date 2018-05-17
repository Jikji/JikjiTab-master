package com.example.gigabyte.CoupleByDesigner.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

import com.example.gigabyte.CoupleByDesigner.R;
import com.example.gigabyte.CoupleByDesigner.entity.TabEntity;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;

public class HomeGroupActivity extends AppCompatActivity {

    private String[] mTitles = {"Best", "할인&특가", "MD추천"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private CommonTabLayout mCommonTabLayout;
    private ViewPager mViewPager;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private HomeGroupPagerAdapter mAdapter;
    public static int mPositionStartTab = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_group);

        initData();
        settingViewPager();

        mCommonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCommonTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_home_group);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ActionBar ab = getSupportActionBar();

        ab.setDisplayShowCustomEnabled(true);
        ab.setDisplayHomeAsUpEnabled(false);
        ab.setDisplayShowTitleEnabled(false);
        ab.setDisplayShowHomeEnabled(false);

        LayoutInflater layoutInflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View actionbar = layoutInflater.inflate(R.layout.custom_actionbar, null);

        ab.setCustomView(actionbar);

        Toolbar parent = (Toolbar)actionbar.getParent();
        parent.setContentInsetsAbsolute(0,0);

        return true;
    }

    private class HomeGroupPagerAdapter extends FragmentPagerAdapter {
        public HomeGroupPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    private void initData() {
        mFragments.add(FragmentHomeGroupPageOne.getInstance(this));
        mFragments.add(FragmentHomeGroupPageTwo.getInstance(this));
        mFragments.add(FragmentHomeGroupPageThree.getInstance(this));

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i]));
        }
    }

    private void settingViewPager() {

        mCommonTabLayout = (CommonTabLayout) findViewById(R.id.tablayout_home_group);
        mCommonTabLayout.setTabData(mTabEntities);
        mViewPager = (ViewPager) findViewById(R.id.vp_group);
        mAdapter = new HomeGroupPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mCommonTabLayout.setCurrentTab(mPositionStartTab);
        mViewPager.setCurrentItem(mPositionStartTab);
    }
}
