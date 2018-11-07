package com.siyuan.enjoyreading.ui.activity.knwoledge;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.androidapp.activity.BaseActivity;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.widget.AppWebView;

public class ArticleDetailPlayer extends BaseActivity {

    public static final String ARTICLE_URL = "article_url";
    private AppWebView mWebView;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.act_knowledge);
    }

    @Override
    protected void initView() {
        mWebView = (AppWebView) findViewById(R.id.article_webview);
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        String articleUrl = bundle.getString(ARTICLE_URL);
        init(articleUrl);
    }

    private void init(String url) {
        mWebView.loadUrl(url);
    }

    public static Intent getIntent(Context context, String url) {
        Intent intent = new Intent(context, ArticleDetailPlayer.class);
        intent.putExtra(ARTICLE_URL, url);
        return intent;
    }
}
