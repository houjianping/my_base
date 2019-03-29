package com.siyuan.enjoyreading.ui.activity.pcenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androidapp.activity.BaseActivity;
import com.androidapp.widget.AppAlertDialog;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.ui.activity.currency.AppWebActivity;
import com.siyuan.enjoyreading.util.ShareUtil;

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
                    doStartActivity(SettingAccount.class);
                    break;
                case R.id.setting_push_control:
                    doStartActivity(SettingPushControl.class);
                    break;
                case R.id.setting_download_control:
                    doStartActivity(SettingDownloadControl.class);
                    break;
                case R.id.setting_feedback_control:
                    doStartActivity(AppWebActivity.class, AppWebActivity.getBundle(FEEDBACK_CONTROL, "帮助与反馈"));
                    break;
                case R.id.setting_clear_control:
                    clearCache();
                    break;
                case R.id.setting_share_control:
                    ShareUtil.share(SettingActivity.this);
                    break;
                case R.id.setting_about_control:
                    doStartActivity(SettingAboutControl.class);
                    break;
            }
        }
    };

    private void clearCache() {
        final AppAlertDialog dialog = new AppAlertDialog(mContext, false);
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
