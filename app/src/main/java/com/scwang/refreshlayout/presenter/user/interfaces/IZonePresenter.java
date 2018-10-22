package com.scwang.refreshlayout.presenter.user.interfaces;

import android.content.Context;

import com.androidapp.mvp.MvpBaseModel;
import com.androidapp.mvp.MvpBasePresenter;
import com.androidapp.mvp.MvpBaseView;
import com.scwang.refreshlayout.bean.UserInfo;
import com.scwang.refreshlayout.bean.circle.CircleItem;

import java.util.List;

import rx.Observable;

public interface IZonePresenter {
    interface View extends MvpBaseView {
        void onCircleListUpdate(List<CircleItem> circleItemList);
    }

    interface  Model extends MvpBaseModel {
        Observable<List<CircleItem>> getCircleListItems(Context context, int page);
    }

    abstract class Presenter extends MvpBasePresenter<View,Model> {
        public abstract void getCircleListItems(int page);
    }
}
