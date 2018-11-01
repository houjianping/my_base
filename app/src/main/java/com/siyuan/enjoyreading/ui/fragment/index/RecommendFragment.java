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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidapp.adapter.BaseQuickAdapter;
import com.androidapp.banner.Banner;
import com.androidapp.banner.listener.OnBannerListener;
import com.androidapp.banner.loader.ImageLoader;
import com.androidapp.fragment.LazyLoadFragment;
import com.androidapp.pagedgridview.PagedGridItem;
import com.androidapp.pagedgridview.PagedGridLayout;
import com.androidapp.pagedgridview.PagedGridView;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnLoadMoreListener;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;
import com.androidapp.utils.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.adapter.RecommendPrasiseAdapter;
import com.siyuan.enjoyreading.api.ApiConfig;
import com.siyuan.enjoyreading.entity.BannerItem;
import com.siyuan.enjoyreading.entity.Movie;
import com.siyuan.enjoyreading.entity.RecommendPrasise;
import com.siyuan.enjoyreading.ui.activity.VideoListActivity;
import com.siyuan.enjoyreading.ui.fragment.TabFragment;
import com.siyuan.enjoyreading.util.BannerImageLoader;
import com.siyuan.enjoyreading.widget.HeaderView;
import com.siyuan.enjoyreading.widget.RecommendGroupView;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class RecommendFragment extends LazyLoadFragment {

    final List<Movie> movies = new Gson().fromJson(ApiConfig.JSON_MOVIES, new TypeToken<ArrayList<Movie>>() {
    }.getType());
    private MultipleItemQuickAdapter mAdapter;

    @Override
    protected void loadData(boolean force) {
        if (force) {
            mAdapter.replaceData(movies);
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
            mAdapter = new MultipleItemQuickAdapter();
            //添加Header
            View header = LayoutInflater.from(getContext()).inflate(R.layout.listitem_movie_header, recyclerView, false);
            Banner banner = (Banner) header;
            banner.setImageLoader(new BannerImageLoader());
            banner.setImages(ApiConfig.BANNER_ITEMS);
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int i) {
                    Toast.makeText(getContext(), "si=" + i, Toast.LENGTH_SHORT).show();
                }
            });
            banner.start();


            RecommendPrasise recommendPrasise = new RecommendPrasise();
            recommendPrasise.setTitle("如何一分钟聊到男神?");
            recommendPrasise.setIconurl("http://p1.meituan.net/movie/55c57c37c9baa412aa9351f385275ef861052.jpg");
            List<RecommendPrasise> prasises = new ArrayList<>();
            prasises.add(recommendPrasise);
            prasises.add(recommendPrasise);
            prasises.add(recommendPrasise);
            prasises.add(recommendPrasise);

            mAdapter.addHeaderView(banner, 0);
            View fiveView = LayoutInflater.from(getContext()).inflate(R.layout.item_grid_column_5, recyclerView, false);
            mAdapter.addHeaderView(fiveView, 1);
            HeaderView headerView = new HeaderView(getContext());
            headerView.setTitle("好评栏目推荐");
            mAdapter.addHeaderView(headerView, 2);
            View twoView = LayoutInflater.from(getContext()).inflate(R.layout.item_grid_column_2, recyclerView, false);
            PagedGridView pagedGridView = twoView.findViewById(R.id.praise_gridview);
            pagedGridView.setAdapter(new RecommendPrasiseAdapter(getActivity(), prasises));
            mAdapter.addHeaderView(twoView, 3);

            HeaderView headerView1 = new HeaderView(getContext());
            headerView1.setTitle("小编推荐");
            headerView1.setRightViewText("更多");
            mAdapter.addHeaderView(headerView1, 4);
            View foreView = LayoutInflater.from(getContext()).inflate(R.layout.item_grid_column_4, recyclerView, false);
            PagedGridView editorPagedGridView = foreView.findViewById(R.id.editer_praise_gridview);
            editorPagedGridView.setAdapter(new RecommendPrasiseAdapter(getActivity(), prasises));
            mAdapter.addHeaderView(foreView, 5);

            HeaderView headerView22 = new HeaderView(getContext());
            headerView22.setTitle("好评栏目推荐");
            mAdapter.addHeaderView(headerView22, 6);

            mAdapter.openLoadAnimation();
            mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
                @Override
                public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                    doStartActivity(VideoListActivity.class, null);
                }
            });
        }
        recyclerView.setAdapter(mAdapter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
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
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mAdapter.addData(movies);
                refreshLayout.finishLoadMoreWithNoMoreData();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab;
    }
}
