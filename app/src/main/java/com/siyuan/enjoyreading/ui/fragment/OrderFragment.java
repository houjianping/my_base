package com.siyuan.enjoyreading.ui.fragment;

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
import com.androidapp.fragment.LazyLoadFragment;
import com.androidapp.pagedgridview.PagedGridItem;
import com.androidapp.pagedgridview.PagedGridLayout;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnLoadMoreListener;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;
import com.androidapp.utils.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.api.ApiConfig;
import com.siyuan.enjoyreading.entity.BannerItem;
import com.siyuan.enjoyreading.entity.OrderMovie;
import com.siyuan.enjoyreading.widget.HeaderView;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class OrderFragment extends LazyLoadFragment {

    public static List<BannerItem> BANNER_ITEMS = new ArrayList<BannerItem>() {{
        add(new BannerItem("最后的骑士", R.mipmap.image_movie_header_48621499931969370));
        add(new BannerItem("三生三世十里桃花", R.mipmap.image_movie_header_12981501221820220));
        add(new BannerItem("豆福传", R.mipmap.image_movie_header_12231501221682438));
    }};
    final List<OrderMovie> movies = new Gson().fromJson(ApiConfig.JSON_MOVIES, new TypeToken<ArrayList<OrderMovie>>() {
    }.getType());
    private MultipleItemQuickAdapter mAdapter;

    @Override
    protected void loadData(boolean force) {
        Log.e("", "------loadData--------" + force);
        if (force) {
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
        final RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        final RefreshLayout refreshLayout = view.findViewById(R.id.refreshLayout);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        if (mAdapter == null) {
            Log.e("", "------loadData----mAdapter---1111-");
            mAdapter = new MultipleItemQuickAdapter();
            //添加Header
            View headerLayout = LayoutInflater.from(getContext()).inflate(R.layout.listitem_movie_header, recyclerView, false);
            Banner banner = (Banner) headerLayout;
            banner.setImageLoader(new GlideImageLoader());
            banner.setImages(BANNER_ITEMS);
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int i) {
                    Toast.makeText(getContext(), "si=" + i, Toast.LENGTH_SHORT).show();
                }
            });
            banner.start();
            PagedGridLayout pagedGridLayout = new PagedGridLayout(getActivity(), new PagedGridLayout.OnGridItemClick() {
                @Override
                public void onGridItemClick(Object item) {
                    GriedViewItem item1 = (GriedViewItem) item;
                    ToastUtils.show("####################" + item1.getTitle());
                }
            });
            List<GriedViewItem> list = new ArrayList<>();
            GriedViewItem item = new GriedViewItem();
            item.setTitle("健身");
            item.setIcon_url("http://cdn.code.lianyouapp.com/static/service/bcategory/8@3x.png");
            list.add(item);
            list.add(item);
            list.add(item);
            list.add(item);
            list.add(item);
            list.add(item);
            list.add(item);
            list.add(item);
            pagedGridLayout.setEnableBigItem(true);
            pagedGridLayout.setData(list, 5, 2);
            mAdapter.addHeaderView(banner, 0);
            HeaderView categoryHeader = new HeaderView(getContext());
            categoryHeader.setTitle("热点专栏");
            mAdapter.addHeaderView(categoryHeader, 1);
            mAdapter.addHeaderView(pagedGridLayout, 2);
            HeaderView recommendHeader = new HeaderView(getContext());
            recommendHeader.setTitle("精品推荐");
            recommendHeader.setRightViewVisible(false);
            mAdapter.addHeaderView(recommendHeader, 3);
            mAdapter.openLoadAnimation();
        }
        recyclerView.setAdapter(mAdapter);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                refreshLayout.getLayout().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (mAdapter.getItemCount() < 2) {
                            List<OrderMovie> movies = new Gson().fromJson(ApiConfig.JSON_MOVIES, new TypeToken<ArrayList<OrderMovie>>() {
                            }.getType());
                            mAdapter.replaceData(movies);
                        }
                        refreshLayout.finishRefresh();
                    }
                }, 8000);
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

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setImageResource(((BannerItem) path).pic);
        }
    }

    class GriedViewItem extends PagedGridItem {
    }
}
