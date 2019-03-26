package com.siyuan.enjoyreading.ui.activity.pcenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.androidapp.activity.BaseListActivity;
import com.androidapp.adapter.BaseQuickAdapter;
import com.androidapp.filter.single.view.AppDividerItemDecoration;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnLoadMoreListener;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;
import com.androidapp.utils.ScreenUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.api.ApiConfig;
import com.siyuan.enjoyreading.entity.FollowItem;
import com.siyuan.enjoyreading.ui.activity.SocializCircleActivity;

import java.util.ArrayList;
import java.util.List;

import static com.androidapp.filter.single.view.AppDividerItemDecoration.VERTICAL_LIST;

public class SettingFollowActivity extends BaseListActivity {
    @Override
    protected OnRefreshListener getOnRefreshListener() {
        return new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mAdapter.getItemCount() < 2) {
                            List<FollowItem> movies = new Gson().fromJson(ApiConfig.JSON_MOVIES, new TypeToken<ArrayList<FollowItem>>() {
                            }.getType());
                            mAdapter.replaceData(movies);
                        }
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
                final List<FollowItem> movies = new Gson().fromJson(ApiConfig.JSON_MOVIES, new TypeToken<ArrayList<FollowItem>>() {
                }.getType());
                mAdapter.addData(movies);
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
                    doStartActivity(SocializCircleActivity.class, null);
                }
            });
        }
        return mAdapter;
    }

    final List<FollowItem> movies = new Gson().fromJson(ApiConfig.JSON_MOVIES, new TypeToken<ArrayList<FollowItem>>() {
    }.getType());

    private MultipleItemQuickAdapter mAdapter;

    @Override
    protected void initView() {
        super.initView();
        AppDividerItemDecoration decoration = new AppDividerItemDecoration(mContext, VERTICAL_LIST);
        decoration.setMargin(ScreenUtil.dip2px(mContext, 70), ScreenUtil.dip2px(mContext, 15));
        mRecyclerView.addItemDecoration(decoration);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
    }

    @Override
    protected void initData() {
        mLoadingLayout.showContent();
        mAdapter.replaceData(movies);
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        mTitleBar.setTitle("我的关注");
    }

    @Override
    protected boolean enableDecoration() {
        return false;
    }
}
