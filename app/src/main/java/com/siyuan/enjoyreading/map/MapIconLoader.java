package com.siyuan.enjoyreading.map;

import android.graphics.Bitmap;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

public class MapIconLoader {

    public static Bitmap loadBitmap(String url, boolean isMemoryCache, boolean isDiscCache, int defaultImage) {
        DisplayImageOptions options = getDisplayImageOptions(isMemoryCache, isDiscCache, defaultImage);
        return ImageLoader.getInstance().loadImageSync(url, options);
    }

    public static DisplayImageOptions getDisplayImageOptions(boolean isMemoryCache, boolean isDiscCache, int defaultImage) {
        return new DisplayImageOptions.Builder()
                .showImageForEmptyUri(defaultImage)         //没有图片资源时的默认图片
                .showImageOnFail(defaultImage)              //加载失败时的图片
                .cacheInMemory(isMemoryCache)                               //启用内存缓存
                .cacheOnDisc(isDiscCache)                                 //启用外存缓存
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }
}
