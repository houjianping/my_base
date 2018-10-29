package com.siyuan.enjoyreading.entity;

import java.util.List;

public class BannerItemEntity extends MultipleEntity{
    private List<BannerItem> bannerItems;

    public List<BannerItem> getBannerItems() {
        return bannerItems;
    }

    public void setBannerItems(List<BannerItem> bannerItems) {
        this.bannerItems = bannerItems;
    }
}
