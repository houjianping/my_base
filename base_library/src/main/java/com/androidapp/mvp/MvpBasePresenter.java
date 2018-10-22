package com.androidapp.mvp;

import android.content.Context;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * 类描述：
 * 1.获取绑定View实例传递到子类中进行调用!
 * 2.注销View实例
 * 3.创建 Model 实例
 * 4.注销Model实例
 * 5.通过RxJava进行绑定activity和fragment生命周期绑定
 **/
public class MvpBasePresenter<V extends MvpBaseView, M extends MvpBaseModel> implements MvpPresenter<V, M> {

    protected Context mContext;

    protected V mView;

    protected M mModel;

    protected CompositeSubscription mCompositeSubscription;

    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }

    protected void addSubscribe(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }

    //    获取绑定View实例
    @Override
    public void attachView(V view) {
        this.mView = view;
    }

    //    获取绑定Model层实例
    @Override
    public void attachModel(M m) {
        this.mModel = m;
    }


    public M getModel() {
        return mModel;
    }

    //    注销mModel实例
    @Override
    public void detachModel() {
        this.mModel = null;
    }

    //    注销View实例
    @Override
    public void detachView() {
        this.mView = null;
        unSubscribe();
    }

    public V getView() {
        return mView;
    }

    public boolean isViewBind() {
        return mView != null;
    }
}
