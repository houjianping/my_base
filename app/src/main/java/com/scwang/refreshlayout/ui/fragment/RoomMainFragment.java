package com.scwang.refreshlayout.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidapp.banner.Banner;
import com.androidapp.banner.listener.OnBannerListener;
import com.androidapp.banner.loader.ImageLoader;
import com.androidapp.adapter.BaseQuickAdapter;
import com.androidapp.adapter.BaseViewHolder;
import com.androidapp.fragment.BaseTabFragment;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnLoadMoreListener;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;
import com.androidapp.smarttablayout.SmartTabLayout;
import com.androidapp.utils.StatusBarUtil;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.refreshlayout.R;
import com.scwang.refreshlayout.ui.activity.SocializCircleActivity;
import com.scwang.refreshlayout.ui.fragment.index.DiscoveryAdapter;
import com.scwang.refreshlayout.ui.fragment.index.IndexFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class RoomMainFragment extends BaseTabFragment {

    private ViewPager mViewPager;
    private SmartTabLayout mSmartTabLayout;

    @Override
    protected void loadData(boolean force) {
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        //状态栏透明和间距处理
        StatusBarUtil.immersive(getActivity());
        mViewPager = view.findViewById(R.id.discovery_viewpager);
        mViewPager.setAdapter(new DiscoveryAdapter(getFragmentManager()));
        mSmartTabLayout = view.findViewById(R.id.discovery_viewpagertab);
        mSmartTabLayout.setViewPager(mViewPager);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_main_test3;
    }

}
