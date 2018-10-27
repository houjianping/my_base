package com.siyuan.enjoyreading.adapter;

import com.androidapp.adapter.BaseViewHolder;
import com.androidapp.adapter.MultipleItemAdapter;
import com.siyuan.enjoyreading.adapter.provider.CircleItemProvider;
import com.siyuan.enjoyreading.adapter.provider.KnowledgeListItemProvider;
import com.siyuan.enjoyreading.adapter.provider.OrderMovieItemProvider;
import com.siyuan.enjoyreading.adapter.provider.TestItemProvider;
import com.siyuan.enjoyreading.adapter.provider.VideoItemProvider;
import com.siyuan.enjoyreading.entity.KnowledgeListItem;
import com.siyuan.enjoyreading.entity.Movie;
import com.siyuan.enjoyreading.entity.MultipleEntity;
import com.siyuan.enjoyreading.entity.OrderMovie;
import com.siyuan.enjoyreading.entity.VideoItem;
import com.siyuan.enjoyreading.entity.circle.CircleItem;

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
