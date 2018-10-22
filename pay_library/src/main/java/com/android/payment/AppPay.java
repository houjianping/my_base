package com.android.payment;

import android.app.Activity;
import android.content.Context;

import com.android.payment.biz.AliPay;
import com.android.payment.biz.BasePay;
import com.android.payment.biz.WxPay;

public class AppPay {

    private PayTypeEnum mPayType;
    private String mPayInfo;
    private Activity mContext;

    /**
     * @param payType 支付方式
     * @param payInfo 订单信息
     */
    public AppPay(Activity context, PayTypeEnum payType, String payInfo) {
        mContext = context;
        mPayType = payType;
        mPayInfo = payInfo;
    }

    /**
     * 开始支付
     *
     * @param callBack
     */
    public void doPay(PayCallBack callBack) {
        BasePay pay = null;
        switch (mPayType) {
            case wx:
                pay = new WxPay(mContext);
                break;
            case al:
                pay = new AliPay(mContext);
                break;
            default:
                break;
        }
        pay.setPayCallback(callBack);
        pay.setPayInfo(mPayInfo);
    }
}
