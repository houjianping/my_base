package com.siyuan.enjoyreading.event;

import android.view.View;

/**
 * 防止view多次被点击，现增加多次点击时长限制
 */
public abstract class OnClickEvent implements View.OnClickListener {

    private static long sLastPressTime;

    @Override
    public void onClick(View v) {
        if (onDoubClick()) {
            return;
        }
        singleClick(v);
    }

    private boolean onDoubClick() {
        boolean flag = false;
        long time = System.currentTimeMillis() - sLastPressTime;
        if (time > 500) {
            flag = true;
        }
        sLastPressTime = System.currentTimeMillis();
        return flag;
    }

    abstract void singleClick(View v);
}
