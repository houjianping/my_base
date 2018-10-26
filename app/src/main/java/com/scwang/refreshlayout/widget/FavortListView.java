package com.scwang.refreshlayout.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * des:点赞列表
 * Created by xsf
 * on 2016.07.11:11
 */
public class FavortListView extends android.support.v7.widget.AppCompatTextView {
    private ISpanClick mSpanClickListener;

    public void setSpanClickListener(ISpanClick listener){
        mSpanClickListener = listener;
    }
    public ISpanClick getSpanClickListener(){
        return  mSpanClickListener;
    }

    public FavortListView(Context context) {
        super(context);
    }

    public FavortListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FavortListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

   /* public void setAdapter(FavortListAdapter adapter){
        adapter.bindListView(this);
    }*/

    public interface ISpanClick {
        void onClick(int position);
    }
}
