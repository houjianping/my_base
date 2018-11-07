package com.siyuan.enjoyreading.util;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.androidapp.utils.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siyuan.enjoyreading.App;
import com.siyuan.enjoyreading.ui.activity.pcenter.SettingAboutControl;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class IntentUtil {

    public static Intent getIntent(String json) {
        return getIntent(App.getInstance(), json);
    }

    private static Intent getIntent(Context context, String json) {
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
}
