package com.scwang.refreshlayout.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.scwang.refreshlayout.R;

public class HeaderView extends RelativeLayout {

    private View mBaseView;
    private TextView mTitleView;
    private View mRightPartView;

    public HeaderView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        mBaseView = LayoutInflater.from(getContext()).inflate(R.layout.view_header, this);
        mTitleView = mBaseView.findViewById(R.id.tv_header_title);
        mRightPartView = mBaseView.findViewById(R.id.ll_header_total);
    }

    public void setTitle(String title) {
        mTitleView.setText(title);
    }

    public void setRightViewVisible(boolean show) {
        mRightPartView.setVisibility(show ? View.VISIBLE : View.GONE);
    }
}
