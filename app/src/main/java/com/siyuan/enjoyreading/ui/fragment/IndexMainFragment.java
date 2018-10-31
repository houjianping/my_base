package com.siyuan.enjoyreading.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.androidapp.fragment.BaseTabFragment;
import com.androidapp.smarttablayout.SmartTabLayout;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.ui.activity.index.ChannelActivity;
import com.siyuan.enjoyreading.ui.fragment.index.IndexFragmentAdapter;

public class IndexMainFragment extends BaseTabFragment {

    private ViewPager mViewPager;
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

        view.findViewById(R.id.chat_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), ChannelActivity.class));
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.act_tab_one;
    }

}
