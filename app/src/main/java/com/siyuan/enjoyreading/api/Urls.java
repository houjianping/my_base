package com.siyuan.enjoyreading.api;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.siyuan.enjoyreading.api.request.JsonCallback;

public class Urls {

    private static boolean isDevMode() {
        return true;
    }

    public static String getBaseUrl() {
        String BASE_URL_ONLINE = "";
        String BASE_URL_DEV = "";
        return isDevMode() ? BASE_URL_DEV : BASE_URL_ONLINE;
    }

    public static class H5 {
        // 分享赚钱
        public static String SHARE_REWARD = "";
        // 邀请好友
        public static String INVITING_FRIENDS = "";
        //联系我们
        public static String CONTACT_US = "";
        //服务条款
        public static String TERMS_OF_SERVICE = "http://m.lrts.me/h5/help/privacy_android?uid=253157424&mparam=9UIOzbldSimtgacznv75IXLcvSwtmfTI1RwOh1a1lL7CTwSPwxJeIeHp46c0g6X%2BVFuayucMrs2merUidWz6eoFr0LyGhSW7K%2BTAcGWgJQc%3D";
        //帮助与反馈
        public static String FEEDBACK_CONTROL = "http://m.lrts.me/h5/help/index";
    }

    public <T> void  test(String url, HttpParams params) {
        OkGo.<T>get(url).params(params).execute(new JsonCallback<T>() {
            @Override
            public void onSuccess(Response<T> response) {
            }

            @Override
            public void onStart(Request<T, ? extends Request> request) {
                super.onStart(request);
            }

            @Override
            public void onCacheSuccess(Response<T> response) {
                super.onCacheSuccess(response);
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }

            @Override
            public void onError(Response<T> response) {
                super.onError(response);
            }
        });
    }
}
