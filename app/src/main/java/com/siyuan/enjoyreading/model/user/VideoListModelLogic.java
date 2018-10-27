package com.siyuan.enjoyreading.model.user;

import android.content.Context;

import com.androidapp.utils.RxSchedulers;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siyuan.enjoyreading.api.ApiConfig;
import com.siyuan.enjoyreading.entity.VideoItem;
import com.siyuan.enjoyreading.presenter.user.interfaces.IVideoListPresenter;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class VideoListModelLogic implements IVideoListPresenter.Model {
    @Override
    public Observable<List<VideoItem>> getVideoListItems(Context context, int page) {
        return Observable.create(new Observable.OnSubscribe<List<VideoItem>>() {
            @Override
            public void call(Subscriber<? super List<VideoItem>> subscriber) {
                final List<VideoItem> circleItems = new Gson().fromJson(ApiConfig.JSON_VIDEO_LIST, new TypeToken<ArrayList<VideoItem>>() {
                }.getType());
                subscriber.onNext(circleItems);
                subscriber.onCompleted();
            }
        }).compose(RxSchedulers.<List<VideoItem>>io_main());
    }
}
