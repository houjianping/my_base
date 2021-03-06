package com.androidapp.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.androidapp.base.R;
import com.androidapp.smartrefresh.layout.SmartRefreshLayout;
import com.androidapp.widget.LoadingLayout;


public class BaseWebFragment extends BaseFragment{

    private LinearLayout mWebViewContainer;
    protected LoadingLayout mLoadingLayout;
    protected SmartRefreshLayout smartRefreshLayout;

    @Override
    protected void loadData(boolean force) {
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mLoadingLayout = view.findViewById(R.id.loading);
        smartRefreshLayout = view.findViewById(R.id.refreshLayout);
        smartRefreshLayout.setEnableLoadMore(false);
        mWebViewContainer = view.findViewById(R.id.webContainer);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.app_browser_fragment;
    }

    protected void addWebView(View mWebView) {
        mWebViewContainer.removeAllViews();
        mWebViewContainer.addView(mWebView);
    }
}
