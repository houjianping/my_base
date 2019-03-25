package com.siyuan.enjoyreading.ui.fragment.discovery;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.androidapp.adapter.BaseQuickAdapter;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnLoadMoreListener;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.api.ApiConfig;
import com.siyuan.enjoyreading.entity.circle.CircleItem;
import com.siyuan.enjoyreading.ui.fragment.base.ViewPagerBaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import ezy.ui.layout.LoadingLayout;

public class SocializCircleFragment extends ViewPagerBaseFragment {
    private RecyclerView mRecyclerView;
    private MultipleItemQuickAdapter mAdapter;
    private LoadingLayout mLoadingLayout;

    @Override
    protected void loadData(boolean force) {
        mLoadingLayout.showContent();
        final List<CircleItem> circleItems = new Gson().fromJson(ApiConfig.JSON_ZONE_LIST, new TypeToken<ArrayList<CircleItem>>() {
        }.getType());
        for (int i = 0; i < circleItems.size(); i++) {
            circleItems.get(i).setPictures(ApiConfig.getRandomPhotoUrlString(new Random().nextInt(9)));
        }
        mAdapter.replaceData(circleItems);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mLoadingLayout = view.findViewById(com.androidapp.base.R.id.loading);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        final RefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayout.VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.finishRefresh();
                    }
                }, 2000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        });
        if (mAdapter == null) {
            mAdapter = new MultipleItemQuickAdapter();
            mAdapter.openLoadAnimation();
            mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                }
            });
        }
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_layout;
    }
}
