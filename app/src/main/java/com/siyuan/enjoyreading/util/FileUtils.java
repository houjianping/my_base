package com.siyuan.enjoyreading.util;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.siyuan.enjoyreading.BuildConfig;

import java.io.File;

public class FileUtils {
    /**
     * URI转换
     * @param context
     * @param file
     * @return
     */
    public static Uri getUriForFile(Context context, File file) {
        Uri contentUri;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {//判读版本是否在7.0以上
            String providerName = BuildConfig.APPLICATION_ID + ".fileProvider";
            contentUri = FileProvider.getUriForFile(context, providerName, file);
        } else {
            contentUri = Uri.fromFile(file);
        }
        return contentUri;
    }
}
