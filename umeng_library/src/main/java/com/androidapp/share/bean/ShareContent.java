package com.androidapp.share.bean;

public class ShareContent {

    private String logo;
    private int shareObject;
    private String title;
    private String url;
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getShareObject() {
        return shareObject;
    }

    public void setShareObject(int shareObject) {
        this.shareObject = shareObject;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
