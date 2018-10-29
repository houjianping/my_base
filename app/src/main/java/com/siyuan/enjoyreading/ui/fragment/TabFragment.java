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
import com.androidapp.adapter.BaseQuickAdapter;
import com.androidapp.fragment.LazyLoadFragment;
import com.androidapp.utils.ToastUtils;
import com.androidapp.pagedgridview.PagedGridItem;
import com.androidapp.pagedgridview.PagedGridLayout;
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
import com.siyuan.enjoyreading.ui.activity.VideoListActivity;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class TabFragment extends LazyLoadFragment {

    final List<Movie> movies = new Gson().fromJson(ApiConfig.JSON_MOVIES, new TypeToken<ArrayList<Movie>>() {
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
            PagedGridLayout pagedGridLayout = new PagedGridLayout(getActivity(), new PagedGridLayout.OnGridItemClick() {
                @Override
                public void onGridItemClick(Object item) {
                    GriedViewItem item1 = (GriedViewItem) item;
                    ToastUtils.show("####################" + item1.getTitle());
                }
            });
            List<GriedViewItem> list = new ArrayList<>();
            GriedViewItem item = new GriedViewItem();
            item.setTitle("美团测试图");
            item.setIcon_url("http://p1.meituan.net/movie/55c57c37c9baa412aa9351f385275ef861052.jpg");
            list.add(item);
            list.add(item);
            list.add(item);
            list.add(item);
            list.add(item);
            list.add(item);
            list.add(item);
            list.add(item);
            list.add(item);
            list.add(item);
            pagedGridLayout.setData(list, 2, 4);
            mAdapter.addHeaderView(banner, 0);
            mAdapter.addHeaderView(pagedGridLayout, 1);
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

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setImageResource(((BannerItem) path).pic);
        }
    }

    class GriedViewItem extends PagedGridItem {
    }
}
