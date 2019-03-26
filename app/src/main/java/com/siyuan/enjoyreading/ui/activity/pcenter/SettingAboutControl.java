package com.siyuan.enjoyreading.ui.activity.pcenter;

import android.os.Bundle;
import android.view.View;

import com.androidapp.activity.BaseActivity;
import com.androidapp.upgrade.UpdateAppManager;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.api.Urls;
import com.siyuan.enjoyreading.api.request.HttpRequest;
import com.siyuan.enjoyreading.ui.activity.currency.AppWebActivity;

public class SettingAboutControl extends BaseActivity {

    private String mUpdateUrl = "https://raw.githubusercontent.com/WVector/AppUpdateDemo/master/json/json.txt";
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
                    showLoading(true);
                    new UpdateAppManager
                            .Builder()
                            .setActivity(SettingAboutControl.this)
                            .setUpdateUrl(mUpdateUrl)
                            .setHttpManager(new HttpRequest())
                            .build()
                            .update();
                    break;
                case R.id.settings_contact_us:
                    doStartActivity(AppWebActivity.class, AppWebActivity.getBundle(Urls.H5.CONTACT_US, "联系我们"));
                    break;
                case R.id.settings_terms_of_service:
                    doStartActivity(AppWebActivity.class, AppWebActivity.getBundle("http://192.168.3.249:3007/", "服务条款"));
//                    doStartActivity(AppWebActivity.class, AppWebActivity.getBundle("http://m.54php.cn/demo/h5_upload", "服务条款"));
                    break;
            }
        }
    };
}
