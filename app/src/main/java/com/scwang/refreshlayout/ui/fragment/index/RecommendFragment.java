package com.scwang.refreshlayout.ui.fragment.index;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidapp.banner.Banner;
import com.androidapp.banner.listener.OnBannerListener;
import com.androidapp.banner.loader.ImageLoader;
import com.androidapp.fragment.LazyLoadFragment;
import com.androidapp.pagedgridview.PagedGridView;
import com.scwang.refreshlayout.R;
import com.scwang.refreshlayout.adapter.RecommendPrasiseAdapter;
import com.scwang.refreshlayout.bean.RecommendPrasise;
import com.scwang.refreshlayout.widget.HeaderView;
import com.scwang.refreshlayout.widget.RecommendGroupView;

import java.util.ArrayList;
import java.util.List;

public class RecommendFragment extends LazyLoadFragment {
    public static List<BannerItem> BANNER_ITEMS = new ArrayList<BannerItem>() {{
        add(new BannerItem("最后的骑士", R.mipmap.image_movie_header_48621499931969370));
        add(new BannerItem("三生三世十里桃花", R.mipmap.image_movie_header_12981501221820220));
        add(new BannerItem("豆福传", R.mipmap.image_movie_header_12231501221682438));
    }};

    @Override
    protected void loadData(boolean force) {
        if (force) {
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
        Banner banner = (Banner) view.findViewById(R.id.recommend_banner);;
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(BANNER_ITEMS);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int i) {
                Toast.makeText(getContext(), "si=" + i, Toast.LENGTH_SHORT).show();
            }
        });
        banner.start();
        HeaderView headerView = view.findViewById(R.id.praise_header);
        headerView.setTitle("好评栏目推荐");
        PagedGridView pagedGridView = view.findViewById(R.id.praise_gridview);
        RecommendPrasise recommendPrasise = new RecommendPrasise();
        recommendPrasise.setTitle("如何一分钟聊到男神?");
        recommendPrasise.setIconurl("http://p1.meituan.net/movie/55c57c37c9baa412aa9351f385275ef861052.jpg");
        List<RecommendPrasise> prasises = new ArrayList<>();
        prasises.add(recommendPrasise);
        prasises.add(recommendPrasise);
        prasises.add(recommendPrasise);
        prasises.add(recommendPrasise);
        HeaderView editorHeaderView = view.findViewById(R.id.editor_praise_header);
        editorHeaderView.setTitle("小编推荐");
        editorHeaderView.setRightViewText("更多");
        pagedGridView.setAdapter(new RecommendPrasiseAdapter(getActivity(), prasises));


        PagedGridView editorPagedGridView = view.findViewById(R.id.editer_praise_gridview);
        editorPagedGridView.setAdapter(new RecommendPrasiseAdapter(getActivity(), prasises));

        LinearLayout layout = view.findViewById(R.id.ll_recommend_group);
        layout.addView(new RecommendGroupView(getActivity()));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.index_recommend;
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

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setImageResource(((BannerItem) path).pic);
        }
    }
}
