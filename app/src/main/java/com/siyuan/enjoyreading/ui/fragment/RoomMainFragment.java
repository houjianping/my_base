package com.siyuan.enjoyreading.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.androidapp.fragment.BaseTabFragment;
import com.androidapp.smarttablayout.SmartTabLayout;
import com.androidapp.utils.StatusBarUtil;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.ui.fragment.index.DiscoveryAdapter;

public class RoomMainFragment extends BaseTabFragment {

    private ViewPager mViewPager;
    private SmartTabLayout mSmartTabLayout;

    @Override
    protected void loadData(boolean force) {
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        //状态栏透明和间距处理
        StatusBarUtil.immersive(getActivity());
        mViewPager = view.findViewById(R.id.discovery_viewpager);
        mViewPager.setAdapter(new DiscoveryAdapter(getFragmentManager()));
        mSmartTabLayout = view.findViewById(R.id.discovery_viewpagertab);
        mSmartTabLayout.setViewPager(mViewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_tab_three;
    }

}
