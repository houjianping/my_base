package com.siyuan.enjoyreading.ui.fragment.knowledge;

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

import com.androidapp.banner.Banner;
import com.androidapp.banner.listener.OnBannerListener;
import com.androidapp.banner.loader.ImageLoader;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnLoadMoreListener;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.api.ApiConfig;
import com.siyuan.enjoyreading.entity.BannerItem;
import com.siyuan.enjoyreading.entity.KnowledgeListItem;
import com.siyuan.enjoyreading.ui.fragment.base.ViewPagerBaseFragment;

import java.util.ArrayList;
import java.util.List;

import com.androidapp.widget.LoadingLayout;
import com.siyuan.enjoyreading.util.BannerUtil;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class KnowledgeListFragment extends ViewPagerBaseFragment {

    private MultipleItemQuickAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private LoadingLayout mLoadingLayout;

    @Override
    protected void loadData(boolean force) {
        Log.e("", "------loadData--------" + force);
        if (force) {
            mLoadingLayout.showContent();
            Banner banner = BannerUtil.getBannerView(getContext(), ApiConfig.BANNER_ITEMS, mRecyclerView, false);
            mAdapter.addHeaderView(banner, 0);
            mAdapter.openLoadAnimation();

            List<KnowledgeListItem> movies = new ArrayList<>();
            movies.add(new KnowledgeListItem());
            movies.add(new KnowledgeListItem());
            movies.add(new KnowledgeListItem());
            movies.add(new KnowledgeListItem());
            movies.add(new KnowledgeListItem());
            movies.add(new KnowledgeListItem());
            movies.add(new KnowledgeListItem());
            movies.add(new KnowledgeListItem());
            movies.add(new KnowledgeListItem());
            movies.add(new KnowledgeListItem());
            movies.add(new KnowledgeListItem());
            movies.add(new KnowledgeListItem());
            mAdapter.replaceData(movies);
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("", "------loadData---onCreate-----" + getUserVisibleHint());
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("", "------loadData---onResume-----" + getUserVisibleHint());
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        Log.e("", "------loadData----mAdapter----");
        mLoadingLayout = view.findViewById(com.androidapp.base.R.id.loading);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        final RefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (mAdapter == null) {
            Log.e("", "------loadData----mAdapter---1111-");
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

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setImageResource(((BannerItem) path).pic);
        }
    }
}
