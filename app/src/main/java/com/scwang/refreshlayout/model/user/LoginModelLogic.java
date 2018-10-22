package com.scwang.refreshlayout.model.user;

import android.content.Context;

import com.scwang.refreshlayout.bean.UserInfo;
import com.scwang.refreshlayout.presenter.user.interfaces.ILoginPresenter;

import java.util.List;

import rx.Observable;

public class LoginModelLogic implements ILoginPresenter.Model{
    @Override
    public Observable<List<UserInfo>> getHomeCate(Context context, String identification) {
        return null;
    }
}
