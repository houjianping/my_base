package com.scwang.refreshlayout.adapter;

import com.androidapp.adapter.BaseViewHolder;
import com.androidapp.adapter.MultipleItemAdapter;
import com.scwang.refreshlayout.adapter.provider.CircleItemProvider;
import com.scwang.refreshlayout.adapter.provider.KnowledgeListItemProvider;
import com.scwang.refreshlayout.adapter.provider.OrderMovieItemProvider;
import com.scwang.refreshlayout.adapter.provider.TestItemProvider;
import com.scwang.refreshlayout.adapter.provider.VideoItemProvider;
import com.scwang.refreshlayout.entity.KnowledgeListItem;
import com.scwang.refreshlayout.entity.Movie;
import com.scwang.refreshlayout.entity.MultipleEntity;
import com.scwang.refreshlayout.entity.OrderMovie;
import com.scwang.refreshlayout.entity.VideoItem;
import com.scwang.refreshlayout.entity.circle.CircleItem;

public class MultipleItemQuickAdapter<T extends MultipleEntity> extends MultipleItemAdapter<T, BaseViewHolder> {

    public static final int ITEM_TEST = 0;
    public static final int ITEM_VIDEO = 1;
    public static final int ITEM_CIRCLE = 2;
    public static final int ITEM_KNOWLEDGE_LIST_ITEM = 3;
    public static final int ITEM_ORDER_MOVIE = 4;

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
        } else if (entity instanceof KnowledgeListItem) {
            return ITEM_KNOWLEDGE_LIST_ITEM;
        } else if (entity instanceof VideoItem) {
            return ITEM_VIDEO;
        } else if (entity instanceof OrderMovie) {
            return ITEM_ORDER_MOVIE;
        }
        return ITEM_VIDEO;
    }

    @Override
    public void registerItemProvider() {
        try {
            mProviderDelegate.registerProvider(new VideoItemProvider());
            mProviderDelegate.registerProvider(new TestItemProvider());
            mProviderDelegate.registerProvider(new CircleItemProvider());
            mProviderDelegate.registerProvider(new KnowledgeListItemProvider());
            mProviderDelegate.registerProvider(new OrderMovieItemProvider());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
