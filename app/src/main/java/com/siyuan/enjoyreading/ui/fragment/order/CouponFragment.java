package com.siyuan.enjoyreading.ui.fragment.order;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.androidapp.fragment.LazyLoadFragment;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnLoadMoreListener;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.api.ApiConfig;
import com.siyuan.enjoyreading.entity.Coupon;

import java.util.ArrayList;
import java.util.List;

public class CouponFragment extends LazyLoadFragment {

    private RecyclerView mRecyclerView;
    private RefreshLayout mRefreshLayout;
    private MultipleItemQuickAdapter mAdapter;
    final List<Coupon> coupons = new Gson().fromJson(ApiConfig.JSON_MOVIES, new TypeToken<ArrayList<Coupon>>() {
    }.getType());

    @Override
    protected void loadData(boolean force) {
        Log.e("", "------loadData--------" + force);
        if (force) {
            mAdapter.replaceData(coupons);
        }
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mRecyclerView = view.findViewById(R.id.recyclerView);
        mRefreshLayout = view.findViewById(R.id.refreshLayout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (mAdapter == null) {
            mAdapter = new MultipleItemQuickAdapter();
            mAdapter.openLoadAnimation();
        }
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mAdapter.getItemCount() < 2) {
                            List<Coupon> coupons = new Gson().fromJson(ApiConfig.JSON_MOVIES, new TypeToken<ArrayList<Coupon>>() {
                            }.getType());
                            mAdapter.replaceData(coupons);
                        }
                        refreshLayout.finishRefresh();
                    }
                }, 2000);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mAdapter.addData(coupons);
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab;
    }
}
