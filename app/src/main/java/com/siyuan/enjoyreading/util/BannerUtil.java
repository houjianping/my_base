package com.siyuan.enjoyreading.util;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.androidapp.banner.Banner;
import com.androidapp.banner.BannerConfig;
import com.androidapp.banner.listener.OnBannerListener;
import com.androidapp.utils.ScreenUtil;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.entity.BannerItem;

import java.util.List;

public class BannerUtil {

    public static Banner getBannerView(final Context context, List<BannerItem> bannerItems, ViewGroup rootView, boolean attach) {
        View header = LayoutInflater.from(context).inflate(R.layout.listitem_movie_header, rootView, attach);
        if (header.getLayoutParams() instanceof RecyclerView.LayoutParams) {
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) header.getLayoutParams();
            params.height = ScreenUtil.dip2px(context, 100);
            header.setLayoutParams(params);
        }
        Banner banner = (Banner) header;
        banner.setImageLoader(new BannerImageLoader());
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.setImages(bannerItems);
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int i) {
                Toast.makeText(context, "si=" + i, Toast.LENGTH_SHORT).show();
            }
        });
        banner.start();
        return banner;
    }
}
