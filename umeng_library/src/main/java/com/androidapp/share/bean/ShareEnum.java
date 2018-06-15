package com.androidapp.share.bean;

import com.androidapp.share.R;

public enum  ShareEnum {

    moments("moments", R.string.moments, R.mipmap.ic_share_moments),
    wechat("wechat", R.string.wechat, R.mipmap.ic_share_wechat),
    weibo("weibo", R.string.weibo, R.mipmap.ic_share_weibo),
    qq("qq", R.string.qq, R.mipmap.ic_share_qq),
    qzone("qzone", R.string.qzone, R.mipmap.ic_share_qzone);

    private String itemType;
    private int itemTitle;
    private int itemIcon;

    ShareEnum(String type, int title, int icon) {
        itemType = type;
        itemTitle = title;
        itemIcon = icon;
    }

    public String getItemType() {
        return itemType;
    }

    public int getItemTitle() {
        return itemTitle;
    }

    public int getItemIcon() {
        return itemIcon;
    }
}
