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
    TextView textView;
    View bottomLine;
    FilterView.FilterAction mFilterAction;
    private int mCurrentIndex;

    public DropdownButton(Context context) {
        super(context);
        init();
    }

    private void init() {
        View view =  LayoutInflater.from(getContext()).inflate(R.layout.dropdown_tab_button,this, true);
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShortToast(getContext(), "############");
            }
        });
        textView = (TextView) view.findViewById(R.id.textView);
        bottomLine = view.findViewById(R.id.bottomLine);
        view.setOnClickListener(mOnClickListener);
    }


    public void setText(CharSequence text) {
        textView.setText(text);
    }

    public void setChecked(boolean checked) {
        Drawable icon;
        if (checked) {
            icon = getResources().getDrawable(R.drawable.ic_dropdown_actived);
            textView.setTextColor(Color.parseColor("#FF3BBD79"));
            bottomLine.setVisibility(VISIBLE);
        } else {
            icon = getResources().getDrawable(R.drawable.ic_dropdown_normal);
            textView.setTextColor(getResources().getColor(R.color.black_main));
            bottomLine.setVisibility(GONE);
        }
        textView.setCompoundDrawablesWithIntrinsicBounds(null, null, icon, null);
    }

    public void setFilterAction(FilterView.FilterAction action, int index) {
        mFilterAction = action;
        mCurrentIndex = index;
    }

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            boolean currentVisable = bottomLine.getVisibility() == View.VISIBLE;
            if (mFilterAction != null) {
                if (currentVisable) {
                    mFilterAction.onHideFilter(mCurrentIndex);
                } else {
                    mFilterAction.onHideFilter(mCurrentIndex);
                    mFilterAction.onShowFilter(mCurrentIndex);
                }
            }
            setChecked(!currentVisable);
        }
    };
}
