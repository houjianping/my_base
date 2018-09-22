package com.scwang.refreshlayout.ui.fragment.mycenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.scwang.refreshlayout.ui.fragment.TabFragment;

public class DownloadFragmentAdapter extends FragmentPagerAdapter {
    private String[] tabs = {"已下载", "下载中"};

    public DownloadFragmentAdapter(FragmentManager fm) {
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
