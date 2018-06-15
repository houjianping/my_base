package com.android.payment;

public enum PayTypeEnum {

    wx("wx"),
    al("al");

    private String payType;

    PayTypeEnum(String type) {
        this.payType = type;
    }

    public String getPayType() {
        return payType;
    }
}
