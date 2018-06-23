package com.scwang.refreshlayout.activity.fragment.common;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.androidapp.base.activity.BaseWebActivity;
import com.androidapp.base.fragment.BaseWebFragment;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;

public class YuedanWebFragment extends BaseWebFragment {

    public static final String KEY_URL = "h5url";

    private WebView mWebView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mWebView == null) {
            mWebView = new WebView(getContext());
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
            tryToLoadData();
            mLoadingLayout.setRetryListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mWebView.reload();
                }
            });
            smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
                @Override
                public void onRefresh(RefreshLayout refreshlayout) {
                    refreshlayout.finishRefresh(2000, false);//传入false表示刷新失败
                    mWebView.reload();
                }
            });
        }
    }

    private void tryToLoadData() {
         Bundle bundle = getArguments();
         if (bundle != null && !TextUtils.isEmpty(bundle.getString(KEY_URL))) {
             if (mWebView != null) {
                mWebView.loadUrl(bundle.getString(KEY_URL));
             }
         }
    }
}
