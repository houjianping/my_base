package com.siyuan.enjoyreading.entity;

public class WebInfo {
    private String title;
    private String url;
    private String source;
    private long date;
    private String body;
    private boolean useWebUrl;

    public boolean isUseWebUrl() {
        return useWebUrl;
    }

    public void setUseWebUrl(boolean useWebUrl) {
        this.useWebUrl = useWebUrl;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
