package com.androidapp.pagedgridview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

public class PagedGridView extends GridView {
    public PagedGridView(Context context) {
        super(context);
    }

    public PagedGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
