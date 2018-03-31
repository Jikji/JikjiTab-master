package com.example.gigabyte.CoupleByDesigner.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gigabyte.CoupleByDesigner.R;

/**
 * Created by Park on 2018-03-31.
 */

public class FragmentAd1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fr_ad_page_one, container, false);

        return view;
    }
}
