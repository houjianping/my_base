package com.androidapp.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.androidapp.base.R;
import com.androidapp.adapter.BaseQuickAdapter;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnLoadMoreListener;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;
import com.androidapp.widget.LoadingLayout;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public abstract class BaseListActivity extends BaseActivity {

    public RecyclerView mRecyclerView;
    public RefreshLayout mRefreshLayout;
    public LoadingLayout mLoadingLayout;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.base_list_activity);
    }

    @Override
    protected void initView() {
        mLoadingLayout = (LoadingLayout) findViewById(R.id.loading);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRefreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this, VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (getListViewAdapter() != null) {
            mRecyclerView.setAdapter(getListViewAdapter());
        }
        if (getOnRefreshListener() != null) {
            mRefreshLayout.setOnRefreshListener(getOnRefreshListener());
        }
        if (getOnLoadMoreListener() != null) {
            mRefreshLayout.setOnLoadMoreListener(getOnLoadMoreListener());
        }
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
    }

    /**
     * 下拉刷新回调
     * @return
     */
    protected abstract OnRefreshListener getOnRefreshListener();

    /**
     * 加载更多回调
     * @return
     */
    protected abstract OnLoadMoreListener getOnLoadMoreListener();

    /**
     * 数据源
     * @return
     */
    protected abstract BaseQuickAdapter getListViewAdapter();

    /**
     * 添加列表头部视图
     * @param headerView 视图
     * @param headerIndex 顶部层级 0 1 2 3
     */
    protected void addHeaderView(View headerView, int headerIndex) {
        if (getListViewAdapter() != null) {
            getListViewAdapter().addHeaderView(headerView, headerIndex);
        }
    };

    /**
     * 添加列表底部视图
     * @param footerView 视图
     * @param viewIndex 层级 0 1 2 3
     */
    protected void addFooterView(View footerView, int viewIndex) {
        if (getListViewAdapter() != null) {
            getListViewAdapter().addFooterView(footerView, viewIndex);
        }
    }
}
