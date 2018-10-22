package com.scwang.refreshlayout.util;

import android.text.TextUtils;

import com.androidapp.utils.SharedPreferencesUtil;
import com.scwang.refreshlayout.App;

public class Storage {

    private static final String MODEL_USER = "model";
    private static final String USER_TOKEN = "token";

    public static String getUserToken() {
        return SharedPreferencesUtil.get(App.getContext(), MODEL_USER, USER_TOKEN);
    }

    public static boolean isUserLogin() {
        return !TextUtils.isEmpty(getUserToken());
    }
}
