package com.siyuan.enjoyreading.adapter;

import com.androidapp.adapter.BaseViewHolder;
import com.androidapp.adapter.MultipleItemAdapter;
import com.siyuan.enjoyreading.adapter.provider.AdItemProvider;
import com.siyuan.enjoyreading.adapter.provider.BannerItemProvider;
import com.siyuan.enjoyreading.adapter.provider.CircleItemProvider;
import com.siyuan.enjoyreading.adapter.provider.HeaderItemProvider;
import com.siyuan.enjoyreading.adapter.provider.IndexGridItemProvider;
import com.siyuan.enjoyreading.adapter.provider.KnowledgeListItemProvider;
import com.siyuan.enjoyreading.adapter.provider.OrderMovieItemProvider;
import com.siyuan.enjoyreading.adapter.provider.TestItemProvider;
import com.siyuan.enjoyreading.adapter.provider.VideoItemProvider;
import com.siyuan.enjoyreading.entity.AdItem;
import com.siyuan.enjoyreading.entity.BannerItem;
import com.siyuan.enjoyreading.entity.BannerItemEntity;
import com.siyuan.enjoyreading.entity.GridItem;
import com.siyuan.enjoyreading.entity.HeaderItem;
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
    public static final int ITEM_HEADER = 5;
    public static final int ITEM_BANNER = 6;
    public static final int ITEM_GRID = 7;
    public static final int ITEM_AD = 8;

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
        } else if (entity instanceof HeaderItem) {
            return ITEM_HEADER;
        } else if (entity instanceof BannerItemEntity) {
            return ITEM_BANNER;
        } else if (entity instanceof GridItem) {
            return ITEM_GRID;
        } else if (entity instanceof AdItem) {
            return ITEM_AD;
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
            mProviderDelegate.registerProvider(new HeaderItemProvider());
            mProviderDelegate.registerProvider(new BannerItemProvider());
            mProviderDelegate.registerProvider(new IndexGridItemProvider());
            mProviderDelegate.registerProvider(new AdItemProvider());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
