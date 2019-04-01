package com.siyuan.enjoyreading.ui.fragment.index;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidapp.adapter.BaseQuickAdapter;
import com.androidapp.banner.Banner;
import com.androidapp.banner.BannerConfig;
import com.androidapp.banner.listener.OnBannerListener;
import com.androidapp.filter.single.view.AppDividerItemDecoration;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnLoadMoreListener;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;
import com.androidapp.widget.AppGridView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.adapter.SmallCategoryAdapter;
import com.siyuan.enjoyreading.api.ApiConfig;
import com.siyuan.enjoyreading.entity.AdItem;
import com.siyuan.enjoyreading.entity.GridItem;
import com.siyuan.enjoyreading.entity.HeaderItem;
import com.siyuan.enjoyreading.entity.IndexSection;
import com.siyuan.enjoyreading.entity.Movie;
import com.siyuan.enjoyreading.entity.MultipleEntity;
import com.siyuan.enjoyreading.entity.SmallCategoryItem;
import com.siyuan.enjoyreading.ui.activity.VideoListActivity;
import com.siyuan.enjoyreading.ui.fragment.base.ViewPagerBaseFragment;
import com.siyuan.enjoyreading.util.BannerImageLoader;
import com.siyuan.enjoyreading.util.IntentUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.androidapp.widget.LoadingLayout;
import com.siyuan.enjoyreading.widget.FlexibleView;


public class RecommendFragment extends ViewPagerBaseFragment {

    private List<MultipleEntity> movies = new ArrayList<>();
    private MultipleItemQuickAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private LoadingLayout mLoadingLayout;

    @Override
    protected void loadData(boolean force) {
        movies.clear();
        //添加Header
        mLoadingLayout.showContent();
        View header = LayoutInflater.from(getContext()).inflate(R.layout.listitem_movie_header, mRecyclerView, false);
        Banner banner = (Banner) header;
        banner.setImageLoader(new BannerImageLoader());
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.setImages(ApiConfig.BANNER_ITEMS);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int i) {
                Toast.makeText(getContext(), "si=" + i, Toast.LENGTH_SHORT).show();
            }
        });
        banner.start();
        mAdapter.addHeaderView(banner, 0);
        FlexibleView flexibleView = new FlexibleView(getContext());
        mAdapter.addHeaderView(flexibleView, 1);

        mAdapter.openLoadAnimation();
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                doStartActivity(VideoListActivity.class, null);
            }
        });
        IndexSection indexSection = new Gson().fromJson(ApiConfig.JSON_INDEX, new TypeToken<IndexSection>() {}.getType());
        HeaderItem headerItem1 = indexSection.getHeader();
        GridItem gridItem11 = indexSection.getData();
        movies.add(headerItem1);
        movies.add(gridItem11);

        IndexSection indexSection2 = new Gson().fromJson(ApiConfig.JSON_INDEX_2, new TypeToken<IndexSection>() {}.getType());
        HeaderItem headerItem2 = indexSection2.getHeader();
        GridItem gridItem2 = indexSection2.getData();
        movies.add(headerItem2);
        movies.add(gridItem2);

        HeaderItem headerItem3 = new HeaderItem();
        headerItem3.setLeftTitle("热门频道");
        movies.add(headerItem3);
        List<MultipleEntity> lists = new Gson().fromJson(ApiConfig.JSON_MOVIES, new TypeToken<ArrayList<Movie>>() {
        }.getType());
        AdItem adItem = new AdItem();
        adItem.setWidth(750);
        adItem.setHeight(360);
        adItem.setAction("{\"page\":\"SettingAbout\",\"type\":1}");
        movies.add(adItem);
        movies.addAll(lists);
        mAdapter.replaceData(movies);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mLoadingLayout = view.findViewById(com.androidapp.base.R.id.loading);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        final RefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (mAdapter == null) {
            mAdapter = new MultipleItemQuickAdapter();
        }
        mRecyclerView.setAdapter(mAdapter);
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
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list_layout;
    }
}
