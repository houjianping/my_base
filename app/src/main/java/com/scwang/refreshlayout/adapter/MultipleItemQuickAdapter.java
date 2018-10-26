package com.scwang.refreshlayout.adapter;

import com.androidapp.adapter.BaseViewHolder;
import com.androidapp.adapter.MultipleItemAdapter;
import com.scwang.refreshlayout.adapter.provider.CircleItemProvider;
import com.scwang.refreshlayout.adapter.provider.TestItemProvider;
import com.scwang.refreshlayout.adapter.provider.VideoItemProvider;
import com.scwang.refreshlayout.entity.Movie;
import com.scwang.refreshlayout.entity.MultipleEntity;
import com.scwang.refreshlayout.entity.circle.CircleItem;

public class MultipleItemQuickAdapter<T extends MultipleEntity> extends MultipleItemAdapter<T, BaseViewHolder> {

    public static final int ITEM_TEST = 0;
    public static final int ITEM_VIDEO = 1;
    public static final int ITEM_CIRCLE = 2;

    public MultipleItemQuickAdapter() {
        super(null);
        finishInitialize();
    }

    @Override
    protected int getViewType(T entity) {
        if (entity instanceof Movie) {
            return ITEM_TEST;
        } else if (entity instanceof CircleItem) {
            return ITEM_CIRCLE;
        }
        if (entity.getViewType() != null && entity.getViewType().equals("video")) {
            return ITEM_VIDEO;
        }
        return ITEM_VIDEO;
    }

    @Override
    public void registerItemProvider() {
        try {
            mProviderDelegate.registerProvider(new VideoItemProvider());
            mProviderDelegate.registerProvider(new TestItemProvider());
            mProviderDelegate.registerProvider(new CircleItemProvider());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
