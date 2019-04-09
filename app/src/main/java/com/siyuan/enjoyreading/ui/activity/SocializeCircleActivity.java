package com.siyuan.enjoyreading.ui.activity;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.androidapp.activity.BaseListActivity;
import com.androidapp.adapter.BaseQuickAdapter;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnLoadMoreListener;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.api.ApiConfig;
import com.siyuan.enjoyreading.entity.circle.CircleItem;
import com.siyuan.enjoyreading.widget.ZoneHeaderView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SocializeCircleActivity extends BaseListActivity {

    ZoneHeaderView zoneHeaderView;
    private MultipleItemQuickAdapter mAdapter;

    @Override
    protected OnRefreshListener getOnRefreshListener() {
        return new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.replaceData(getCircles());
                        refreshLayout.finishRefresh();
                    }
                }, 2000);
            }
        };
    }

    @Override
    protected OnLoadMoreListener getOnLoadMoreListener() {
        return new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        };
    }

    @Override
    protected BaseQuickAdapter getListViewAdapter() {
        if (mAdapter == null) {
            mAdapter = new MultipleItemQuickAdapter();
            mAdapter.openLoadAnimation();
            mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    doStartActivity(SocializeCircleActivity.class, null);
                }
            });
        }
        return mAdapter;
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        mTitleBar.setTitle("我的动态");
    }

    @Override
    protected void initView() {
        super.initView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        zoneHeaderView = new ZoneHeaderView(this);
        zoneHeaderView.setData("测试用户名", "http://d.hiphotos.baidu.com/image/pic/item/e4dde71190ef76c6e453882a9f16fdfaaf516729.jpg");
        mAdapter.addHeaderView(zoneHeaderView, 0);
        updateNotReadNewsCount(10, null);
    }

    @Override
    protected void initData() {
        super.initData();
        mLoadingLayout.showContent();
        mAdapter.replaceData(getCircles());
    }

    public void updateNotReadNewsCount(int count, String icon) {
        zoneHeaderView.setNotReadMsgData(count, icon);
    }

    private List<CircleItem> getCircles() {
        final List<CircleItem> circleItems = new Gson().fromJson(ApiConfig.JSON_ZONE_LIST, new TypeToken<ArrayList<CircleItem>>() {
        }.getType());
        for (int i = 0; i < circleItems.size(); i++) {
            circleItems.get(i).setPictures(ApiConfig.getRandomPhotoUrlString(new Random().nextInt(9)));
        }
        return circleItems;
    }
}
