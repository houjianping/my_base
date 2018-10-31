package com.siyuan.enjoyreading.ui.activity.pcenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androidapp.activity.BaseActivity;
import com.androidapp.share.bean.ShareContent;
import com.androidapp.share.bean.ShareEnum;
import com.androidapp.share.util.ShareUtil;
import com.androidapp.widget.CustomAlertDialog;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.ui.activity.currency.YuedanWebActivity;

import static com.siyuan.enjoyreading.api.Urls.H5.FEEDBACK_CONTROL;

public class SettingActivity extends BaseActivity {

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.act_personal_settings);
    }

    @Override
    protected void initView() {
        findViewById(R.id.setting_account).setOnClickListener(mOnClickListener);
        findViewById(R.id.setting_push_control).setOnClickListener(mOnClickListener);
        findViewById(R.id.setting_download_control).setOnClickListener(mOnClickListener);
        findViewById(R.id.setting_feedback_control).setOnClickListener(mOnClickListener);
        findViewById(R.id.setting_share_control).setOnClickListener(mOnClickListener);
        findViewById(R.id.setting_about_control).setOnClickListener(mOnClickListener);
        findViewById(R.id.setting_clear_control).setOnClickListener(mOnClickListener);
    }

    @Override
    protected void initData() {
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.setting_account:
                    startActivity(new Intent(mContext, SettingAccount.class));
                    break;
                case R.id.setting_push_control:
                    startActivity(new Intent(mContext, SettingPushControl.class));
                    break;
                case R.id.setting_download_control:
                    startActivity(new Intent(mContext, SettingDownloadControl.class));
                    break;
                case R.id.setting_feedback_control:
                    doStartActivity(YuedanWebActivity.class, YuedanWebActivity.getBundle(FEEDBACK_CONTROL, "帮助与反馈"));
                    break;
                case R.id.setting_clear_control:
                    clearCache();
                    break;
                case R.id.setting_share_control:
                    share();
                    break;
                case R.id.setting_about_control:
                    startActivity(new Intent(mContext, SettingAboutControl.class));
                    break;
            }
        }
    };

    private void share() {
        ShareUtil shareUtil = new ShareUtil(mContext, "分享标题", R.mipmap.ic_launcher);
        shareUtil.setShareCallback(new ShareUtil.ShareCallback() {
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

    private void clearCache() {
        final CustomAlertDialog dialog = new CustomAlertDialog(mContext, false);
        dialog.setTitle("清除缓存");
        dialog.setMessage("确认清除App缓存? 清除缓存后个别页面可能访问过慢", getResources().getColor(R.color.black_main));
        dialog.setPositiveButton("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setNegativeButton("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}
