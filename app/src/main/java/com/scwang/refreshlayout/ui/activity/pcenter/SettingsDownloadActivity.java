package com.scwang.refreshlayout.ui.activity.pcenter;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.androidapp.activity.BaseActivity;
import com.androidapp.fragment.BaseTabFragment;
import com.androidapp.smarttablayout.SmartTabLayout;
import com.androidapp.widget.CommonTitleBar;
import com.scwang.refreshlayout.R;
import com.scwang.refreshlayout.ui.fragment.mycenter.DownloadFragmentAdapter;
import com.scwang.refreshlayout.ui.fragment.order.OrderFragmentAdapter;

public class SettingsDownloadActivity extends BaseActivity {

    private CommonTitleBar mTitleBar;
    private ViewPager mViewPager;
    private SmartTabLayout mSmartTabLayout;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.personal_donwload);
    }

    @Override
    protected void initView() {
        mTitleBar = findViewById(R.id.titlebar);
        mViewPager = findViewById(R.id.order_viewpager);
        mSmartTabLayout = mTitleBar.getCenterCustomView().findViewById(R.id.tab_list);
    }

    @Override
    protected void initData() {
        DownloadFragmentAdapter adapter = new DownloadFragmentAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mSmartTabLayout.setViewPager(mViewPager);
    }
}
