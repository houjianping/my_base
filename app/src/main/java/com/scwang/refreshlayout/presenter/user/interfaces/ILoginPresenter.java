package com.scwang.refreshlayout.presenter.user.interfaces;

import android.content.Context;

import com.androidapp.mvp.MvpBaseModel;
import com.androidapp.mvp.MvpBasePresenter;
import com.androidapp.mvp.MvpBaseView;
import com.scwang.refreshlayout.entity.UserInfo;

import java.util.List;

import rx.Observable;

public interface ILoginPresenter {
    interface View extends MvpBaseView {
        void getOtherList(List<UserInfo> homeCates);
    }

    interface  Model extends MvpBaseModel {
        Observable<List<UserInfo>> getHomeCate(Context context, String identification);
    }

    abstract class Presenter extends MvpBasePresenter<View,Model> {
        public abstract void getHomeCate(String identification);
    }
}
