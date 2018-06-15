package com.scwang.refreshlayout.activity.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.androidapp.base.fragment.BaseTabFragment;
import com.androidapp.widget.CommonTitleBar;
import com.androidapp.smarttablayout.SmartTabLayout;
import com.scwang.refreshlayout.R;
import com.scwang.refreshlayout.activity.fragment.order.OrderFragmentAdapter;

public class OrderMainFragment extends BaseTabFragment {

    private CommonTitleBar mTitleBar;
    private ViewPager mViewPager;
    private SmartTabLayout mSmartTabLayout;

    @Override
    protected void loadData(boolean force) {
        Log.e("","--------loadData-----OrderMainFragment--------" + force);
        if (force) {
            FragmentPagerAdapter adapter = new OrderFragmentAdapter(getFragmentManager());
            mViewPager.setAdapter(adapter);
            mSmartTabLayout.setViewPager(mViewPager);
        }
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mTitleBar = (CommonTitleBar) view.findViewById(R.id.titlebar);
        mViewPager = (ViewPager) view.findViewById(R.id.order_viewpager);
        mSmartTabLayout = (SmartTabLayout) mTitleBar.getCenterCustomView().findViewById(R.id.tab_list);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_main_test1;
    }
}
