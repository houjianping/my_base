package com.scwang.refreshlayout;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;

import com.androidapp.cachewebviewlib.CacheWebView;
import com.androidapp.share.ShareConfig;
import com.androidapp.smartrefresh.layout.SmartRefreshLayout;
import com.androidapp.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.androidapp.smartrefresh.layout.api.RefreshHeader;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.header.ClassicsHeader;
import com.facebook.stetho.Stetho;
import com.scwang.refreshlayout.request.OkGoRequest;
import com.scwang.refreshlayout.util.DynamicTimeFormat;

import java.io.File;

public class App extends Application {

    private static final long MAX_DISK_SIZE = 1024 * 1024 * 100;
    private static final long MAX_RAM_SIZE = 1024 * 1024 * 10;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);//启用矢量图兼容
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
                return new ClassicsHeader(context).setTimeFormat(new DynamicTimeFormat("更新于 %s"));
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        OkGoRequest.initOkGo(this, true);
        ShareConfig.onCreate(this);
        File cacheFile = new File(this.getCacheDir(), "webview_cache");
        CacheWebView.getCacheConfig().init(this, cacheFile.getAbsolutePath(), MAX_DISK_SIZE, MAX_RAM_SIZE)
                .enableDebug(true);//100M 磁盘缓存空间,10M 内存缓存空间
    }
}
