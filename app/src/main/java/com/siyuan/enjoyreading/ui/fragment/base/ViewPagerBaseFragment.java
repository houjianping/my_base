package com.siyuan.enjoyreading.ui.fragment.base;

import android.os.Bundle;
import android.util.Log;

import com.androidapp.fragment.BaseFragment;

public abstract class ViewPagerBaseFragment extends BaseFragment {

    /**
     * Fragment是否已经绑定
     */
    protected boolean isViewInitiated;
    /**
     * 用户是否可见
     */
    protected boolean isVisibleToUser;
    /**
     * 是否绑定数据
     */
    protected boolean isDataInitiated;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        isViewInitiated = true;
        prepareFetchData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isVisibleToUser = isVisibleToUser;
        prepareFetchData();
        onVisibleChange(isVisibleToUser);
    }

    public boolean prepareFetchData() {
        if (isVisibleToUser && isViewInitiated && (!isDataInitiated)) {
            loadData(true);
            isDataInitiated = true;
            return true;
        }
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.e("","#######onResume#########" + getClass().getSimpleName());
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("","#######onPause#########" + getClass().getSimpleName());
    }

    protected void onVisibleChange(boolean isVisibleToUser) {
    };
}
