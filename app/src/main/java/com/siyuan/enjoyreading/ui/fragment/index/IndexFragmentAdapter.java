package com.siyuan.enjoyreading.ui.fragment.index;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.siyuan.enjoyreading.ui.fragment.TabFragment;
import com.siyuan.enjoyreading.ui.fragment.common.YuedanWebFragment;

public class IndexFragmentAdapter extends FragmentPagerAdapter {
    private String[] tabs = {"推荐", "娱乐", "娱乐", "推荐", "娱乐", "娱乐","推荐", "娱乐", "H5"};

    public IndexFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {
        if (tabs[position] .equals("推荐")) {
            return new RecommendFragment();
        } else if (tabs[position] .equals("娱乐")) {
            return new EntertainmentFragment();
        } else {
            Fragment fragment = new YuedanWebFragment();
            Bundle bundle = new Bundle();
            bundle.putString(YuedanWebFragment.KEY_URL, "http://www.sina.com.cn/");
            fragment.setArguments(bundle);
            return fragment;
        }
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
