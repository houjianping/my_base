package com.siyuan.enjoyreading.model.user;

import android.content.Context;

import com.siyuan.enjoyreading.entity.UserInfo;
import com.siyuan.enjoyreading.presenter.user.interfaces.ILoginPresenter;

import java.util.List;

import rx.Observable;

public class LoginModelLogic implements ILoginPresenter.Model{
    @Override
    public Observable<List<UserInfo>> getHomeCate(Context context, String identification) {
        return null;
    }
}
