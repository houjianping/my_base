package com.siyuan.enjoyreading.ui.fragment.index;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.siyuan.enjoyreading.ui.fragment.common.AppWebFragment;

public class IndexFragmentAdapter extends FragmentPagerAdapter {
    private String[] tabs = {"推荐", "热门", "最新", "H5"};

    public IndexFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (tabs[position]) {
            case "推荐":
                return new RecommendFragment();
            case "H5":
                Fragment fragment = new AppWebFragment();
                Bundle bundle = new Bundle();
                bundle.putString(AppWebFragment.KEY_URL, "http://www.sina.com.cn/");
                fragment.setArguments(bundle);
                return fragment;
            default:
                return new EntertainmentFragment();
        }
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
