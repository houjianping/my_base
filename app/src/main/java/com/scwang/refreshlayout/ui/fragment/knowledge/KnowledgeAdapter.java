package com.scwang.refreshlayout.ui.fragment.knowledge;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.scwang.refreshlayout.ui.fragment.common.YuedanWebFragment;

public class KnowledgeAdapter extends FragmentPagerAdapter {
    private String[] tabs = {"详情", "目录"};

    public KnowledgeAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new KnowledgeDescFragment();
            case 1:
                return new KnowledgeListFragment();
            default:
                Fragment fragment = new YuedanWebFragment();
                Bundle bundle = new Bundle();
                bundle.putString(YuedanWebFragment.KEY_URL, "http://m.lrts.me/h5/appactivity?uid=252995147&mparam=0s34Of5C32xrw7YCOrk1qnLcvSwtmfTIFCFP3YV38QKEyVc71soBDCET0OKwantG1gfqqrUSXcWB%0Aa9C8hoUluyvkwHBloCUH");
                fragment.setArguments(bundle);
                return fragment;
        }
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}