package com.androidapp.base.utils;

import android.util.Log;

/**
 * 如果用于android平台，将信息记录到“LogCat”。如果用于java平台，将信息记录到“Console”
 * 使用logger封装
 */
public class LogUtils {
    public static boolean DEBUG_ENABLE =true;// 是否调试模式
    private static final String TAG = "MY_INFO";
    public static void logd(String message) {
        if (DEBUG_ENABLE) {
            Log.d(TAG, message);
        }
    }
}
