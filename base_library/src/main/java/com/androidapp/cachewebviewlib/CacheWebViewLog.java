package com.androidapp.cachewebviewlib;

import android.util.Log;

import com.androidapp.cachewebviewlib.config.CacheConfig;

 class CacheWebViewLog {
    private static final String TAG="CacheWebView";


    public static void d(String log){
        if (CacheConfig.getInstance().isDebug()){
            Log.d(TAG,log);
        }
    }
}
