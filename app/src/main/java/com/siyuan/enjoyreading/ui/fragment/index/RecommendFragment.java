package com.siyuan.enjoyreading.ui.fragment.index;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.androidapp.adapter.BaseQuickAdapter;
import com.androidapp.banner.Banner;
import com.androidapp.banner.listener.OnBannerListener;
import com.androidapp.filter.single.view.DividerItemDecoration;
import com.androidapp.fragment.LazyLoadFragment;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnLoadMoreListener;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.api.ApiConfig;
import com.siyuan.enjoyreading.entity.AdItem;
import com.siyuan.enjoyreading.entity.GridItem;
import com.siyuan.enjoyreading.entity.HeaderItem;
import com.siyuan.enjoyreading.entity.Movie;
import com.siyuan.enjoyreading.entity.MultipleEntity;
import com.siyuan.enjoyreading.ui.activity.VideoListActivity;
import com.siyuan.enjoyreading.util.BannerImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RecommendFragment extends LazyLoadFragment {

    List<MultipleEntity> movies = new ArrayList<>();
    private MultipleItemQuickAdapter mAdapter;

    @Override
    protected void loadData(boolean force) {
        if (force) {
            movies.clear();
            HeaderItem headerItem1 = new HeaderItem();
            headerItem1.setLeftTitle("好评栏目推荐1");
            movies.add(headerItem1);
            GridItem gridItem11 = new GridItem();
            gridItem11.setColumn(2);
            movies.add(gridItem11);

            HeaderItem headerItem2 = new HeaderItem();
            headerItem2.setLeftTitle("好评栏目推荐2");
            movies.add(headerItem2);
            GridItem gridItem2 = new GridItem();
            gridItem2.setColumn(4);
            movies.add(gridItem2);

            HeaderItem headerItem3 = new HeaderItem();
            headerItem3.setLeftTitle("好评栏目推荐");
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
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL_LIST);
        Map<Integer, Boolean> itemTYpes = new HashMap<>();
        itemTYpes.put(MultipleItemQuickAdapter.ITEM_TEST, true);
        dividerItemDecoration.setItemDividerType(itemTYpes);
//        recyclerView.addItemDecoration(dividerItemDecoration);
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

            mAdapter.addHeaderView(banner, 0);
            View fiveView = LayoutInflater.from(getContext()).inflate(R.layout.item_grid_column_5, recyclerView, false);
            mAdapter.addHeaderView(fiveView, 1);

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
}
