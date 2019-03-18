package com.siyuan.enjoyreading.ui.fragment.order;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class CouponFragmentAdapter extends FragmentPagerAdapter {
    private String[] tabs = {"可用券", "实效券"};

    public CouponFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {
        return new CouponFragment();
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
