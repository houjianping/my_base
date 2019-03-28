package com.androidapp.systemreport;

import android.app.Application;
import android.content.Context;

import com.tendcloud.tenddata.TCAgent;

import java.util.Map;

public class SystemReport {

    private static Application sApplication;

    public static void init(Application application, String appId, String channel, boolean debug) {
        sApplication = application;
        TCAgent.LOG_ON = debug;
        TCAgent.init(application, appId, channel);
        TCAgent.setReportUncaughtExceptions(true);
    }

    public static void onEvent(String event, String eventDes) {
        TCAgent.onEvent(sApplication, event, eventDes);
    }

    public static void onEvent(String event, String eventDes, Map<String, String> map) {
        TCAgent.onEvent(sApplication, event, eventDes, map);
    }

    public static void onPageStart(Context context, String pageName) {
        TCAgent.onPageStart(context, pageName);
    }

    public static void onPageEnd(Context context, String pageName) {
        TCAgent.onPageEnd(context, pageName);
    }
}
