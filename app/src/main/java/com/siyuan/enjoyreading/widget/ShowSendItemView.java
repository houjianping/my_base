package com.siyuan.enjoyreading.widget;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidapp.utils.ImageLoaderUtils;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.entity.ShowSendItemInfo;
import com.siyuan.enjoyreading.util.IntentUtil;

public class ShowSendItemView extends LinearLayout {
    private Context mContext;
    private ImageView ivIcon;
    private ImageView ivGuideIcon;
    private TextView tvTitle;
    private TextView tvSubTitle;
    private TextView tvDes;
    private View llGuideDes;
    private ShowSendItemInfo showSendItemInfo;

    private AnimationActionView anmationShowSendButtonView;

    public ShowSendItemView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public ShowSendItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.show_send_item_view, this);
        ivIcon = (ImageView) findViewById(R.id.ivIcon);
        ivGuideIcon = (ImageView) findViewById(R.id.ivGuideIcon);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvSubTitle = (TextView) findViewById(R.id.tvSubTitle);
        tvDes = (TextView) findViewById(R.id.tvDes);
        llGuideDes = findViewById(R.id.llGuideDes);
        setOnClickListener(mOnClickListener);
    }

    public void setData(AnimationActionView anmationShowSendButtonView, ShowSendItemInfo showSendItemInfo) {
        try {
            if (showSendItemInfo == null) {
                this.setVisibility(INVISIBLE);
                return;
            }
            this.setVisibility(VISIBLE);
            this.anmationShowSendButtonView = anmationShowSendButtonView;
            this.showSendItemInfo = showSendItemInfo;
            llGuideDes.setVisibility(INVISIBLE);
            ivGuideIcon.setVisibility(INVISIBLE);

            ImageLoaderUtils.display(mContext, ivIcon, showSendItemInfo.getIcon());
            if (showSendItemInfo.getShow_type() == 1) {   //文案气泡
                llGuideDes.setVisibility(VISIBLE);
                tvDes.setText(showSendItemInfo.getDes());
            } else if (showSendItemInfo.getShow_type() == 2) {   //角标显示
                ivGuideIcon.setVisibility(VISIBLE);
                if (!TextUtils.isEmpty(showSendItemInfo.getGuide_icon())) {
                    ImageLoaderUtils.display(mContext, ivGuideIcon, showSendItemInfo.getGuide_icon());
                }
            }
            tvTitle.setText(showSendItemInfo.getTitle());
            tvSubTitle.setText(showSendItemInfo.getSub_title());
            int color = Color.parseColor(showSendItemInfo.getTitle_color());
            tvTitle.setTextColor(color);
            tvSubTitle.setTextColor(color);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (showSendItemInfo != null && !TextUtils.isEmpty(showSendItemInfo.getApp_jump())) {
                anmationShowSendButtonView.hide();
                IntentUtil.startActivity(mContext, showSendItemInfo.getApp_jump());
            }
        }
    };
}
