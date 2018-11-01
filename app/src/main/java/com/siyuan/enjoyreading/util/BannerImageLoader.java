package com.siyuan.enjoyreading.util;

import android.content.Context;
import android.widget.ImageView;

import com.androidapp.banner.loader.ImageLoader;
import com.siyuan.enjoyreading.entity.BannerItem;

public class BannerImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        imageView.setImageResource(((BannerItem) path).pic);
    }
}
