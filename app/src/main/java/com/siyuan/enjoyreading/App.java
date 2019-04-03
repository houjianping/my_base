package com.siyuan.enjoyreading;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;

import com.androidapp.activity.AppManager;
import com.androidapp.cachewebviewlib.CacheWebView;
import com.androidapp.map.MapApplication;
import com.androidapp.share.ShareConfig;
import com.androidapp.smartrefresh.layout.SmartRefreshLayout;
import com.androidapp.smartrefresh.layout.api.DefaultRefreshHeaderCreator;
import com.androidapp.smartrefresh.layout.api.RefreshHeader;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.header.ClassicsHeader;
import com.androidapp.systemreport.SystemReport;
import com.androidapp.utils.SPUtils;
import com.androidapp.utils.ToastUtils;
import com.facebook.stetho.Stetho;
import com.siyuan.enjoyreading.api.ApiConfig;
import com.siyuan.enjoyreading.api.request.HttpRequest;
import com.siyuan.enjoyreading.util.DynamicTimeFormat;

import java.io.File;

public class App extends Application {

    private static final long MAX_DISK_SIZE = 1024 * 1024 * 100;
    private static final long MAX_RAM_SIZE = 1024 * 1024 * 10;
    private static Application sInstance;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);//启用矢量图兼容
        SmartRefreshLayout.setDefaultRefreshHeaderCreator(
                new DefaultRefreshHeaderCreator() {
            @Override
            public RefreshHeader createRefreshHeader(@NonNull Context context, @NonNull RefreshLayout layout) {
                //全局设置主题颜色（优先级第二低，可以覆盖 DefaultRefreshInitializer 的配置，与下面的ClassicsHeader绑定）
                layout.setPrimaryColorsId(R.color.white, R.color.colorIndicator);
                return new ClassicsHeader(context).setTimeFormat(new DynamicTimeFormat("更新于 %s"));
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setApplication(this);
        SystemReport.init(this, "", "", true);
        SPUtils.init(this);
        ToastUtils.setContext(this);
        Stetho.initializeWithDefaults(this);
        HttpRequest.initOkGo(this, ApiConfig.DEV_MODE);
        ShareConfig.onCreate(this);
        File cacheFile = new File(this.getCacheDir(), ApiConfig.EXT_NAME);
        CacheWebView.getCacheConfig().init(this, cacheFile.getAbsolutePath(), MAX_DISK_SIZE, MAX_RAM_SIZE)
                .enableDebug(ApiConfig.DEV_MODE);//100M 磁盘缓存空间,10M 内存缓存空间
        MapApplication.onCreate(this);
    }

    /**
     * 获得当前app运行的Application
     */
    public static Application getInstance() {
        if (sInstance == null) {
            throw new NullPointerException("please inherit BaseApplication or call setApplication.");
        }
        return sInstance;
    }

    /**
     * 当主工程没有继承BaseApplication时，可以使用setApplication方法初始化Application
     */
    public static void setApplication(@NonNull Application application) {
        sInstance = application;
        application.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                AppManager.getAppManager().addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
                SystemReport.onPageStart(activity, activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityPaused(Activity activity) {
                SystemReport.onPageEnd(activity, activity.getClass().getSimpleName());
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                AppManager.getAppManager().removeActivity(activity);
            }
        });
    }
}
