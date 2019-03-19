package com.siyuan.enjoyreading.util;

import android.app.Activity;

import com.androidapp.share.bean.ShareContent;
import com.androidapp.share.bean.ShareEnum;
import com.androidapp.share.util.ShareHelper;
import com.siyuan.enjoyreading.R;

public class ShareUtil {

    public static void share(Activity context) {
        ShareHelper shareUtil = new ShareHelper(context, "分享标题", R.mipmap.ic_launcher);
        shareUtil.setShareCallback(new ShareHelper.ShareCallback() {
            @Override
            public void onShareStart(ShareEnum shareEnum) {
            }

            @Override
            public void onShareSuccess(ShareEnum shareEnum) {
            }

            @Override
            public void onShareFailed(ShareEnum shareEnum) {
            }

            @Override
            public void onShareCancel(ShareEnum shareEnum) {
            }
        });
        ShareContent shareContent = new ShareContent();
        shareContent.setUrl("");
        shareContent.setTitle("");
        shareContent.setLogo("");
        shareContent.setText("");
        shareContent.setShareObject(1);
        shareUtil.show(shareContent);
    }
}
