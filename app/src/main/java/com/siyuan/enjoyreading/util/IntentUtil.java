package com.siyuan.enjoyreading.util;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.androidapp.utils.JsonUtils;
import com.androidapp.utils.ToastUtils;
import com.siyuan.enjoyreading.App;
import com.siyuan.enjoyreading.api.ApiConfig;
import com.siyuan.enjoyreading.ui.activity.pcenter.SettingAboutControl;

import java.util.Map;

public class IntentUtil {

    private static final String TAG = IntentUtil.class.getName();

    public static Intent getIntent(String json) {
        return getIntent(App.getInstance(), json);
    }

    private static Intent getIntent(Context context, String json) {
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        Map<String, Object> map = JsonUtils.getMap(json);
        Intent intent = new Intent();
        if (map.containsKey("page")) {
            String page = (String) map.get("page");
            if (page.equals("SettingAbout")) {
                intent.setClass(context, SettingAboutControl.class);
            }
            for (Object key : map.keySet()) {
                if (!intent.hasExtra(key.toString())) {
                    intent.putExtra(key.toString(), map.get(key).toString());
                }
            }
        }
        return intent;
    }

    public static void startActivity(Context context, String json) {
        if (TextUtils.isEmpty(json)) {
            Log.e(TAG,"跳转页面为空");
            if (ApiConfig.DEV_MODE) {
                ToastUtils.show("跳转页面为空");
            }
            return;
        }
        try {
            Intent intent = getIntent(json);
            if (intent != null) {
                context.startActivity(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
