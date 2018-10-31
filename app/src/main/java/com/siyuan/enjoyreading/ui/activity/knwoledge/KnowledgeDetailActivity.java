package com.siyuan.enjoyreading.ui.activity.knwoledge;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.androidapp.activity.BaseActivity;
import com.androidapp.smarttablayout.SmartTabLayout;
import com.androidapp.widget.CommonTitleBar;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.ui.fragment.knowledge.KnowledgeAdapter;

public class KnowledgeDetailActivity extends BaseActivity {

    private CommonTitleBar mTitleBar;
    private ViewPager mViewPager;
    private SmartTabLayout mSmartTabLayout;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.act_knowledge_main);
    }

    @Override
    protected void initView() {
        mTitleBar = findViewById(R.id.titlebar);
        mViewPager = findViewById(R.id.viewpager);
        mSmartTabLayout = mTitleBar.getCenterCustomView().findViewById(R.id.tab_list);
    }

    @Override
    protected void initData() {
        mViewPager.setAdapter(new KnowledgeAdapter(getSupportFragmentManager()));
        mSmartTabLayout.setViewPager(mViewPager);
    }
}
