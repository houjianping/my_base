package com.yuedan.zxing.common;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;

/**
 * 兼容低版本的子线程开启任务
 *
 * @author hugo
 */
public class Runnable {

    @SuppressWarnings("unchecked")
    public static void execAsync(AsyncTask<?, ?, ?> task) {
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
