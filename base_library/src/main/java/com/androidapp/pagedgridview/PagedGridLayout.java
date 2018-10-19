package com.androidapp.pagedgridview;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import com.androidapp.base.R;
import com.androidapp.utils.CollectionUtils;
import com.androidapp.utils.ScreenUtil;
import com.androidapp.indicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class PagedGridLayout<T extends PagedGridItem> extends LinearLayout {

    private static final int NUM_COLUMNS = 4;
    private CirclePageIndicator mCirclePageIndicator;
    private PagedViewPager mViewPager;
    private LinearLayout mContainer;
    private OnGridItemClick onGridItemClick;
    private Activity mContext;
    private boolean mShowBigItems;

    public PagedGridLayout(Activity context, OnGridItemClick listener) {
        super(context);
        this.mContext = context;
        initView();
        onGridItemClick = listener;
    }

    private void initView() {
        View.inflate(mContext, R.layout.paged_gridview, this);
        mCirclePageIndicator = findViewById(R.id.indicator);
        mContainer = findViewById(R.id.ll_paged_grid);
        mViewPager = findViewById(R.id.view_pager);
    }

    public void setEnableBigItem(boolean showBigItem) {
        mShowBigItems = showBigItem;
    }

    public void setData(List<T> industryList, int row, int colum) {
        if (industryList.size() > 0) {
            mContainer.setVisibility(View.VISIBLE);
        }
        List<List<T>> subList = CollectionUtils.splitList(industryList, row * colum);
        List<PagedGridView> gridList = new ArrayList<>();
        for (int i = 0; i < subList.size(); i++) {
            PagedGridView gridView = new PagedGridView(mContext);
            gridView.setNumColumns(colum);
            gridView.setHorizontalSpacing(15);
            gridView.setVerticalSpacing(15);
            PagedGridViewAdapter adapter = new PagedGridViewAdapter(mContext, mShowBigItems, subList.get(i));
            if (onGridItemClick != null) {
                adapter.setOnItemClick(onGridItemClick);
            }
            gridView.setAdapter(adapter);
            gridList.add(gridView);
            setListViewHeightBasedOnChildren(gridView);
        }
        PagedGridPageAdapter mAdapter = new PagedGridPageAdapter();
        mAdapter.add(gridList);
        mViewPager.setAdapter(mAdapter);
        mCirclePageIndicator.setViewPager(mViewPager);
        if (gridList.size() > 1) {
            mCirclePageIndicator.setVisibility(View.VISIBLE);
        }
    }

    private void setListViewHeightBasedOnChildren(GridView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i += NUM_COLUMNS) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight() + ScreenUtil.dip2px(getContext(), 5);
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        if (params != null) {
            params.height = totalHeight;
            listView.setLayoutParams(params);
        }
    }

    public interface OnGridItemClick<T> {
        void onGridItemClick(T item);
    }
}
