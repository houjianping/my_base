package com.androidapp.filter.single;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.androidapp.base.R;
import com.androidapp.filter.DropdownButton;
import com.androidapp.filter.FilterView;
import com.androidapp.filter.single.bean.SignalBean;
import com.androidapp.filter.single.view.DropdownListItemView;

import java.util.LinkedList;
import java.util.List;

public class SignalPopupView extends ScrollView {

    public LinearLayout linearLayout;
    public SignalBean current;
    private DropdownButton mDropdownButton;
    private String selectedKeyWord = "";

    List<? extends SignalBean> list;

    public SignalPopupView(Context context) {
        super(context);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.filter_signal_tab_list, this, true);
        linearLayout = (LinearLayout) view.findViewById(R.id.linearLayout);
    }

    public void flush() {
        for (int i = 0, n = linearLayout.getChildCount(); i < n; i++) {
            View view = linearLayout.getChildAt(i);
            if (view instanceof DropdownListItemView) {
                DropdownListItemView itemView = (DropdownListItemView) view;
                SignalBean data = (SignalBean) itemView.getTag();
                if (data == null) {
                    return;
                }
                boolean checked = data == current;
                itemView.bind(data.getText(), checked);
                if (checked) mDropdownButton.setText(data.getText());
            }
        }
    }

    public void bind(final List<? extends SignalBean> list, DropdownButton dropdownButton, final FilterView.FilterAction container, final FilterView.OnItemClick onItemClickm) {
        mDropdownButton = dropdownButton;
        for (SignalBean item : list) {
            if (item.isSelected()) {
                current = item;
                break;
            }
        }
        this.list = list;
        LinkedList<View> cachedDividers = new LinkedList<>();
        LinkedList<DropdownListItemView> cachedViews = new LinkedList<>();
        for (int i = 0, n = linearLayout.getChildCount(); i < n; i++) {
            View view = linearLayout.getChildAt(i);
            if (view instanceof DropdownListItemView) {
                cachedViews.add((DropdownListItemView) view);
            } else {
                cachedDividers.add(view);
            }
        }

        linearLayout.removeAllViews();
        LayoutInflater inflater = LayoutInflater.from(getContext());
        boolean isFirst = true;
        for (SignalBean item : list) {
            if (isFirst) {
                isFirst = false;
            } else {
                View divider = cachedDividers.poll();
                if (divider == null) {
                    divider = inflater.inflate(R.layout.filter_list_divider, linearLayout, false);
                }
                linearLayout.addView(divider);
            }
            DropdownListItemView view = cachedViews.poll();
            if (view == null) {
                view = (DropdownListItemView) inflater.inflate(R.layout.filter_signal_list_item, linearLayout, false);
            }
            view.setTag(item);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    SignalBean data = (SignalBean) v.getTag();
                    if (data == null) return;
                    SignalBean oldOne = current;
                    current = data;
                    flush();
                    if (oldOne != current) {
                        if (onItemClickm != null) {
                            onItemClickm.onSignalItemClick(data);
                        }
                        selectedKeyWord = data.getKeyword();
                    }
                    container.onHideFilter(-1);
                    for (SignalBean item : list) {
                        if (item.getKeyword().equals(data.getKeyword())) {
                            item.setSelected(true);
                        } else {
                            item.setSelected(false);
                        }
                    }
                }
            });
            linearLayout.addView(view);
            if (item.getKeyword().equalsIgnoreCase(selectedKeyWord) && current == null) {
                current = item;
            }
        }

        if (current == null && list.size() > 0) {
            current = list.get(0);
        }
        flush();
    }
}