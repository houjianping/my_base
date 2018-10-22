package com.androidapp.filter.multiple;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.androidapp.base.R;
import com.androidapp.filter.FilterHeaderItem;
import com.androidapp.filter.FilterView;
import com.androidapp.filter.multiple.adapter.FlowPopListViewAdapter;
import com.androidapp.filter.multiple.bean.MultiBean;
import com.androidapp.filter.multiple.view.CustomHeightListView;

import java.util.ArrayList;
import java.util.List;

public class MultiPopupView extends LinearLayout {

    private Context mContext;
    private FilterHeaderItem filterHeaderItem;
    private FlowPopListViewAdapter mDataAdapter;
    private View mContainer;
    private FilterView.FilterAction mFilterAction;
    private FilterView.OnItemClick mOnItemClick;
    private List<MultiBean> mDictList = new ArrayList<>();

    public MultiPopupView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public void setFilterAction(FilterView.FilterAction action, FilterView.OnItemClick onItemClick) {
        mFilterAction = action;
        mOnItemClick = onItemClick;
    }

    private void initView() {
        mContainer = LayoutInflater.from(getContext()).inflate(R.layout.filter_multiple, this);
        CustomHeightListView mListView = (CustomHeightListView) mContainer.findViewById(R.id.listview);
        TextView tvReset = (TextView) mContainer.findViewById(R.id.tv_reset);
        TextView tvConfirm = (TextView) mContainer.findViewById(R.id.tv_confirm);
        View nullView = mContainer.findViewById(R.id.view_null);

        mDataAdapter = new FlowPopListViewAdapter(mContext, mDictList);
        mListView.setAdapter(mDataAdapter);
        tvReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int x = 0; x < mDictList.size(); x++) {
                    List<MultiBean.Children> childrenBeen = mDictList.get(x).getChildren();
                    for (int y = 0; y < childrenBeen.size(); y++) {
                        if (childrenBeen.get(y).isSelected())
                            childrenBeen.get(y).setSelected(false);
                    }
                }
                mDataAdapter.notifyDataSetChanged();
            }
        });
        tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContainer.setVisibility(View.GONE);
                mFilterAction.onHideFilter(filterHeaderItem);
                if (mOnItemClick != null) {
                    mOnItemClick.doFilter(filterHeaderItem);
                }
            }
        });
        nullView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mContainer.setVisibility(View.GONE);
                mFilterAction.onHideFilter(filterHeaderItem);
            }
        });
    }

    public void setViewData(FilterHeaderItem item) {
        mDictList.clear();
        mDictList.addAll(item.getmDictList());
        filterHeaderItem = item;
        if (mDataAdapter != null) {
            mDataAdapter.notifyDataSetChanged();
        }
    }
}
