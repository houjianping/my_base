package com.siyuan.enjoyreading.ui.activity.knwoledge;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.androidapp.activity.BaseActivity;
import com.androidapp.utils.ToastUtils;
import com.androidapp.widget.CommonTitleBar;
import com.siyuan.enjoyreading.R;

public class SearchKeyWordActivity extends BaseActivity {

    private CommonTitleBar mTitleBar;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.act_search_keyword);
    }

    @Override
    protected void initView() {
        mTitleBar = findViewById(R.id.titlebar);
        mTitleBar.getRightTextView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyWord = mTitleBar.getSearchKey();
                if (!TextUtils.isEmpty(keyWord)) {
                    ToastUtils.show(keyWord);
                }
            }
        });
        mTitleBar.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_SEARCH_SUBMIT) {
                    if (!TextUtils.isEmpty(extra)) {
                        ToastUtils.show(extra);
                    }
                }
            }
        });
    }

    @Override
    protected void initData() {
    }
}
