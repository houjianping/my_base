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
import com.androidapp.utils.ToastUtils;
import com.facebook.stetho.Stetho;
import com.scwang.refreshlayout.api.request.OkGoRequest;
import com.scwang.refreshlayout.util.DynamicTimeFormat;

import java.io.File;

public class App extends Application {

    private static final long MAX_DISK_SIZE = 1024 * 1024 * 100;
    private static final long MAX_RAM_SIZE = 1024 * 1024 * 10;
    private static App mApplication;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);//启用矢量图兼容
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                layout.setPrimaryColorsId(R.color.tab_panel_bg, R.color.colorTextTitle);//全局设置主题颜色
                return new ClassicsHeader(context).setTimeFormat(new DynamicTimeFormat("更新于 %s"));
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        ToastUtils.setContext(this);
        Stetho.initializeWithDefaults(this);
        OkGoRequest.initOkGo(this, true);
        ShareConfig.onCreate(this);
        File cacheFile = new File(this.getCacheDir(), "webview_cache");
        CacheWebView.getCacheConfig().init(this, cacheFile.getAbsolutePath(), MAX_DISK_SIZE, MAX_RAM_SIZE)
                .enableDebug(true);//100M 磁盘缓存空间,10M 内存缓存空间
    }

    public static App getContext() {
        return mApplication;
    }
}
