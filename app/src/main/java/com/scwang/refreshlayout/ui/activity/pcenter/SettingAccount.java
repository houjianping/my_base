package com.scwang.refreshlayout.ui.activity.pcenter;

import android.os.Bundle;
import android.view.View;

import com.androidapp.base.activity.BaseActivity;
import com.scwang.refreshlayout.R;

public class SettingAccount extends BaseActivity {

    @Override
    protected void initContentView(Bundle bundle) {
    }

    @Override
    protected void initView() {
        setContentView(R.layout.personal_account);
        findViewById(R.id.setting_modify_pwd).setOnClickListener(mOnClickListener);
    }

    @Override
    protected void initData() {
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.setting_modify_pwd:
                    startActivity(SettingModifyPwd.class);
                    break;
            }
        }
    };
}
