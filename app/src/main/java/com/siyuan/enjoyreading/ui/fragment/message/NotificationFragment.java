package com.siyuan.enjoyreading.ui.fragment.message;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.androidapp.adapter.BaseQuickAdapter;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnLoadMoreListener;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;
import com.androidapp.widget.LoadingLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.api.ApiConfig;
import com.siyuan.enjoyreading.entity.NotificationItem;
import com.siyuan.enjoyreading.ui.activity.VideoListActivity;
import com.siyuan.enjoyreading.ui.fragment.base.ViewPagerBaseFragment;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class NotificationFragment extends ViewPagerBaseFragment {

    final List<NotificationItem> movies = new Gson().fromJson(ApiConfig.JSON_NOTIFICATION, new TypeToken<ArrayList<NotificationItem>>() {
    }.getType());
    private LoadingLayout mLoadingLayout;

    private MultipleItemQuickAdapter mAdapter;

    @Override
    protected void loadData(boolean force) {
        if (force) {
            mLoadingLayout.showContent();
            mAdapter.replaceData(movies);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mLoadingLayout = view.findViewById(R.id.loading);
        final RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        final RefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (mAdapter == null) {
            mAdapter = new MultipleItemQuickAdapter();
            mAdapter.openLoadAnimation();
            mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    doStartActivity(VideoListActivity.class, null);
                }
            });
        }
        recyclerView.setAdapter(mAdapter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mAdapter.getItemCount() < 2) {
                            List<NotificationItem> movies = new Gson().fromJson(ApiConfig.JSON_MOVIES, new TypeToken<ArrayList<NotificationItem>>() {
                            }.getType());
                            mAdapter.replaceData(movies);
                        }
                        refreshLayout.finishRefresh();
                    }
                }, 2000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mAdapter.addData(movies);
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_layout;
    }
}
