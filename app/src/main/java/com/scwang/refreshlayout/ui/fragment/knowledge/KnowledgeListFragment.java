package com.scwang.refreshlayout.ui.fragment.knowledge;

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
import com.androidapp.adapter.BaseViewHolder;
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
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.refreshlayout.R;
import com.scwang.refreshlayout.ui.activity.SimplePlayer;
import com.scwang.refreshlayout.ui.activity.VideoListActivity;
import com.scwang.refreshlayout.ui.fragment.TabFragment;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.DividerItemDecoration.VERTICAL;

public class KnowledgeListFragment extends LazyLoadFragment {

    public static List<BannerItem> BANNER_ITEMS = new ArrayList<BannerItem>() {{
        add(new BannerItem("最后的骑士", R.mipmap.image_movie_header_48621499931969370));
        add(new BannerItem("三生三世十里桃花", R.mipmap.image_movie_header_12981501221820220));
        add(new BannerItem("豆福传", R.mipmap.image_movie_header_12231501221682438));
    }};
    private QuickAdapter mAdapter;

    @Override
    protected void loadData(boolean force) {
        Log.e("", "------loadData--------" + force);
        if (force) {
            List<Movie> movies = new ArrayList<>();
            movies.add(new Movie());
            movies.add(new Movie());
            movies.add(new Movie());
            movies.add(new Movie());
            movies.add(new Movie());
            movies.add(new Movie());
            movies.add(new Movie());
            movies.add(new Movie());
            movies.add(new Movie());
            movies.add(new Movie());
            movies.add(new Movie());
            movies.add(new Movie());
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
            mAdapter = new QuickAdapter();
            //添加Header
            View header = LayoutInflater.from(getContext()).inflate(R.layout.listitem_movie_header, recyclerView, false);
            Banner banner = (Banner) header;
            banner.setImageLoader(new GlideImageLoader());
            banner.setImages(BANNER_ITEMS);
            banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int i) {
                    Toast.makeText(getContext(), "si=" + i, Toast.LENGTH_SHORT).show();
                }
            });
            banner.start();
            mAdapter.addHeaderView(banner, 0);
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

    public static class Movie {
    }

    public static class BannerItem {

        public int pic;
        public String title;

        public BannerItem() {
        }

        public BannerItem(String title, int pic) {
            this.pic = pic;
            this.title = title;
        }
    }

    public class QuickAdapter extends BaseQuickAdapter<Movie, BaseViewHolder> {
        public QuickAdapter() {
            super(R.layout.item_knowledge);
        }

        @Override
        protected void convert(BaseViewHolder viewHolder, Movie item) {
            viewHolder.setText(R.id.article_time, "2018-01-05")
                    .setText(R.id.article_read_num, "139次播放")
                    .setText(R.id.article_title, "第5集 荒山野岭").addOnClickListener(R.id.article_item);
        }
    }

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setImageResource(((BannerItem) path).pic);
        }
    }
}
