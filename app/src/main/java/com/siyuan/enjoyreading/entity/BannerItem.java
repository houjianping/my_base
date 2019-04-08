package com.siyuan.enjoyreading.entity;

public class BannerItem {

    public String url;
    public String title;

    public BannerItem() {
    }
    public BannerItem(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
