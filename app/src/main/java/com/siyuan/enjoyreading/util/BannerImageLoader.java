package com.siyuan.enjoyreading.util;

import android.content.Context;
import android.widget.ImageView;

import com.androidapp.banner.loader.ImageLoader;
import com.androidapp.utils.ImageLoaderUtils;
import com.siyuan.enjoyreading.entity.BannerItem;

public class BannerImageLoader extends ImageLoader {

    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        BannerItem bannerItem = ((BannerItem) path);
        ImageLoaderUtils.loadBanner(context, imageView, bannerItem.pic);
    }
}
