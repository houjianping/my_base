package com.siyuan.enjoyreading.api.request;

import android.app.Application;
import android.support.annotation.NonNull;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.Progress;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

public class HttpRequest {

    /**
     * 初始化网络请求框架
     */
    public static void initOkGo(Application application, boolean devMode) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //-----------------------------------LOG日志显示模块-----------------------------------------------------//
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(devMode ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        loggingInterceptor.setColorLevel(devMode ? Level.INFO : Level.OFF);
        builder.addInterceptor(loggingInterceptor);
        //--------------------------------------超时时间设置--------------------------------------------------//
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);   //全局的连接超时时间
        //--------------------------------------Cookie设置--------------------------------------------------//
        builder.cookieJar(new CookieJarImpl(new DBCookieStore(application)));           //使用数据库保持cookie，如果cookie不过期，则一直有效.如 SPCookieStore MemoryCookieStore
        OkGo.getInstance().init(application)
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置会使用默认的
                .setCacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(0);                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
    }

    /**
     * 异步get
     *
     * @param url      get请求地址
     * @param params   get参数
     * @param callBack 回调
     */
    public <T> void asyncGet(@NonNull String url, @NonNull Map<String, String> params, @NonNull final JsonCallback<T> callBack) {
        OkGo.<T>get(url).params(params).execute(callBack);
    }

    /**
     * 异步post
     *
     * @param url      post请求地址
     * @param params   post请求参数
     * @param callBack 回调
     */
    public <T> void asyncPost(@NonNull String url, @NonNull Map<String, String> params, @NonNull final JsonCallback<T> callBack) {
        OkGo.<T>post(url).params(params).execute(callBack);
    }

    /**
     * 下载
     *
     * @param url      下载地址
     * @param path     文件保存路径
     * @param fileName 文件名称
     * @param callback 回调
     */
    public void download(@NonNull String url, @NonNull String path, @NonNull String fileName, @NonNull final HttpRequestCallback.FileCallback callback) {
        OkGo.<File>get(url).execute(new com.lzy.okgo.callback.FileCallback(path, fileName) {
            @Override
            public void onSuccess(com.lzy.okgo.model.Response<File> response) {
                callback.onResponse(response.body());
            }

            @Override
            public void onStart(com.lzy.okgo.request.base.Request<File, ? extends com.lzy.okgo.request.base.Request> request) {
                super.onStart(request);
                callback.onBefore();
            }

            @Override
            public void onError(com.lzy.okgo.model.Response<File> response) {
                super.onError(response);
                callback.onError("异常");
            }

            @Override
            public void downloadProgress(Progress progress) {
                super.downloadProgress(progress);

                callback.onProgress(progress.fraction, progress.totalSize);
            }
        });
    }
}
