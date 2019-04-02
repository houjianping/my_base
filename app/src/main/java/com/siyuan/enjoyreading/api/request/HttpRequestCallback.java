package com.siyuan.enjoyreading.api.request;

import java.io.File;

public class HttpRequestCallback {
    /**
     * 网络请求回调
     */
    public interface Callback {

        /**
         * 开始调用网络请求
         */
        void onStart();
        /**
         * 结果回调
         *
         * @param result 结果
         */
        void onResponse(com.lzy.okgo.model.Response result);

        /**
         * 错误回调
         *
         * @param error 错误提示
         */
        void onError(String error);

        /**
         * 结束请求
         */
        void onFinish();
    }

    /**
     * 下载回调
     */
    public interface FileCallback {
        /**
         * 进度
         *
         * @param progress 进度0.00 - 0.50  - 1.00
         * @param total    文件总大小 单位字节
         */
        void onProgress(float progress, long total);

        /**
         * 错误回调
         *
         * @param error 错误提示
         */
        void onError(String error);

        /**
         * 结果回调
         *
         * @param file 下载好的文件
         */
        void onResponse(File file);

        /**
         * 请求之前
         */
        void onBefore();
    }
}
