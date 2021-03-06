package com.siyuan.enjoyreading.ui.coll;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.androidapp.activity.BaseActivity;
import com.androidapp.utils.StatusBarUtil;
import com.androidapp.utils.ToastUtils;
import com.androidapp.widget.AppSwipeRefreshLayout;
import com.androidapp.widget.AppViewPager;
import com.androidapp.filter.FilterHeaderItem;
import com.androidapp.filter.FilterView;
import com.androidapp.filter.multiple.bean.MultiBean;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.ui.fragment.base.ListFragment;

import java.util.ArrayList;
import java.util.List;

public class ActCollapsingToolBar extends BaseActivity {
    AppBarLayout mAppBarLayout;

    Bitmap bitmap;

    AppViewPager mViewPager;

    ListFragment mFragment1;

    ListFragment mFragment2;

    ListFragment mFragment3;

    PagerAdapter mPagerAdapter;

    private LinearLayout head_layout;

    private TabLayout toolbar_tab;

    private List<ListFragment> mFragments = new ArrayList<ListFragment>();

    private List<MultiBean> dictList = new ArrayList<>();

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.act_collapsingtoolbar);
    }

    @Override
    protected void initView() {
        final AppSwipeRefreshLayout swipeRefreshLayout = findViewById(R.id.swipe_refresh_widget);
        mAppBarLayout = findViewById(R.id.app_bar_layout);
        mViewPager = findViewById(R.id.view_pager);
        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_share_qq);
        mViewPager.setOffscreenPageLimit(2);
        head_layout = findViewById(R.id.login_layout);
        toolbar_tab = findViewById(R.id.toolbar_tab);
        head_layout.setBackgroundDrawable(new BitmapDrawable(bitmap));
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset >= 0) {
                    swipeRefreshLayout.setEnabled(true);
                } else {
                    swipeRefreshLayout.setEnabled(false);
                }
            }
        });
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        initFragment();
    }

    @Override
    protected void initData() {

    }


    private void initFragment() {
        if (mFragment1 == null) {
            mFragment1 = new ListFragment();
        }
        if (mFragment2 == null) {
            mFragment2 = new ListFragment();
        }
        if (mFragment3 == null) {
            mFragment3 = new ListFragment();
        }
        mFragments.add(mFragment1);
        mFragments.add(mFragment2);
        mFragments.add(mFragment3);
        mPagerAdapter = new PagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(toolbar_tab));
        toolbar_tab.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
    }

    public class PagerAdapter extends FragmentPagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return mFragment1;
            } else if (position == 1) {
                return mFragment2;
            } else if (position == 2) {
                return mFragment3;
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
