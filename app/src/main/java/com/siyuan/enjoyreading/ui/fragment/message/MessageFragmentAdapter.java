package com.siyuan.enjoyreading.ui.fragment.message;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.siyuan.enjoyreading.ui.fragment.TabFragment;

public class MessageFragmentAdapter extends FragmentPagerAdapter {
    private String[] tabs = {"消息", "通知"};

    public MessageFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {
        return new NotificationFragment();
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
