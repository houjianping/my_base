package com.scwang.refreshlayout.ui.activity.pcenter;

import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.androidapp.banner.Banner;
import com.androidapp.banner.listener.OnBannerListener;
import com.androidapp.base.activity.BaseListActivity;
import com.androidapp.base.adapter.BaseQuickAdapter;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnLoadMoreListener;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.refreshlayout.R;
import com.scwang.refreshlayout.adapter.QuickAdapter;
import com.scwang.refreshlayout.api.ApiConfig;
import com.scwang.refreshlayout.bean.BannerItem;
import com.scwang.refreshlayout.bean.Movie;
import com.scwang.refreshlayout.imageloader.GlideImageLoader;
import com.scwang.refreshlayout.ui.ui.TestActivity;

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
            mAdapter = new QuickAdapter();
            mAdapter.openLoadAnimation();
            mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    doStartActivity(TestActivity.class, null);
                }
            });
        }
        return mAdapter;
    }

    public static List<BannerItem> BANNER_ITEMS = new ArrayList<BannerItem>() {{
        add(new BannerItem("最后的骑士", R.mipmap.image_movie_header_48621499931969370));
        add(new BannerItem("三生三世十里桃花", R.mipmap.image_movie_header_12981501221820220));
        add(new BannerItem("豆福传", R.mipmap.image_movie_header_12231501221682438));
    }};

    final List<Movie> movies = new Gson().fromJson(ApiConfig.JSON_MOVIES, new TypeToken<ArrayList<Movie>>() {
    }.getType());

    private QuickAdapter mAdapter;

    @Override
    protected void initView() {
        //添加Header
        super.initView();
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        View header = LayoutInflater.from(mContext).inflate(R.layout.listitem_movie_header, mRecyclerView, false);
        Banner banner = (Banner) header;
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(BANNER_ITEMS);
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
        mAdapter.replaceData(movies);
    }

    @Override
    protected void initTitle() {
        super.initTitle();
        mTitleBar.setTitle("我的收藏");
    }
}
