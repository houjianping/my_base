package com.siyuan.enjoyreading.ui.activity.pcenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.androidapp.activity.BaseListActivity;
import com.androidapp.adapter.BaseQuickAdapter;
import com.androidapp.banner.Banner;
import com.androidapp.banner.listener.OnBannerListener;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnLoadMoreListener;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.api.ApiConfig;
import com.siyuan.enjoyreading.entity.BannerItem;
import com.siyuan.enjoyreading.entity.Movie;
import com.siyuan.enjoyreading.ui.activity.SocializCircleActivity;
import com.siyuan.enjoyreading.util.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class SettingLeavingMessage extends BaseListActivity {
    @Override
    protected OnRefreshListener getOnRefreshListener() {
        return new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mAdapter.getItemCount() < 2) {
                            List<Movie> movies = new Gson().fromJson(ApiConfig.JSON_MOVIES, new TypeToken<ArrayList<Movie>>() {
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
                final List<Movie> movies = new Gson().fromJson(ApiConfig.JSON_MOVIES, new TypeToken<ArrayList<Movie>>() {
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

    final List<Movie> movies = new Gson().fromJson(ApiConfig.JSON_MOVIES, new TypeToken<ArrayList<Movie>>() {
    }.getType());

    private MultipleItemQuickAdapter mAdapter;

    @Override
    protected void initView() {
        //添加Header
        super.initView();
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        View header = LayoutInflater.from(mContext).inflate(R.layout.listitem_movie_header, mRecyclerView, false);
        Banner banner = (Banner) header;
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(ApiConfig.BANNER_ITEMS);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int i) {
                Toast.makeText(mContext, "si=" + i, Toast.LENGTH_SHORT).show();
            }
        });
        banner.start();
        mAdapter.addHeaderView(banner, 0);
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
}
