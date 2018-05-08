package com.example.gigabyte.CoupleByDesigner.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.example.gigabyte.CoupleByDesigner.R;
import com.example.gigabyte.CoupleByDesigner.entity.TabEntity;
import com.example.gigabyte.CoupleByDesigner.utils.ViewFindUtils;

import java.util.ArrayList;

/**
 * 메인 화면
 */

public class MainActivity extends AppCompatActivity implements OnTabSelectListener {

    /**
     * 변수 선언
     */

    // 현재 컨텍스트를 자기 자신으로 선언
    private Context mContext = this;
    // 페이지 전환을 위한 프래그먼트 ArrayList 생성
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    // 커스텀 탭 인터페이스 ArrayList 생성
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    // SlidingTabLayout (라이브러리) 객체 생성
    private SlidingTabLayout mTabLayout;

    // 탭 제목 배열
    private final String[] mTitles = {
            "HOME", "이벤트 & 기획", "에디터 TIP"
            , "유저 게시판", "제휴 문의"
    };

    // 메인 화면 프래그먼트 전환을 위한 페이저 어댑터 생성
    private MainPagerAdapter mAdapter;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 각종 데이터 초기화 (탭, 프래그먼트를 초기화함)
        initData();

        // 현재 윈도우의 DecorView를 View 객채인 decorView에 저장한다.
        View mDecorView = getWindow().getDecorView();
        /*
        ViewFindUtils의 find메소드로 mDecorView와 뷰페이저의 id를 넘긴다.
        ViewFindUtils.java에는 CustomTabLayout 라이브러리 제작자가 findViewById를 쉽게 사용하기 위해
        findViewById를 재정의하였다.
         */
        mViewPager = ViewFindUtils.find(mDecorView, R.id.vp_main_page);
        // mAdapter에 프래그먼트 전환이 가능하도록 세팅해준다.
        mAdapter = new MainPagerAdapter(getSupportFragmentManager());
        // 전환이 가능해진 어댑터를 ViewPager 객체의 mViewPager에 달아준다.
        mViewPager.setAdapter(mAdapter);
        // activity_main.xml에 있는 탭 레이아웃을 mTabLayout이랑 바인딩한다.
        mTabLayout = ViewFindUtils.find(mDecorView, R.id.mTabLayout);
        // mTabLayout에 뷰페이저를 세팅한다. mtitles 리스트랑 mTabEntities 리스트를 넘겨줌.
        mTabLayout.setViewPager(mViewPager, mTitles, mTabEntities);
        // 뷰 페이저 시작 화면을 0페이지로 설정,
        mViewPager.setCurrentItem(0);
    }

    @Override
    public void onTabSelect(int position) {
        // 탭을 클릭했을 때 구현해줄 부분같음
    }

    @Override
    public void onTabReselect(int position) {
        // 탭을 다시 클릭했을 때 구현해줄 부분같음
    }

    private class MainPagerAdapter extends FragmentPagerAdapter {

        // 기본적으로 만들어지는 생성자
        public MainPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        // 슬라이드 시킬 프래그먼트 갯수
        @Override
        public int getCount() {
            //  mFragments에 들어간 아이템 수 만큼 반환
            return mFragments.size();
        }

        // 프래그먼트에 표시할 문자열 지정
        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        // 해당하는 포지션에 있는 프래그먼트를 가져와 반환.
        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    private void initData() {
        // 반복문을 돌려서 mFragment 리스트와 mTabEntities 리스트에 아이템을 추가해준다.
        for (int i = 0; i < mTitles.length; i++) {

            /* new 키워드를 통해서 TabEntity 객체를 계속 생성한다. 객체가 생성될 때마다 호출되는
            생성자에 매개변수로 mTitles[i], mIconSelectId[i], mIconUnselected[i]를 전달한다.
            그러면 각 TabEntity 객체에 전달받아 TabEntity안에 있는 변수에 저장한다. */
            mTabEntities.add(new TabEntity(mTitles[i]));
        }

        /* mFragments에 만들어둔 프래그먼트를 추가한다. */
        mFragments.add(FragmentOnePage.getInstance(this));
        mFragments.add(SimpleCardFragment.getInstance(mTitles[1]));
        mFragments.add(SimpleCardFragment.getInstance(mTitles[2]));
        mFragments.add(SimpleCardFragment.getInstance(mTitles[3]));
        mFragments.add(SimpleCardFragment.getInstance(mTitles[4]));
    }
}
