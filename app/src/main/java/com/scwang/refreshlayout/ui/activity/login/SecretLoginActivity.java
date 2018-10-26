package com.scwang.refreshlayout.ui.activity.login;

import android.os.Bundle;

import com.androidapp.mvp.MvpBaseActivity;
import com.androidapp.mvp.MvpBaseView;
import com.scwang.refreshlayout.R;
import com.scwang.refreshlayout.entity.UserInfo;
import com.scwang.refreshlayout.model.user.LoginModelLogic;
import com.scwang.refreshlayout.presenter.user.LoginPresenterImpl;
import com.scwang.refreshlayout.presenter.user.interfaces.ILoginPresenter;

import java.util.List;

public class SecretLoginActivity extends MvpBaseActivity<LoginModelLogic, LoginPresenterImpl> implements ILoginPresenter.View {

    @Override
    protected int getLayoutId() {
        return R.layout.login_sms;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        mPresenter.getHomeCate("");
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
}
