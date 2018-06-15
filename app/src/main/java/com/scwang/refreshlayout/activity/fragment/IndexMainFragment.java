package com.scwang.refreshlayout.activity.fragment;


import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.androidapp.base.fragment.BaseTabFragment;
import com.scwang.refreshlayout.request.OkGoRequest;
import com.androidapp.smarttablayout.SmartTabLayout;
import com.scwang.refreshlayout.R;
import com.scwang.refreshlayout.activity.fragment.index.IndexFragmentAdapter;

import java.util.HashMap;
import java.util.Map;

public class IndexMainFragment extends BaseTabFragment {

    private ViewPager mViewPager;
    private SmartTabLayout mSmartTabLayout;

    @Override
    protected void loadData(boolean force) {
        Log.e("","--------loadData-----IndexMainFragment--------" + force);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new IndexFragmentAdapter(getFragmentManager()));
        mSmartTabLayout = (SmartTabLayout) view.findViewById(R.id.viewpagertab);
        mSmartTabLayout.setViewPager(mViewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_main_test;
    }

}
