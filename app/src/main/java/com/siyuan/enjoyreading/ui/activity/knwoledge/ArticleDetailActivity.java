package com.siyuan.enjoyreading.ui.activity.knwoledge;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebSettings;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.androidapp.activity.BaseActivity;
import com.androidapp.utils.JsonUtils;
import com.androidapp.utils.KeyboardUtil;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.api.ApiConfig;
import com.siyuan.enjoyreading.entity.WebInfo;
import com.siyuan.enjoyreading.util.ShareUtil;
import com.siyuan.enjoyreading.widget.AppWebView;

public class ArticleDetailActivity extends BaseActivity {

    public static final String ARTICLE_URL = "article_url";

    private LinearLayout mInputAreaView;
    private AppWebView mWebView;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.act_knowledge);
    }

    @Override
    protected void initView() {
        mWebView = (AppWebView) findViewById(R.id.article_webview);
        mInputAreaView = (LinearLayout) findViewById(R.id.ll_inputparent);
        mWebView.getSettings().setJavaScriptEnabled(true); // 设置可以支持缩放
        mWebView.getSettings().setSupportZoom(true); // 设置出现缩放工具
        mWebView.getSettings().setBuiltInZoomControls(true); //扩大比例的缩放
        mWebView.getSettings().setUseWideViewPort(true); //自适应屏幕
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mTitleBar.getRightImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtil.share(ArticleDetailActivity.this);
            }
        });
        KeyboardUtil.addKeyboardListener(this, new KeyboardUtil.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {/*软键盘显示：执行隐藏title动画，并修改listview高度和装载礼物容器的高度*/
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mInputAreaView.getLayoutParams();
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                layoutParams.setMargins(layoutParams.leftMargin, layoutParams.rightMargin, layoutParams.topMargin, height);
                mInputAreaView.setLayoutParams(layoutParams);
            }
            @Override
            public void keyBoardHide(int height) {/*软键盘隐藏：隐藏聊天输入框并显示聊天按钮，执行显示title动画，并修改listview高度和装载礼物容器的高度*/
                mInputAreaView.setVisibility(View.GONE);
            }
        });
        findViewById(R.id.add_comments).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputAreaView.setVisibility(View.VISIBLE);
                mInputAreaView.requestFocus(); // 获取焦点
                showKeyboard();
            }
        });
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        init(ApiConfig.getWebInfo());
    }

    private void init(final WebInfo webInfo) {
        if (webInfo == null ) {
            return;
        }
        webInfo.setUseWebUrl(false);
        if (webInfo.isUseWebUrl()) {
            mWebView.loadUrl(webInfo.getUrl());
        } else {
            String articleUrl = "file:///android_asset/detail/article.html";
            mWebView.loadUrl(articleUrl);
            mWebView.setWebviewCallback(new AppWebView.WebViewCallback() {
                @Override
                public void onPageFinished() {
                    mWebView.loadUrl("javascript:initialArticle(" + JsonUtils.toJson(webInfo) + ")");
                }
                @Override
                public void onPageLoadError() {
                }
            });
        }
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(findViewById(R.id.et_chat).getWindowToken(), 0);
    }

    private void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(findViewById(R.id.et_chat), InputMethodManager.SHOW_FORCED);
    }

    public static Intent getIntent(Context context, String url) {
        Intent intent = new Intent(context, ArticleDetailActivity.class);
        intent.putExtra(ARTICLE_URL, url);
        return intent;
    }
}
