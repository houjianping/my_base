package com.siyuan.enjoyreading.ui.activity.currency;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.androidapp.activity.BaseWebActivity;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;

public class YuedanWebActivity extends BaseWebActivity {

    private static final String KEY_URL = "web_url";
    private static final String KEY_TITLE = "web_title";

    private String mUrl;
    private String mTitle;
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
        mWebView.loadUrl(mUrl);
        mCommonTitleBar.setTitle(mTitle);
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
        if (extras != null) {
            mUrl = extras.getString(KEY_URL, "");
            mTitle = extras.getString(KEY_TITLE, "");
        }
        mUrl = TextUtils.isEmpty(mUrl) ? "https://www.baidu.com/" : mUrl;
        mTitle = TextUtils.isEmpty(mTitle) ? "浏览器标题" : mTitle;
    }

    /**
     * 获取H5跳转参数
     * @param url
     * @param title
     * @return
     */
    public static Bundle getBundle(String url, String title) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_URL, url);
        bundle.putString(KEY_TITLE, title);
        return bundle;
    }
}
