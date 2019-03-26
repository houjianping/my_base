package com.siyuan.enjoyreading.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidapp.utils.ToastUtils;
import com.siyuan.enjoyreading.R;

public class FlexibleView extends RelativeLayout {

    private View mBaseView, flexible_action1, flexible_action2, flexible_action3, flexible_action4, flexible_action5;
    private TextView mFlexibleTitle1, mFlexibleTitle2, mFlexibleTitle3, mFlexibleTitle4, mFlexibleTitle5;
    private TextView mFlexibleSummary1, mFlexibleSummary2, mFlexibleSummary3;

    public FlexibleView(Context context) {
        super(context);
        initView();
        bindFlexibleInfo();
    }

    public FlexibleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
        bindFlexibleInfo();
    }

    private void initView() {
        mBaseView = LayoutInflater.from(getContext()).inflate(R.layout.view_flexible, this);
        flexible_action1 = mBaseView.findViewById(R.id.flexible_action1);
        flexible_action2 = mBaseView.findViewById(R.id.flexible_action2);
        flexible_action3 = mBaseView.findViewById(R.id.flexible_action3);
        flexible_action4 = mBaseView.findViewById(R.id.flexible_action4);
        flexible_action5 = mBaseView.findViewById(R.id.flexible_action5);
        mFlexibleTitle1 = mBaseView.findViewById(R.id.flexible_action1_title);
        mFlexibleTitle2 = mBaseView.findViewById(R.id.flexible_action2_title);
        mFlexibleTitle3 = mBaseView.findViewById(R.id.flexible_action3_title);
        mFlexibleTitle4 = mBaseView.findViewById(R.id.flexible_action4_title);
        mFlexibleTitle5 = mBaseView.findViewById(R.id.flexible_action5_title);
        mFlexibleSummary1 = mBaseView.findViewById(R.id.flexible_action1_summary);
        mFlexibleSummary2 = mBaseView.findViewById(R.id.flexible_action2_summary);
        mFlexibleSummary3 = mBaseView.findViewById(R.id.flexible_action3_summary);
        mBaseView.findViewById(R.id.flexible_action1).setOnClickListener(mOnClickListener);
        mBaseView.findViewById(R.id.flexible_action2).setOnClickListener(mOnClickListener);
        mBaseView.findViewById(R.id.flexible_action3).setOnClickListener(mOnClickListener);
        mBaseView.findViewById(R.id.flexible_action4).setOnClickListener(mOnClickListener);
        mBaseView.findViewById(R.id.flexible_action5).setOnClickListener(mOnClickListener);
    }

    public void bindFlexibleInfo() {
        mFlexibleTitle1.setText("潮爸辣妈");
        mFlexibleTitle2.setText("潮爸辣妈");
        mFlexibleTitle3.setText("潮爸辣妈");
        mFlexibleTitle4.setText("精致\n生活");
        mFlexibleTitle5.setText("家装\n雅居");
        mFlexibleSummary1.setText("爸妈帮/育儿经等等的");
        mFlexibleSummary2.setText("爸妈帮/育儿经等等的");
        mFlexibleSummary3.setText("爸妈帮/育儿经等等的");
    }

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.flexible_action1:
                    break;
                case R.id.flexible_action2:
                    break;
                case R.id.flexible_action3:
                    break;
                case R.id.flexible_action4:
                    break;
                case R.id.flexible_action5:
                    break;
            }
            ToastUtils.show("暂未设置跳转");
        }
    };
}
