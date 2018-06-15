package com.scwang.refreshlayout.activity.fragment.order;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.scwang.refreshlayout.activity.fragment.TabFragment;

public class OrderFragmentAdapter extends FragmentPagerAdapter {
    private String[] tabs = {"技能管理", "需求管理"};

    public OrderFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {
        return new TabFragment();
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
