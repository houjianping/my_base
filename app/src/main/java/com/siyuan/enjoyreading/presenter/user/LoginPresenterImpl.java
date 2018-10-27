package com.siyuan.enjoyreading.presenter.user;

import com.siyuan.enjoyreading.presenter.user.interfaces.ILoginPresenter;

public class LoginPresenterImpl extends ILoginPresenter.Presenter{
    @Override
    public void getHomeCate(String identification) {
//        mModel.getHomeCate("")
        mView.getOtherList(null);
    }
}
