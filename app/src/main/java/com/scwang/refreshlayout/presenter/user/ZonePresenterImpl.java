package com.scwang.refreshlayout.presenter.user;

import com.androidapp.utils.ToastUtils;
import com.scwang.refreshlayout.bean.circle.CircleItem;
import com.scwang.refreshlayout.presenter.user.interfaces.IZonePresenter;

import java.util.List;

import rx.Subscriber;

public class ZonePresenterImpl extends IZonePresenter.Presenter {
    @Override
    public void getCircleListItems(int page) {
        mModel.getCircleListItems(mContext, 1).subscribe(new Subscriber<List<CircleItem>>() {
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
            public void onNext(List<CircleItem> result) {
                ToastUtils.show("--1----1-");
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
