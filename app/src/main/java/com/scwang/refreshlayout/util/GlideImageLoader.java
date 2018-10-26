package com.scwang.refreshlayout.util;

import android.content.Context;
import android.widget.ImageView;

import com.androidapp.banner.loader.ImageLoader;
import com.scwang.refreshlayout.entity.BannerItem;

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        imageView.setImageResource(((BannerItem) path).pic);
    }
}
