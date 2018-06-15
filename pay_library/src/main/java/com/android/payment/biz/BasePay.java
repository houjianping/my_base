package com.android.payment.biz;

import com.android.payment.PayCallBack;

public abstract class BasePay {

    /**
     * 支付接口
     */
    public abstract void setPayCallback(PayCallBack callback);

    /**
     * 设置支付订单信息
     *
     * @param payInfo
     */
    public abstract void setPayInfo(String payInfo);

    /**
     * 调用支付功能
     */
    public abstract void startPayment();
}
