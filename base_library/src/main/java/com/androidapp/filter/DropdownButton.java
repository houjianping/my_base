package com.androidapp.filter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.androidapp.base.R;
import com.androidapp.base.utils.ToastUtils;

public class DropdownButton extends RelativeLayout {
    private TextView textView;
    private FilterView.FilterAction mFilterAction;
    private FilterHeaderItem filterHeaderItem;
    private boolean mChecked;

    public DropdownButton(Context context) {
        super(context);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dropdown_tab_button, this, true);
        textView = view.findViewById(R.id.textView);
        view.setOnClickListener(mOnClickListener);
    }

    public void setText(CharSequence text) {
        textView.setText(text);
    }

    public void setChecked(boolean hightLight, boolean up) {
        Drawable icon;
        if (hightLight) {
            textView.setTextColor(getResources().getColor(R.color.color_primary));
        } else {
            textView.setTextColor(getResources().getColor(R.color.black_main));
        }
        if (filterHeaderItem != null && !filterHeaderItem.isSelectable()) {
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        } else {
            if (up) {
                if (hightLight) {
                    icon = getResources().getDrawable(R.drawable.ic_dropdown_up_actived);
                } else {
                    icon = getResources().getDrawable(R.drawable.ic_dropdown_up_normal);
                }
            } else {
                if (hightLight) {
                    icon = getResources().getDrawable(R.drawable.ic_dropdown_down_actived);
                } else {
                    icon = getResources().getDrawable(R.drawable.ic_dropdown_down_normal);
                }
            }
            textView.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);
        }
    }

    public void setFilterAction(FilterView.FilterAction action, FilterHeaderItem item) {
        mFilterAction = action;
        filterHeaderItem = item;
    }

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (mFilterAction != null) {
                if (mChecked) {
                    mFilterAction.onHideFilter(filterHeaderItem);
                } else {
                    mFilterAction.onHideFilter(filterHeaderItem);
                    mFilterAction.onShowFilter(filterHeaderItem);
                }
            }
            setChecked(!mChecked, !mChecked);
        }
    };
}
