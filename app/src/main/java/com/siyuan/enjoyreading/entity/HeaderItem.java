package com.siyuan.enjoyreading.entity;

public class HeaderItem extends MultipleEntity{
    private String leftTitle;
    private String rightTitle;
    private boolean showMoreItem;

    public String getLeftTitle() {
        return leftTitle;
    }

    public void setLeftTitle(String leftTitle) {
        this.leftTitle = leftTitle;
    }

    public String getRightTitle() {
        return rightTitle;
    }

    public void setRightTitle(String rightTitle) {
        this.rightTitle = rightTitle;
    }

    public boolean isShowMoreItem() {
        return showMoreItem;
    }

    public void setShowMoreItem(boolean showMoreItem) {
        this.showMoreItem = showMoreItem;
    }
}
