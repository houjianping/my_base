package com.scwang.refreshlayout.presenter.user;

import com.scwang.refreshlayout.presenter.user.interfaces.ILoginPresenter;

public class LoginPresenterImpl extends ILoginPresenter.Presenter{
    @Override
    public void getHomeCate(String identification) {
//        mModel.getHomeCate("")
        mView.getOtherList(null);
    }
}
