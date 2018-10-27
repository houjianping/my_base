package com.siyuan.enjoyreading.ui.activity.login;

import android.os.Bundle;

import com.androidapp.mvp.MvpBaseActivity;
import com.androidapp.mvp.MvpBaseView;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.entity.UserInfo;
import com.siyuan.enjoyreading.model.user.LoginModelLogic;
import com.siyuan.enjoyreading.presenter.user.LoginPresenterImpl;
import com.siyuan.enjoyreading.presenter.user.interfaces.ILoginPresenter;

import java.util.List;

public class CreateAccountActivity extends MvpBaseActivity<LoginModelLogic, LoginPresenterImpl> implements ILoginPresenter.View {

    @Override
    protected int getLayoutId() {
        return R.layout.login_register;
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
