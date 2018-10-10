package com.scwang.refreshlayout.ui.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.androidapp.base.adapter.BaseQuickAdapter;
import com.androidapp.base.utils.ToastUtils;
import com.androidapp.mvp.MvpBaseListActivity;
import com.androidapp.mvp.MvpBaseView;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnLoadMoreListener;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.refreshlayout.adapter.VideoListAdapter;
import com.scwang.refreshlayout.bean.VideoItem;
import com.scwang.refreshlayout.model.user.VideoListModelLogic;
import com.scwang.refreshlayout.presenter.user.VideoListPresenterImpl;
import com.scwang.refreshlayout.presenter.user.interfaces.IVideoListPresenter;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.utils.GSYVideoHelper;

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
            mAdapter = new VideoListAdapter();
            mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    Toast.makeText(mContext, "a" + view.getId(), Toast.LENGTH_SHORT).show();
                }
            });
            mAdapter.openLoadAnimation();
        }
        return mAdapter;
    }

    private VideoListAdapter mAdapter;

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
        mTitleBar.setTitle("测试页面");
    }

    @Override
    public void onVideoListUpdate(List<VideoItem> circleItemList) {
        ToastUtils.showShortToast(this, "--1-----");
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
}
