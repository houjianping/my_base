package com.siyuan.enjoyreading.ui.activity.pcenter;

import android.os.Bundle;
import android.view.View;

import com.androidapp.activity.BaseActivity;
import com.androidapp.widget.LoadingDialog;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.api.Urls;
import com.siyuan.enjoyreading.ui.activity.currency.AppWebActivity;

public class SettingAboutControl extends BaseActivity {

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.act_personal_about);
    }

    @Override
    protected void initView() {
        findViewById(R.id.settings_check_update).setOnClickListener(mOnClickListener);
        findViewById(R.id.settings_contact_us).setOnClickListener(mOnClickListener);
        findViewById(R.id.settings_terms_of_service).setOnClickListener(mOnClickListener);
    }

    @Override
    protected void initData() {
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.settings_check_update:
                    LoadingDialog.Builder loadBuilder = new LoadingDialog.Builder(mContext)
                            .setMessage("加载中...")
                            .setCancelable(true)
                            .setCancelOutside(true);
                    LoadingDialog dialog = loadBuilder.create();
                    dialog.show();
                    break;
                case R.id.settings_contact_us:
                    doStartActivity(AppWebActivity.class, AppWebActivity.getBundle(Urls.H5.CONTACT_US, "联系我们"));
                    break;
                case R.id.settings_terms_of_service:
                    doStartActivity(AppWebActivity.class, AppWebActivity.getBundle(Urls.H5.TERMS_OF_SERVICE, "服务条款"));
                    break;
            }
        }
    };
}
