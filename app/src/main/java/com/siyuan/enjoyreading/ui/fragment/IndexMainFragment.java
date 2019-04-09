package com.siyuan.enjoyreading.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.androidapp.fragment.BaseTabFragment;
import com.androidapp.smarttablayout.SmartTabLayout;
import com.androidapp.widget.AppViewPager;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.ui.activity.MessageActivity;
import com.siyuan.enjoyreading.ui.activity.SearchKeyWordActivity;
import com.siyuan.enjoyreading.ui.fragment.index.IndexFragmentAdapter;

public class IndexMainFragment extends BaseTabFragment {

    private AppViewPager mViewPager;
    private SmartTabLayout mSmartTabLayout;

    @Override
    protected void loadData(boolean force) {
        Log.e("", "--------loadData-----IndexMainFragment--------" + force);
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        mViewPager = view.findViewById(R.id.viewpager);
        mViewPager.setAdapter(new IndexFragmentAdapter(getFragmentManager()));
        mSmartTabLayout = view.findViewById(R.id.viewpagertab);
        mSmartTabLayout.setViewPager(mViewPager);

        view.findViewById(R.id.search_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doStartActivity(SearchKeyWordActivity.class, null);
            }
        });
        view.findViewById(R.id.chat_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), MessageActivity.class));
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_tab_one;
    }

}
