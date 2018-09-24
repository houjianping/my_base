package com.scwang.refreshlayout.ui.activity.login;

import android.os.Bundle;
import android.view.View;

import com.androidapp.base.utils.ToastUtils;
import com.androidapp.mvp.MvpBaseView;
import com.androidapp.mvp.MvpBaseActivity;
import com.scwang.refreshlayout.R;
import com.scwang.refreshlayout.bean.UserInfo;
import com.scwang.refreshlayout.model.user.LoginModelLogic;
import com.scwang.refreshlayout.presenter.user.LoginPresenterImpl;
import com.scwang.refreshlayout.presenter.user.interfaces.ILoginPresenter;

import java.util.List;

public class PassWordLoginActivity extends MvpBaseActivity<LoginModelLogic, LoginPresenterImpl> implements ILoginPresenter.View {

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
        ToastUtils.showShortToast(this, "-------");
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            doStartActivity(SecretLoginActivity.class, null);
        }
    };
}
