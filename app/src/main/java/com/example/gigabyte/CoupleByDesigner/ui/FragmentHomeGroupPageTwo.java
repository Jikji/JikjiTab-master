package com.example.gigabyte.CoupleByDesigner.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gigabyte.CoupleByDesigner.Adapter.RecyclerViewHomeAdapter;
import com.example.gigabyte.CoupleByDesigner.Item.RecyclerViewHomeItem;
import com.example.gigabyte.CoupleByDesigner.R;

import java.util.ArrayList;

public class FragmentHomeGroupPageTwo extends Fragment {

    private static Context mContext;
    private static View mRootView;
    private RecyclerView mRecyclerView;
    private RecyclerViewHomeAdapter mRecyclerViewAdapter;
    LinearLayoutManager mLinearLayoutManager;
    ArrayList<RecyclerViewHomeItem> mItems = new ArrayList<>();

    public static FragmentHomeGroupPageTwo getInstance(Context context) {
        mContext = context;
        FragmentHomeGroupPageTwo mFragmentHomeGroupPageTwo = new FragmentHomeGroupPageTwo();
        return mFragmentHomeGroupPageTwo;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fr_home_group_page_two, container, false);

        settingRecyclerView();
        initData();

        return mRootView;
    }

    private void settingRecyclerView() {
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recyclerview_home_group_sale);
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerViewAdapter = new RecyclerViewHomeAdapter(mItems);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
    }

    private void initData() {
        mItems.add(new RecyclerViewHomeItem());
        mItems.add(new RecyclerViewHomeItem());
        mItems.add(new RecyclerViewHomeItem());

        mRecyclerViewAdapter.notifyDataSetChanged();
    }
}
