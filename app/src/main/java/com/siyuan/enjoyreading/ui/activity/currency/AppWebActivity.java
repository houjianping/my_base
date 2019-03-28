package com.siyuan.enjoyreading.ui.activity.currency;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.androidapp.activity.BaseWebActivity;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;
import com.siyuan.enjoyreading.widget.AppWebView;

public class AppWebActivity extends BaseWebActivity {

    private static final String KEY_URL = "web_url";
    private static final String KEY_TITLE = "web_title";

    private String mUrl;
    private String mTitle;
    private AppWebView mAppWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAppWebView = new AppWebView(this);
        mAppWebView.setWebviewCallback(new AppWebView.WebViewCallback() {
            @Override
            public void onPageFinished(String url, String title) {
                mLoadingLayout.showContent();
            }
            @Override
            public void onPageLoadError() {
                mLoadingLayout.showEmpty();
            }
        });
        mCommonTitleBar.setTitle(mTitle);
        mLoadingLayout.setRetryListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAppWebView.reload();
            }
        });
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                mAppWebView.reload();
            }
        });
        addWebView(mAppWebView);
        mAppWebView.loadUrl(mUrl);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        super.getBundleExtras(extras);
        if (extras != null) {
            mUrl = extras.getString(KEY_URL, "");
            mTitle = extras.getString(KEY_TITLE, "");
        }
        mUrl = TextUtils.isEmpty(mUrl) ? "" : mUrl;
        mTitle = TextUtils.isEmpty(mTitle) ? "" : mTitle;
        if (TextUtils.isEmpty(mUrl)) {
            finish();
        }
    }

    public static Bundle getBundle(String url, String title) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_URL, url);
        bundle.putString(KEY_TITLE, title);
        return bundle;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mAppWebView.onActivityResult(requestCode,resultCode, data);
    }
}
