package com.siyuan.enjoyreading.ui.activity.login;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.androidapp.activity.BaseActivity;
import com.siyuan.enjoyreading.R;

public class PassWordLoginActivity extends BaseActivity {

    private TextView mCreateAccountTextView;

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mTitleBar.getRightTextView()) {
                doStartActivity(SecretLoginActivity.class, null);
            } else if (v == mCreateAccountTextView) {
                doStartActivity(CreateAccountActivity.class, null);
            }
        }
    };

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.act_account_login_pwd);
    }

    @Override
    protected void initView() {
        if (mTitleBar != null) {
            mTitleBar.getRightTextView().setOnClickListener(mOnClickListener);
        }
        mCreateAccountTextView = findViewById(R.id.tv_create_account);
        mCreateAccountTextView.setOnClickListener(mOnClickListener);
    }

    @Override
    protected void initData() {

    }
}
