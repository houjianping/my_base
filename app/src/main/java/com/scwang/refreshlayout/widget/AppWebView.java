package com.scwang.refreshlayout.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.androidapp.base.utils.JsonUtils;
import com.androidapp.base.utils.ToastUtils;
import com.androidapp.cachewebviewlib.CacheInterceptor;
import com.androidapp.cachewebviewlib.CacheWebView;
import com.androidapp.cachewebviewlib.WebViewCache;

public class AppWebView extends CacheWebView {

    public static final String TAG = "AppWebView";
    int lastX = 0;
    int lastY = 0;
    private WebViewCallback mWebViewCallback;
    private OnTouchListener mOnTouchListener = new OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            getParent().requestDisallowInterceptTouchEvent(true);
            int x = (int) event.getRawX();
            int y = (int) event.getRawY();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    lastX = x;
                    lastY = y;
                    break;
                case MotionEvent.ACTION_MOVE:
                    int deltaY = y - lastY;
                    int deltaX = x - lastX;
                    if (Math.abs(deltaX) < Math.abs(deltaY)) {
                        getParent().requestDisallowInterceptTouchEvent(false);
                    } else {
                        getParent().requestDisallowInterceptTouchEvent(true);
                        return true;
                    }
                default:
                    break;
            }
            return false;
        }
    };

    public AppWebView(Context context) {
        super(context);
        initWebView();
        setCacheStrategy(WebViewCache.CacheStrategy.FORCE);
        setOnTouchListener(mOnTouchListener);
        Log.e("", "###########setOnTouchListener##########mOnTouchListener#####");
    }

    public static void preLoadUrl(final Context context, final String url) {
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    CacheWebView.cacheWebView(context).loadUrl(url);//要放在UI线程
                }
            });
        } else {
            ToastUtils.showShortToast(context, "URL类型加载出错");
        }
    }

    private void initWebView() {
        addJavascriptInterface(this, "myjs");              // 执行JavaScript脚本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //网页兼容https与非http混合使用
            getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setWebContentsDebuggingEnabled(true);
        }
        setCacheInterceptor(new CacheInterceptor() {
            public boolean canCache(String url) {
                return true;
            }
        });
        initWebViewClient();
        initWebChromeClient();
    }

    private void initWebViewClient() {
        setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                loadUrl("javascript:window.myjs.doAction(\"size\", '{width='+document.body.scrollWidth+',height='+document.documentElement.scrollHeight+'}');");
                String title = view.getTitle();
                Log.e(TAG, "========onPageFinished WebView title=" + title);
                if (mWebViewCallback != null) {
                    mWebViewCallback.onPageFinished();
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.e("", "shouldOverrideUrlLoading" + url);
                if (!url.startsWith("http")) {
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, url);
            }

            //处理网页加载失败时
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                if (mWebViewCallback != null) {
                    mWebViewCallback.onPageLoadError();
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                view.stopLoading();
                view.clearView();
                if (errorCode >= 400) {
                    if (mWebViewCallback != null) {
                        mWebViewCallback.onPageLoadError();
                    }
                } else {
                    super.onReceivedError(view, errorCode, description, failingUrl);
                }
                if (mWebViewCallback != null) {
                    mWebViewCallback.onPageLoadError();
                }
            }
        });
    }

    private void initWebChromeClient() {
        setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
                Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
                result.confirm();
                return true;
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if (!TextUtils.isEmpty(title)
                        && (title.toLowerCase().contains("403") || title.toLowerCase().contains("502") || title
                        .toLowerCase().contains("500"))) {
                    AppWebView.this.setVisibility(View.GONE);
                }
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
            }
        });
    }

    public void setWebviewCallback(WebViewCallback webviewCallback) {
        mWebViewCallback = webviewCallback;
    }

    @JavascriptInterface
    public void doAction(String action, String json) {
        if ("size".equals(action)) {
            final int height = JsonUtils.getIntValue(json, "height");
            if (height < 1) {
                return;
            }
            if (getContext() instanceof Activity) {
                ((Activity) getContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setLayoutParams(new LinearLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels, (int) (height * getResources().getDisplayMetrics().density)));
                    }
                });
            } else if (getContext() instanceof FragmentActivity) {
                ((FragmentActivity) getContext()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setLayoutParams(new LinearLayout.LayoutParams(getResources().getDisplayMetrics().widthPixels, (int) (height * getResources().getDisplayMetrics().density)));
                    }
                });
            } else {
                ToastUtils.showShortToast(getContext(), "页面出错了");
            }
        }
    }

    public boolean onBackPress() {
        if (canGoBack()) {
            goBack();
            return true;
        }
        return false;
    }

    public interface WebViewCallback {
        void onPageFinished();

        void onPageLoadError();
    }
}
