package com.scwang.refreshlayout.ui.activity.pcenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androidapp.base.activity.BaseActivity;
import com.scwang.refreshlayout.R;

public class SettingActivity extends BaseActivity {

    @Override
    protected void initContentView(Bundle bundle) {
    }

    @Override
    protected void initView() {
        setContentView(R.layout.personal_settings);
        findViewById(R.id.setting_account).setOnClickListener(mOnClickListener);
        findViewById(R.id.setting_push_control).setOnClickListener(mOnClickListener);
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
            }
        }
    };
}
