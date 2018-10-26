package com.scwang.refreshlayout.model.user;

import android.content.Context;

import com.androidapp.utils.RxSchedulers;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.scwang.refreshlayout.api.ApiConfig;
import com.scwang.refreshlayout.entity.circle.CircleItem;
import com.scwang.refreshlayout.presenter.user.interfaces.IZonePresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import rx.Observable;
import rx.Subscriber;

public class ZoneModelLogic implements IZonePresenter.Model {
    @Override
    public Observable<List<CircleItem>> getCircleListItems(Context context, int page) {
        return Observable.create(new Observable.OnSubscribe<List<CircleItem>>() {
            @Override
            public void call(Subscriber<? super List<CircleItem>> subscriber) {
                final List<CircleItem> circleItems = new Gson().fromJson(ApiConfig.JSON_ZONE_LIST, new TypeToken<ArrayList<CircleItem>>() {
                }.getType());
                for (int i = 0; i < circleItems.size(); i++) {
                    circleItems.get(i).setPictures(ApiConfig.getRandomPhotoUrlString(new Random().nextInt(9)));
                }
                subscriber.onNext(circleItems);
                subscriber.onCompleted();
            }
        }).compose(RxSchedulers.<List<CircleItem>>io_main());
    }
}
