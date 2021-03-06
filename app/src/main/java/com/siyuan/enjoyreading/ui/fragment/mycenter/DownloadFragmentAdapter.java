package com.siyuan.enjoyreading.ui.fragment.mycenter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.siyuan.enjoyreading.ui.fragment.index.EntertainmentFragment;

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
        return new EntertainmentFragment();
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
