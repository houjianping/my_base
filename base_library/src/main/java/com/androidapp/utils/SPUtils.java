package com.androidapp.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * 对SharedPreference文件中的各种类型的数据进行存取操作
 *
 */
public class SPUtils {

    private static SharedPreferences sp;

    public static void init(Context context) {
        if (sp == null) {
            sp = PreferenceManager.getDefaultSharedPreferences(context);
        }
    }

    public static void setSharedIntData(String key, int value) {
        sp.edit().putInt(key, value).apply();
    }

    public static int getSharedIntData(String key) {
        if (sp == null) {
            LogUtil.d("SPUtils need init");
            return 0;
        }
        return sp.getInt(key, 0);
    }

    public static void setSharedlongData(String key, long value) {
        if (sp == null) {
            LogUtil.d("SPUtils need init");
            return;
        }
        sp.edit().putLong(key, value).apply();
    }

    public static long getSharedlongData(String key) {
        if (sp == null) {
            LogUtil.d("SPUtils need init");
            return 0l;
        }
        return sp.getLong(key, 0l);
    }

    public static void setSharedFloatData(String key, float value) {
        if (sp == null) {
            LogUtil.d("SPUtils need init");
            return;
        }
        sp.edit().putFloat(key, value).apply();
    }

    public static Float getSharedFloatData(String key) {
        if (sp == null) {
            LogUtil.d("SPUtils need init");
            return 0f;
        }
        return sp.getFloat(key, 0f);
    }

    public static void setSharedBooleanData(String key, boolean value) {
        if (sp == null) {
            LogUtil.d("SPUtils need init");
            return;
        }
        sp.edit().putBoolean(key, value).apply();
    }

    public static Boolean getSharedBooleanData(String key) {
        if (sp == null) {
            LogUtil.d("SPUtils need init");
            return false;
        }
        return sp.getBoolean(key, false);
    }

    public static void setSharedStringData(String key, String value) {
        if (sp == null) {
            LogUtil.d("SPUtils need init");
            return;
        }
        sp.edit().putString(key, value).apply();
    }

    public static String getSharedStringData(String key) {
        if (sp == null) {
            LogUtil.d("SPUtils need init");
            return "";
        }
        return sp.getString(key, "");
    }

}