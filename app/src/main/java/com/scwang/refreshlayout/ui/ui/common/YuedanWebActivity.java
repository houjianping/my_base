package com.scwang.refreshlayout.ui.ui.common;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.androidapp.base.activity.BaseWebActivity;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;

public class YuedanWebActivity extends BaseWebActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final WebView mWebView = new WebView(this);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mLoadingLayout.showContent();
            }
        });
        addWebView(mWebView);
        mWebView.loadUrl("https://www.baidu.com/");
        mCommonTitleBar.setTitle("浏览器标题");
        mLoadingLayout.setRetryListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWebView.reload();
            }
        });
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                mWebView.reload();
            }
        });
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        super.getBundleExtras(extras);

    }
}