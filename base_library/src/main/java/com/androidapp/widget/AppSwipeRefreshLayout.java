package com.androidapp.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class AppSwipeRefreshLayout extends SwipeRefreshLayout {
    private float startY;
    private float startX;
    private boolean mIsVpDragger;

    public AppSwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        boolean moveStateX = false;
        boolean moveStateY = false;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                startY = ev.getY();
                startX = ev.getX();
                // 初始化标记
                mIsVpDragger = false;
                break;
            case MotionEvent.ACTION_MOVE:
                // 如果viewpager正在拖拽中，那么不拦截它的事件，直接return false；
                if (mIsVpDragger) {
                    return false;
                }
                float endY = ev.getY();
                float endX = ev.getX();
                float distanceX = Math.abs(endX - startX);
                float distanceY = Math.abs(endY - startY);

                if (!moveStateY && distanceX > 20) {
                    moveStateX = true;
                }
                if (moveStateX && distanceY > 20) {
                    return false;   // 左右滑动时，禁止上下
                }

                if (!moveStateX && distanceY > 20) {
                    moveStateY = true;
                }
                if (mIsVpDragger && distanceX > 20) {
                    return false;   //上下滑动时，禁止左右
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                moveStateX = false;
                moveStateY = false;
                break;
        }
        // 如果是Y轴位移大于X轴，事件交给swipeRefreshLayout处理。
        return super.onInterceptTouchEvent(ev);
    }
}
