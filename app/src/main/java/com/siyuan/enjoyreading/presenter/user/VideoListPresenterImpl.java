package com.siyuan.enjoyreading.presenter.user;

import com.siyuan.enjoyreading.entity.NewsItem;
import com.siyuan.enjoyreading.presenter.user.interfaces.IVideoListPresenter;

import java.util.List;

import rx.Subscriber;

public class VideoListPresenterImpl extends IVideoListPresenter.Presenter {
    @Override
    public void getVideoListItems(int page) {
        mModel.getVideoListItems(mContext, 1).subscribe(new Subscriber<List<NewsItem>>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(List<NewsItem> result) {
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
