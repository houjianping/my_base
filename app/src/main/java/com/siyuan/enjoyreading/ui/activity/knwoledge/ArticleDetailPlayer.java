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
import com.siyuan.enjoyreading.widget.AppWebView;

public class ArticleDetailPlayer extends BaseActivity {

    public static final String ARTICLE_URL = "article_url";
    private AppWebView mWebView;
    private LinearLayout ll_inputparent;

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
        articleUrl = "file:///android_asset/detail/article.html";
        init(articleUrl);
    }

    private void init(String url) {
        mWebView.loadUrl(url); //支持javascript
        mWebView.getSettings().setJavaScriptEnabled(true); // 设置可以支持缩放
        mWebView.getSettings().setSupportZoom(true); // 设置出现缩放工具
        mWebView.getSettings().setBuiltInZoomControls(true); //扩大比例的缩放
        mWebView.getSettings().setUseWideViewPort(true); //自适应屏幕
        mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWebView.getSettings().setLoadWithOverviewMode(true);
        mWebView.setWebviewCallback(new AppWebView.WebViewCallback() {
            @Override
            public void onPageFinished() {
                String content = "<div class=\"widt_ad\" style=\"padding-bottom: 0px; padding-top: 0px; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Microsoft YaHei&quot;, arial, sans-serif; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px;\"><img class=\"scrollLoading\" src=\"http://08.imgmini.eastday.com/mobile/20171213/20171213090938_5d204cbfe29043528eac687619b28f40_1.jpeg\" style=\"background-color:rgb(241, 240, 240); border:none; display:block; height:500.63888888889; margin-left:auto; margin-right:auto; max-width:100%; width:670px\" /></div>\n\n<p style=\"text-align:start\">&nbsp;</p>\n\n<p style=\"text-align:start\">据《每日邮报》12月12日报道，圣诞节快到了，美国一家小餐馆提前迎来了一位&ldquo;圣诞老人&rdquo;&mdash;&mdash;一位神秘的顾客吃了一顿17美元（约113元）的早餐后，给服务员留下了2000美元（约1.3万元）的小费。</p>\n\n<p style=\"text-align:start\">&nbsp;</p>\n\n<div class=\"widt_ad\" style=\"padding-bottom: 0px; padding-top: 0px; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Microsoft YaHei&quot;, arial, sans-serif; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px;\"><img class=\"scrollLoading\" src=\"http://08.imgmini.eastday.com/mobile/20171213/20171213090938_5d204cbfe29043528eac687619b28f40_2.jpeg\" style=\"background-color:rgb(241, 240, 240); border:none; display:block; height:819px; margin-left:auto; margin-right:auto; max-width:100%; width:634px\" /></div>\n\n<p style=\"text-align:start\">&nbsp;</p>\n\n<p style=\"text-align:start\">在美国亚利桑那州斯科茨代尔市一家名为&ldquo;5 &amp; Diner&rdquo;的餐厅里，一位匿名人士在吃过一顿17.23美元的早餐后，给服务员们留下了2000美元的小费，并在账单上留下了一句话：请把小费分给所有餐馆工作人员，圣诞快乐。</p>\n\n<p style=\"text-align:start\">&nbsp;</p>\n\n<div class=\"widt_ad\" style=\"padding-bottom: 0px; padding-top: 0px; color: rgb(51, 51, 51); font-family: 微软雅黑, &quot;Microsoft YaHei&quot;, arial, sans-serif; font-size: 14px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: normal; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px;\"><img class=\"scrollLoading\" src=\"http://08.imgmini.eastday.com/mobile/20171213/20171213090938_5d204cbfe29043528eac687619b28f40_3.jpeg\" style=\"background-color:rgb(241, 240, 240); border:none; display:block; height:436px; margin-left:auto; margin-right:auto; max-width:100%; width:634px\" /></div>\n\n<p style=\"text-align:start\">&nbsp;</p>\n\n<p style=\"text-align:start\">餐馆服务员Delia Meeks说：&ldquo;我们立即把它交给了值班经理，她简直不敢相信，我们都很兴奋。这让餐馆每个人都有了很棒的一天。&rdquo;这顿饭的价格为17.23美元，顾客点了培根和鸡蛋作为早餐，小费是餐费的一百多倍。这家餐厅的9名员工每人分到了200多美元的小费。Meeks说，工作人员希望这位仁慈的陌生人能回来，这样他们就可以感谢他了。</p>\n";

                Info info = new Info();
                info.setTitle("神秘顾客花一百块吃早餐，扔给服务员一万三小费");
                info.setSource("东方头条");
                info.setDate(1541660938);
                info.setBody(content);
                mWebView.loadUrl("javascript:initialArticle(" +
                        JsonUtils.toJson(info)
                        + ")");
            }

            @Override
            public void onPageLoadError() {
            }
        });
        ll_inputparent = (LinearLayout) findViewById(R.id.ll_inputparent);

        findViewById(R.id.add_comments).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_inputparent.setVisibility(View.VISIBLE);
                ll_inputparent.requestFocus(); // 获取焦点
                showKeyboard();
            }
        });

        // 软键盘监听
        KeyboardUtil.addKeyboardListener(this, new KeyboardUtil.OnSoftKeyBoardChangeListener() {
            @Override
            public void keyBoardShow(int height) {/*软键盘显示：执行隐藏title动画，并修改listview高度和装载礼物容器的高度*/
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) ll_inputparent.getLayoutParams();
                layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
                layoutParams.setMargins(layoutParams.leftMargin, layoutParams.rightMargin, layoutParams.topMargin, height);
                ll_inputparent.setLayoutParams(layoutParams);
            }

            @Override
            public void keyBoardHide(int height) {/*软键盘隐藏：隐藏聊天输入框并显示聊天按钮，执行显示title动画，并修改listview高度和装载礼物容器的高度*/
                ll_inputparent.setVisibility(View.GONE);
            }
        });
    }

    /**
     * 隐藏软键盘
     */
    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(findViewById(R.id.et_chat).getWindowToken(), 0);
    }

    /**
     * 显示软键盘
     */
    private void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(findViewById(R.id.et_chat), InputMethodManager.SHOW_FORCED);
    }

    public static Intent getIntent(Context context, String url) {
        Intent intent = new Intent(context, ArticleDetailPlayer.class);
        intent.putExtra(ARTICLE_URL, url);
        return intent;
    }

    private class Info {
        private String title;
        private String source;
        private long date;
        private String body;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public long getDate() {
            return date;
        }

        public void setDate(long date) {
            this.date = date;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }
    }

}
