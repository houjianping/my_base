package com.androidapp.filter;

import com.androidapp.filter.multiple.bean.MultiBean;
import com.androidapp.filter.single.bean.SignalBean;

import java.util.List;

public class FilterHeaderItem {

    private String key;
    private String title;
    private List<MultiBean> mDictList;
    private List<SignalBean> mSignalBean;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MultiBean> getmDictList() {
        return mDictList;
    }

    public void setmDictList(List<MultiBean> mDictList) {
        this.mDictList = mDictList;
    }

    public List<SignalBean> getmSignalBean() {
        return mSignalBean;
    }

    public void setmSignalBean(List<SignalBean> mSignalBean) {
        this.mSignalBean = mSignalBean;
    }

    public boolean isSelectable() {
        return mDictList != null || mSignalBean != null;
    }
}
