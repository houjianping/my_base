package com.siyuan.enjoyreading.ui.activity.pcenter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.androidapp.activity.BaseActivity;
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
import com.siyuan.enjoyreading.entity.WalletItem;
import com.siyuan.enjoyreading.util.BannerImageLoader;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class PersonalWalletList extends BaseActivity {

    private MultipleItemQuickAdapter mAdapter;
    private RefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    final List<WalletItem> mWalletListItems = new Gson().fromJson(ApiConfig.JSON_WALLET_LIST, new TypeToken<ArrayList<WalletItem>>() {
    }.getType());

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.act_personal_wallet_list);
    }

    @Override
    protected void initView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRefreshLayout = findViewById(R.id.refreshLayout);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        if (mAdapter == null) {
            mAdapter = new MultipleItemQuickAdapter();
            View header = LayoutInflater.from(this).inflate(R.layout.listitem_movie_header, mRecyclerView, false);
            Banner banner = (Banner) header;
            banner.setImageLoader(new BannerImageLoader());
            banner.setImages(ApiConfig.BANNER_ITEMS);
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int i) {
                    Toast.makeText(PersonalWalletList.this, "si=" + i, Toast.LENGTH_SHORT).show();
                }
            });
            banner.start();
            mAdapter.addHeaderView(banner, 0);
            mAdapter.openLoadAnimation();
        }
        mAdapter.replaceData(mWalletListItems);
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mAdapter.getItemCount() < 2) {
                            List<WalletItem> items = new Gson().fromJson(ApiConfig.JSON_WALLET_LIST, new TypeToken<ArrayList<WalletItem>>() {
                            }.getType());
                            mAdapter.replaceData(items);
                        }
                        refreshLayout.finishRefresh();
                    }
                }, 2000);
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mAdapter.addData(mWalletListItems);
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        });
    }

    @Override
    protected void initData() {
    }
}
