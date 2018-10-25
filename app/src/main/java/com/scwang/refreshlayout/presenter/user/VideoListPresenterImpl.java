package com.scwang.refreshlayout.presenter.user;

import com.androidapp.utils.ToastUtils;
import com.scwang.refreshlayout.bean.VideoItem;
import com.scwang.refreshlayout.bean.circle.CircleItem;
import com.scwang.refreshlayout.presenter.user.interfaces.IVideoListPresenter;
import com.scwang.refreshlayout.presenter.user.interfaces.IZonePresenter;

import java.util.List;

import rx.Subscriber;

public class VideoListPresenterImpl extends IVideoListPresenter.Presenter {
    @Override
    public void getVideoListItems(int page) {
        mModel.getVideoListItems(mContext, 1).subscribe(new Subscriber<List<VideoItem>>() {
            @Override
            public void onCompleted() {
                ToastUtils.show("--1--onCompleted--1-");
            }
            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                ToastUtils.show("--1--onError--1-");
            }

            @Override
            public void onNext(List<VideoItem> result) {
                ToastUtils.show("--1----1-");
                if (result != null) {
                    try {
                        mView.onVideoListUpdate(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
