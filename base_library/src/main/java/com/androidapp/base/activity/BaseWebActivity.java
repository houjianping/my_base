package com.androidapp.base.activity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.androidapp.base.R;
import com.androidapp.base.utils.StatusBarUtil;
import com.androidapp.widget.CommonTitleBar;
import com.androidapp.smartrefresh.layout.SmartRefreshLayout;

import ezy.ui.layout.LoadingLayout;

public class BaseWebActivity extends BaseActivity {

    private LinearLayout mWebViewContainer;
    protected CommonTitleBar mCommonTitleBar;
    protected LoadingLayout mLoadingLayout;
    protected SmartRefreshLayout smartRefreshLayout;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.app_browser);
        StatusBarUtil.darkMode(this);
    }

    @Override
    protected void initView() {
        mCommonTitleBar = findViewById(R.id.titlebar);
        mLoadingLayout = findViewById(R.id.loading);
        smartRefreshLayout = findViewById(R.id.refreshLayout);
        smartRefreshLayout.setEnableLoadMore(false);
        mWebViewContainer = findViewById(R.id.webContainer);
        mCommonTitleBar.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON
                        || action == CommonTitleBar.ACTION_LEFT_TEXT) {
                    onBackPressed();
                }
            }
        });
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
    }

    protected void addWebView(WebView mWebView) {
        mWebViewContainer.removeAllViews();
        mWebViewContainer.addView(mWebView);
    }
}
