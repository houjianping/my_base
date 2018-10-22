package com.androidapp.mvp;

public interface MvpPresenter<View,Model> {
    //    绑定View控件
    void attachView(View view);
    //    绑定Model
    void attachModel(Model model);
    //    注销View控件
    void detachView();
    //    注销Model对象
    void detachModel();
}
