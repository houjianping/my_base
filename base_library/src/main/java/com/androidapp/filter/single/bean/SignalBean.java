package com.androidapp.filter.single.bean;

public class SignalBean {
    private String keyword;
    private String text;
    private boolean isSelected;

    public SignalBean(String key, String text) {
        this.text = text;
        this.keyword = key;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
