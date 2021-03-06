package com.siyuan.enjoyreading.ui.fragment.index;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.siyuan.enjoyreading.ui.fragment.common.AppWebFragment;
import com.siyuan.enjoyreading.ui.fragment.discovery.SocializCircleFragment;
import com.siyuan.enjoyreading.ui.fragment.discovery.VideoListFragment;

public class DiscoveryAdapter extends FragmentPagerAdapter {
    private String[] tabs = {"读友", "小视频", "福利", "活动"};

    public DiscoveryAdapter(FragmentManager fm) {
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
                return new SocializCircleFragment();
            case 1:
                return new VideoListFragment();
            case 2:
                Fragment fragment = new AppWebFragment();
                Bundle bundle = new Bundle();
                bundle.putString(AppWebFragment.KEY_URL, "http://m.lrts.me/h5/appactivity?uid=252995147&mparam=0s34Of5C32xrw7YCOrk1qnLcvSwtmfTIFCFP3YV38QKEyVc71soBDCET0OKwantG1gfqqrUSXcWB%0Aa9C8hoUluyvkwHBloCUH");
                fragment.setArguments(bundle);
                return fragment;
            default:
                Fragment activeFragment = new AppWebFragment();
                Bundle activebundle = new Bundle();
                activebundle.putString(AppWebFragment.KEY_URL, "http://m.lrts.me/h5/appactivity?uid=252995147&mparam=0s34Of5C32xrw7YCOrk1qnLcvSwtmfTIFCFP3YV38QKEyVc71soBDCET0OKwantG1gfqqrUSXcWB%0Aa9C8hoUluyvkwHBloCUH");
                activeFragment.setArguments(activebundle);
                return activeFragment;
        }
    }

    @Override
    public int getCount() {
        return tabs.length;
    }
}
