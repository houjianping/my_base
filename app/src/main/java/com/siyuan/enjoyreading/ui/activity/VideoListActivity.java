package com.siyuan.enjoyreading.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;

import com.androidapp.adapter.BaseQuickAdapter;
import com.androidapp.mvp.MvpBaseListActivity;
import com.androidapp.mvp.MvpBaseView;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnLoadMoreListener;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;
import com.androidapp.utils.ToastUtils;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.entity.VideoItem;
import com.siyuan.enjoyreading.model.user.VideoListModelLogic;
import com.siyuan.enjoyreading.presenter.user.VideoListPresenterImpl;
import com.siyuan.enjoyreading.presenter.user.interfaces.IVideoListPresenter;
import com.shuyu.gsyvideoplayer.GSYVideoManager;

import java.util.List;

public class VideoListActivity extends MvpBaseListActivity<VideoListModelLogic, VideoListPresenterImpl> implements IVideoListPresenter.View {

    @Override
    protected OnRefreshListener getOnRefreshListener() {
        return new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
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
        }
        return mAdapter;
    }

    private MultipleItemQuickAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mPresenter.getVideoListItems(1);
    }

    @Override
    protected void onEvent() {
    }

    @Override
    protected MvpBaseView getView() {
        return this;
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        mTitleBar.setTitle("视频列表页面");
    }

    @Override
    public void onVideoListUpdate(List<VideoItem> circleItemList) {
        ToastUtils.show("--1-----");
        mAdapter.replaceData(circleItemList);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }

    @Override
    protected boolean isDividerItemDecorationEnable() {
        return false;
    }
}
