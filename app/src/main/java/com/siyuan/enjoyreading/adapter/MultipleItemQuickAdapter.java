package com.siyuan.enjoyreading.adapter;

import com.androidapp.adapter.BaseViewHolder;
import com.androidapp.adapter.MultipleItemAdapter;
import com.siyuan.enjoyreading.adapter.provider.AdItemProvider;
import com.siyuan.enjoyreading.adapter.provider.BannerItemProvider;
import com.siyuan.enjoyreading.adapter.provider.CircleItemProvider;
import com.siyuan.enjoyreading.adapter.provider.CouponProvider;
import com.siyuan.enjoyreading.adapter.provider.FollowItemProvider;
import com.siyuan.enjoyreading.adapter.provider.HeaderItemProvider;
import com.siyuan.enjoyreading.adapter.provider.IndexGridItemProvider;
import com.siyuan.enjoyreading.adapter.provider.KnowledgeListItemProvider;
import com.siyuan.enjoyreading.adapter.provider.NotificationItemProvider;
import com.siyuan.enjoyreading.adapter.provider.OrderMovieItemProvider;
import com.siyuan.enjoyreading.adapter.provider.TestItemProvider;
import com.siyuan.enjoyreading.adapter.provider.NewsItemProvider;
import com.siyuan.enjoyreading.adapter.provider.VideoItemProvider;
import com.siyuan.enjoyreading.adapter.provider.WalletItemProvider;
import com.siyuan.enjoyreading.entity.AdItem;
import com.siyuan.enjoyreading.entity.BannerItemEntity;
import com.siyuan.enjoyreading.entity.Coupon;
import com.siyuan.enjoyreading.entity.FollowItem;
import com.siyuan.enjoyreading.entity.GridItem;
import com.siyuan.enjoyreading.entity.HeaderItem;
import com.siyuan.enjoyreading.entity.KnowledgeListItem;
import com.siyuan.enjoyreading.entity.Movie;
import com.siyuan.enjoyreading.entity.MultipleEntity;
import com.siyuan.enjoyreading.entity.NotificationItem;
import com.siyuan.enjoyreading.entity.OrderMovie;
import com.siyuan.enjoyreading.entity.NewsItem;
import com.siyuan.enjoyreading.entity.VideoItem;
import com.siyuan.enjoyreading.entity.WalletItem;
import com.siyuan.enjoyreading.entity.circle.CircleItem;

public class MultipleItemQuickAdapter<T extends MultipleEntity> extends MultipleItemAdapter<T, BaseViewHolder> {

    public static final int ITEM_TEST = 0;
    public static final int ITEM_NEWS = 1;
    public static final int ITEM_CIRCLE = 2;
    public static final int ITEM_KNOWLEDGE_LIST_ITEM = 3;
    public static final int ITEM_ORDER_MOVIE = 4;
    public static final int ITEM_HEADER = 5;
    public static final int ITEM_BANNER = 6;
    public static final int ITEM_GRID = 7;
    public static final int ITEM_AD = 8;
    public static final int ITEM_WALLET = 9;
    public static final int ITEM_COUPON = 10;
    public static final int ITEM_NOTIFICATION = 11;
    public static final int ITEM_VIDEO_LIST = 12;
    public static final int ITEM_FOLLOW = 13;

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
        } else if (entity instanceof NewsItem) {
            return ITEM_NEWS;
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
        } else if (entity instanceof WalletItem) {
            return ITEM_WALLET;
        } else if (entity instanceof Coupon) {
            return ITEM_COUPON;
        } else if (entity instanceof NotificationItem) {
            return ITEM_NOTIFICATION;
        } else if (entity instanceof VideoItem) {
            return ITEM_VIDEO_LIST;
        } else if (entity instanceof FollowItem) {
            return ITEM_FOLLOW;
        }
        return ITEM_NEWS;
    }

    @Override
    public void registerItemProvider() {
        try {
            mProviderDelegate.registerProvider(new NewsItemProvider());
            mProviderDelegate.registerProvider(new TestItemProvider());
            mProviderDelegate.registerProvider(new CircleItemProvider());
            mProviderDelegate.registerProvider(new KnowledgeListItemProvider());
            mProviderDelegate.registerProvider(new OrderMovieItemProvider());
            mProviderDelegate.registerProvider(new HeaderItemProvider());
            mProviderDelegate.registerProvider(new BannerItemProvider());
            mProviderDelegate.registerProvider(new IndexGridItemProvider());
            mProviderDelegate.registerProvider(new AdItemProvider());
            mProviderDelegate.registerProvider(new WalletItemProvider());
            mProviderDelegate.registerProvider(new CouponProvider());
            mProviderDelegate.registerProvider(new NotificationItemProvider());
            mProviderDelegate.registerProvider(new VideoItemProvider());
            mProviderDelegate.registerProvider(new FollowItemProvider());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
