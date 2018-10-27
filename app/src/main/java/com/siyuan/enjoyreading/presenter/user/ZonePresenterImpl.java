package com.siyuan.enjoyreading.presenter.user;

import com.androidapp.utils.ToastUtils;
import com.siyuan.enjoyreading.entity.circle.CircleItem;
import com.siyuan.enjoyreading.presenter.user.interfaces.IZonePresenter;

import java.util.List;

import rx.Subscriber;

public class ZonePresenterImpl extends IZonePresenter.Presenter {
    @Override
    public void getCircleListItems(int page) {
        mModel.getCircleListItems(mContext, 1).subscribe(new Subscriber<List<CircleItem>>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(List<CircleItem> result) {
                if (result != null) {
                    try {
                        mView.onCircleListUpdate(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
