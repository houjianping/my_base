package com.scwang.refreshlayout.request;

import android.app.Application;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.GetRequest;
import com.lzy.okgo.request.base.Request;
import com.scwang.refreshlayout.bean.BaseBean;
import com.scwang.refreshlayout.bean.Test;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import okhttp3.OkHttpClient;

public class OkGoRequest {

    /**
     * 初始化网络请求框架
     */
    public static void initOkGo(Application application, boolean devMode) {
        HttpParams params = new HttpParams();
        params.put("commonParamsKey1", "commonParamsValue1");     //param支持中文,直接传,不要自己编码
        params.put("commonParamsKey2", "这里支持中文参数");
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
     * 全局公共头
     *
     * @param headers
     */
    public static void addCommonHeaders(HttpHeaders headers) {
        OkGo.getInstance().getCommonHeaders().clear();
        OkGo.getInstance().addCommonHeaders(headers);
    }

    /**
     * 全局公共参数
     *
     * @param params
     */
    public static void addCommonParams(HttpParams params) {
        OkGo.getInstance().getCommonParams().clear();
        OkGo.getInstance().addCommonParams(params);
    }

    public static void doGetRequest(String url, Map<String, String> params) {
        final GetRequest<BaseBean<Test>> request = OkGo.<BaseBean<Test>>get(url).params(params).cacheKey(url);
        request.execute(new JsonCallback<BaseBean<Test>>() {
            @Override
            public void onSuccess(Response<BaseBean<Test>> response) {
                Log.e("", "@@@@@@doGetRequest@@@@@@onSuccess@@@" + (response.body().getNu()));
            }

            @Override
            public void onStart(Request<BaseBean<Test>, ? extends Request> request) {
                super.onStart(request);
            }

            @Override
            public void onCacheSuccess(Response<BaseBean<Test>> response) {
                super.onCacheSuccess(response);
                Log.e("", "@@@@@@doGetRequest@@@@@@onCacheSuccess@@@" + response.body().getNu());
            }

            @Override
            public void onError(Response<BaseBean<Test>> response) {
                super.onError(response);
            }
        });
    }
}
