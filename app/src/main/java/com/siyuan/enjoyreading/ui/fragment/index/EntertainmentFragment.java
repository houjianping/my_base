package com.siyuan.enjoyreading.ui.fragment.index;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidapp.adapter.BaseQuickAdapter;
import com.androidapp.banner.Banner;
import com.androidapp.banner.listener.OnBannerListener;
import com.androidapp.banner.loader.ImageLoader;
import com.androidapp.fragment.LazyLoadFragment;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnLoadMoreListener;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.api.ApiConfig;
import com.siyuan.enjoyreading.entity.BannerItem;
import com.siyuan.enjoyreading.entity.VideoItem;
import com.siyuan.enjoyreading.ui.activity.currency.FullPagePlayerActivity;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class EntertainmentFragment extends LazyLoadFragment {

    private MultipleItemQuickAdapter mAdapter;

    @Override
    protected void loadData(boolean force) {
        if (force) {
            final List<VideoItem> circleItems = new Gson().fromJson(ApiConfig.JSON_VIDEO_LIST, new TypeToken<ArrayList<VideoItem>>() {
            }.getType());
            mAdapter.replaceData(circleItems);
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
        Log.e("", "------loadData----mAdapter----");
        final RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        final RefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (mAdapter == null) {
            Log.e("", "------loadData----mAdapter---1111-");
            mAdapter = new MultipleItemQuickAdapter<VideoItem>();
            //添加Header
            View header = LayoutInflater.from(getContext()).inflate(R.layout.listitem_movie_header, recyclerView, false);
            Banner banner = (Banner) header;
            banner.setImageLoader(new GlideImageLoader());
            banner.setImages(ApiConfig.BANNER_ITEMS);
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int i) {
                    Toast.makeText(getContext(), "si=" + i, Toast.LENGTH_SHORT).show();
                }
            });
            banner.start();
            mAdapter.addHeaderView(banner, 0);
            mAdapter.openLoadAnimation();
        }
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                startActivity(FullPagePlayerActivity.getIntent(mContext, (VideoItem) mAdapter.getItem(position)));
            }
        });
        recyclerView.setAdapter(mAdapter);
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
        return R.layout.fragment_tab;
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setImageResource(((BannerItem) path).pic);
        }
    }
}
