package com.scwang.refreshlayout.presenter.user.interfaces;

import android.content.Context;

import com.androidapp.mvp.MvpBaseModel;
import com.androidapp.mvp.MvpBasePresenter;
import com.androidapp.mvp.MvpBaseView;
import com.scwang.refreshlayout.bean.VideoItem;

import java.util.List;

import rx.Observable;

public interface IVideoListPresenter {
    interface View extends MvpBaseView {
        void onVideoListUpdate(List<VideoItem> videoItemList);
    }

    interface  Model extends MvpBaseModel {
        Observable<List<VideoItem>> getVideoListItems(Context context, int page);
    }

    abstract class Presenter extends MvpBasePresenter<View,Model> {
        public abstract void getVideoListItems(int page);
    }
}
