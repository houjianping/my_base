package com.scwang.refreshlayout.ui.activity.login;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.androidapp.mvp.MvpBaseActivity;
import com.androidapp.mvp.MvpBaseView;
import com.scwang.refreshlayout.R;
import com.scwang.refreshlayout.entity.UserInfo;
import com.scwang.refreshlayout.model.user.LoginModelLogic;
import com.scwang.refreshlayout.presenter.user.LoginPresenterImpl;
import com.scwang.refreshlayout.presenter.user.interfaces.ILoginPresenter;

import java.util.List;

public class PassWordLoginActivity extends MvpBaseActivity<LoginModelLogic, LoginPresenterImpl> implements ILoginPresenter.View {

    private TextView mCreateAccountTextView;

    @Override
    protected int getLayoutId() {
        return R.layout.login_pwd;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        mPresenter.getHomeCate("");
        if (mTitleBar != null) {
            mTitleBar.getRightTextView().setOnClickListener(mOnClickListener);
        }
        mCreateAccountTextView = findViewById(R.id.tv_create_account);
        mCreateAccountTextView.setOnClickListener(mOnClickListener);
    }

    @Override
    protected void onEvent() {
    }

    @Override
    protected MvpBaseView getView() {
        return this;
    }

    @Override
    public void getOtherList(List<UserInfo> homeCates) {
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v ==  mTitleBar.getRightTextView()) {
                doStartActivity(SecretLoginActivity.class, null);
            } else if (v == mCreateAccountTextView) {
                doStartActivity(CreateAccountActivity.class, null);
            }
        }
    };
}
