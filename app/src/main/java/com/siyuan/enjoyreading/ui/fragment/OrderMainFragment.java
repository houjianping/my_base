package com.siyuan.enjoyreading.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.androidapp.fragment.BaseTabFragment;
import com.androidapp.smarttablayout.SmartTabLayout;
import com.androidapp.widget.CommonTitleBar;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.ui.fragment.order.OrderFragmentAdapter;

public class OrderMainFragment extends BaseTabFragment {

    private CommonTitleBar mTitleBar;
    private ViewPager mViewPager;
    private SmartTabLayout mSmartTabLayout;

    @Override
    protected void loadData(boolean force) {
        Log.e("", "--------loadData-----OrderMainFragment--------" + force);
        if (force) {
            FragmentPagerAdapter adapter = new OrderFragmentAdapter(getFragmentManager());
            mViewPager.setAdapter(adapter);
            mSmartTabLayout.setViewPager(mViewPager);
        }
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mTitleBar = view.findViewById(R.id.titlebar);
        mViewPager = view.findViewById(R.id.order_viewpager);
        mSmartTabLayout = mTitleBar.getCenterCustomView().findViewById(R.id.tab_list);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_main_test1;
    }
}