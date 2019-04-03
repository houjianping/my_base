package com.siyuan.enjoyreading.util;

import android.text.TextUtils;

import com.androidapp.utils.SPUtils;

public class Storage {

    private static final String MODEL_USER = "model";
    private static final String USER_TOKEN = "token";

    public static String getUserToken() {
        return SPUtils.getSharedStringData(USER_TOKEN);
    }

    public static boolean isUserLogin() {
        return !TextUtils.isEmpty(getUserToken());
    }
}
