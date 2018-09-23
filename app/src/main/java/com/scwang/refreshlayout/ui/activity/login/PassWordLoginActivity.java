package com.scwang.refreshlayout.ui.activity.login;

import android.os.Bundle;
import android.view.View;

import com.androidapp.base.activity.BaseActivity;
import com.scwang.refreshlayout.R;

public class PassWordLoginActivity extends BaseActivity {

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.login_pwd);
    }

    @Override
    protected void initView() {
        if (mTitleBar != null) {
            mTitleBar.getRightTextView().setOnClickListener(mOnClickListener);
        }
    }

    @Override
    protected void initData() {
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            doStartActivity(SecretLoginActivity.class, null);
        }
    };
}
