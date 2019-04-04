package com.siyuan.enjoyreading.ui.fragment.index;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.androidapp.adapter.BaseQuickAdapter;
import com.androidapp.banner.Banner;
import com.androidapp.banner.listener.OnBannerListener;
import com.androidapp.smartrefresh.layout.SmartRefreshLayout;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnLoadMoreListener;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;
import com.androidapp.widget.AppGridView;
import com.androidapp.widget.LoadingLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.adapter.SmallCategoryAdapter;
import com.siyuan.enjoyreading.api.ApiConfig;
import com.siyuan.enjoyreading.entity.NewsItem;
import com.siyuan.enjoyreading.entity.SmallCategoryItem;
import com.siyuan.enjoyreading.ui.activity.currency.FullPagePlayerActivity;
import com.siyuan.enjoyreading.ui.fragment.base.ViewPagerBaseFragment;
import com.siyuan.enjoyreading.util.BannerImageLoader;
import com.siyuan.enjoyreading.util.BannerUtil;
import com.siyuan.enjoyreading.util.IntentUtil;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class EntertainmentFragment extends ViewPagerBaseFragment {

    private MultipleItemQuickAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private LoadingLayout mLoadingLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void loadData(boolean force) {
        mLoadingLayout.showContent();
        final List<NewsItem> circleItems = new Gson().fromJson(ApiConfig.JSON_VIDEO_LIST, new TypeToken<ArrayList<NewsItem>>() {}.getType());
        Banner banner = BannerUtil.getBannerView(getContext(), ApiConfig.BANNER_ITEMS, mRecyclerView, false);
        mAdapter.addHeaderView(banner, 0);
        List<SmallCategoryItem> searchKeywords = new Gson().fromJson(ApiConfig.JSON_SMALL_CATEGORY, new TypeToken<ArrayList<SmallCategoryItem>>() {
        }.getType());
        final SmallCategoryAdapter smallCategoryAdapter = new SmallCategoryAdapter(getContext(), searchKeywords);
        AppGridView appGridView = new AppGridView(getContext());
        appGridView.setNumColumns(5);
        appGridView.setAdapter(smallCategoryAdapter);
        appGridView.setHorizontalSpacing(10);
        appGridView.setVerticalSpacing(10);
        appGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SmallCategoryItem smallCategoryItem = smallCategoryAdapter.getItem(position);
                IntentUtil.startActivity(mContext, smallCategoryItem.getApp_jump());
            }
        });
        mAdapter.addHeaderView(appGridView, 1);
        mAdapter.openLoadAnimation();
        mAdapter.replaceData(circleItems);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        Log.e("", "------loadData----mAdapter----");
        mLoadingLayout = view.findViewById(com.androidapp.base.R.id.loading);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        final SmartRefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (mAdapter == null) {
            Log.e("", "------loadData----mAdapter---1111-");
            mAdapter = new MultipleItemQuickAdapter<NewsItem>();
        }
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(FullPagePlayerActivity.getIntent(mContext, (NewsItem) mAdapter.getItem(position)));
            }
        });
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
