package com.androidapp.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidapp.base.R;

public class SettingItem extends RelativeLayout {

    private boolean mShowTopDiv;
    private boolean mShowBottomDiv;

    private Drawable mIcon;
    private String mTitle;
    private float mTitleSize;
    private int mTitleColor;

    private String mSummary;
    private float mSummaryeSize;
    private int mSummaryColor;
    private boolean mClickAble;

    private View mBaseView;
    private ImageView mIconImageView;
    private TextView mTitleTextView;
    private TextView mSummaryTextView;
    private View mTopDivView;
    private View mBottomDivView;

    public SettingItem(Context context) {
        super(context);
    }

    public SettingItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SettingsItem);
        mShowTopDiv = typedArray.getBoolean(R.styleable.SettingsItem_settingsShowTopDiv, false);
        mShowBottomDiv = typedArray.getBoolean(R.styleable.SettingsItem_settingsShowBottomDiv, false);
        mIcon = typedArray.getDrawable(R.styleable.SettingsItem_settingsIcon);

        mTitle = typedArray.getString(R.styleable.SettingsItem_settingsTitle);
        mTitleSize = typedArray.getDimension(R.styleable.SettingsItem_settingsTitleSize, 15);
        mTitleColor = typedArray.getColor(R.styleable.SettingsItem_settingsTitleColor, 0XFF333333);

        mSummary = typedArray.getString(R.styleable.SettingsItem_settingsSummary);
        mSummaryeSize = typedArray.getDimension(R.styleable.SettingsItem_settingsSummarySize, 13);
        mSummaryColor = typedArray.getColor(R.styleable.SettingsItem_settingsSummaryColor, 0XFF999999);

        mClickAble = typedArray.getBoolean(R.styleable.SettingsItem_settingsClickable, true);
        initView();
        updateUI();
        setClickAble(mClickAble);
    }

    private void initView() {
        mBaseView = LayoutInflater.from(getContext()).inflate(R.layout.settings_row_item, this);
        mIconImageView = (ImageView) mBaseView.findViewById(R.id.iv_setting_icon);
        mTitleTextView = (TextView) mBaseView.findViewById(R.id.tv_setting_title);
        mSummaryTextView = (TextView) mBaseView.findViewById(R.id.tv_setting_summary);
        mTopDivView = mBaseView.findViewById(R.id.setting_top_div);
        mBottomDivView = mBaseView.findViewById(R.id.setting_bottom_div);
    }

    private void updateUI() {
        if (mIcon != null) {
            mIconImageView.setBackground(mIcon);
        } else {
            mIconImageView.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(mTitle)) {
            mTitleTextView.setText(mTitle);
        }
        mTitleTextView.setTextColor(mTitleColor);
        mTitleTextView.setTextSize(mTitleSize);

        if (!TextUtils.isEmpty(mSummary)) {
            mSummaryTextView.setText(mSummary);
        }
        mSummaryTextView.setTextColor(mSummaryColor);
        mSummaryTextView.setTextSize(mSummaryeSize);
        mTopDivView.setVisibility(mShowTopDiv ? View.VISIBLE : View.GONE);
        if (mShowBottomDiv) {
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(mBottomDivView.getLayoutParams());
            layoutParams.leftMargin = 0;
            layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
            mBottomDivView.setLayoutParams(layoutParams);
        }
    }

    public void setClickAble(boolean clickable) {
        mClickAble = clickable;
        mBaseView.setClickable(mClickAble);
    }
}
