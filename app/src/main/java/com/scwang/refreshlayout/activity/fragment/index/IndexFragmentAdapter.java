package com.scwang.refreshlayout.activity.fragment.index;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.scwang.refreshlayout.activity.fragment.TabFragment;
import com.scwang.refreshlayout.activity.fragment.common.YuedanWebFragment;

public class IndexFragmentAdapter extends FragmentPagerAdapter {
    private String[] tabs = {"推荐", "娱乐", "技能", "相亲", "交友", "H5"};

    public IndexFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {
        if (position > 4) {
            Fragment fragment = new YuedanWebFragment();
            Bundle bundle = new Bundle();
            bundle.putString(YuedanWebFragment.KEY_URL, "http://www.sina.com.cn/");
            fragment.setArguments(bundle);
            return fragment;
        }
        return new TabFragment();
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
