package com.siyuan.enjoyreading.ui.fragment.common;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.androidapp.fragment.BaseWebFragment;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;
import com.siyuan.enjoyreading.widget.AppWebView;

public class AppWebFragment extends BaseWebFragment {

    public static final String KEY_URL = "h5url";

    private AppWebView mWebView;

    @Override
    public void onResume() {
        super.onResume();
        getFocus();
    }

    //主界面获取焦点
    private void getFocus() {
        if (getView() == null)
            return;
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    return mWebView.onBackPress();
                }
                return false;
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mWebView == null) {
            mWebView = new AppWebView(getActivity());
            mWebView.setWebviewCallback(new AppWebView.WebViewCallback() {
                @Override
                public void onPageFinished(String url, String title) {
                    mLoadingLayout.showContent();
                    getFocus();
                }

                @Override
                public void onPageLoadError() {
                    mLoadingLayout.showError();
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
                    Log.e("", "----->" + mWebView.getUrl());
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
