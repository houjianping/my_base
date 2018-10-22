package com.androidapp.share;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.weixin.view.WXCallbackActivity;

public class ShareConfig {

    private static final String TAG = "thirdPartApp";

    public static void onCreate(Application application) {
        UMConfigure.setLogEnabled(BuildConfig.DEBUG);
        final String channel = getMetaValue(application, "UMENG_CHANNEL");
        UMConfigure.init(application, getString(application, R.string.UM_KEY), channel, UMConfigure.DEVICE_TYPE_PHONE, "");
        //初始化QQ参数
        String qqId = getString(application, R.string.QQ_ID);
        String qqKey = getString(application, R.string.QQ_KEY);
        PlatformConfig.setQQZone(qqId, qqKey);
        if (BuildConfig.DEBUG) {
            Log.d(TAG, String.format("QQ_ID :%s\tQQ_KEY :%s", qqId, qqKey));
        }

        //初始化微信参数
        String wxId = getString(application, R.string.WEIXIN_ID);
        String wxKey = getString(application, R.string.WEIXIN_KEY);
        PlatformConfig.setWeixin(wxId, wxKey);
        Log.d(TAG, String.format("WX_ID :%s\tWX_KEY :%s", wxId, wxKey));

        //初始化微博参数
        String wbId = getString(application, R.string.WEIBO_ID);
        String wbSecret = getString(application, R.string.WEIBO_SECRET);
        String wbUrl = getString(application, R.string.WEIBO_URL);
        PlatformConfig.setSinaWeibo(wbId, wbSecret, wbUrl);
        Log.d(TAG, String.format("WB_Id :%s\tWB_Key :%s\tWB_URL :%s", wbId, wbSecret, wbUrl));

        UMShareAPI.get(application);
    }

    private static String getString(Application application, int id) {
        return application.getString(id);
    }

    // 获取ApiKey
    private static String getMetaValue(Context context, String metaKey) {
        Bundle metaData = null;
        Object apiKey = null;
        if (context == null || metaKey == null) {
            return null;
        }
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(),
                    PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                apiKey = metaData.get(metaKey);
            }
            if (apiKey != null) {
                if (apiKey instanceof String) {
                    return String.valueOf(apiKey);
                } else if (apiKey instanceof Integer) {
                    return String.valueOf(apiKey);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(apiKey);
    }
}
