package com.androidapp.pagedgridview;

public abstract class PagedGridItem {
    private int icon;
    private String icon_url;
    private String title;
    private String badge_text;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBadge_text() {
        return badge_text;
    }

    public void setBadge_text(String badge_text) {
        this.badge_text = badge_text;
    }
}
