package com.scwang.refreshlayout.activity.fragment.common;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;

import com.androidapp.base.fragment.BaseWebFragment;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.refreshlayout.widget.AppWebView;

public class YuedanWebFragment extends BaseWebFragment {

    public static final String KEY_URL = "h5url";

    private AppWebView mWebView;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mWebView == null) {
            mWebView = new AppWebView(getContext());
            mWebView.setWebviewCallback(new AppWebView.WebViewCallback() {
                @Override
                public void onPageFinished() {
                    mLoadingLayout.showContent();
                }

                @Override
                public void onPageLoadError() {
//                    mLoadingLayout.showError();
                }
            });
            addWebView(mWebView);
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
            tryToLoadData();
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
