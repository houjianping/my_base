package com.scwang.refreshlayout.ui.fragment.order;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.scwang.refreshlayout.ui.fragment.OrderFragment;
import com.scwang.refreshlayout.ui.fragment.TabFragment;

public class OrderFragmentAdapter extends FragmentPagerAdapter {
    private String[] tabs = {"专栏"};

    public OrderFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {
        return new OrderFragment();
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
