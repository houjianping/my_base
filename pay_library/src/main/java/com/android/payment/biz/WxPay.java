package com.android.payment.biz;

import android.app.Activity;
import android.content.Context;

import com.android.payment.PayCallBack;
import com.android.payment.bean.Order;
import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WxPay extends BasePay {

    private static PayCallBack mPayCallback;
    private String mPayInfo;
    private PayReq mPayReq;
    private Activity mContext;

    public static PayCallBack getPayCallBack() {
        return mPayCallback;
    }

    public WxPay(Activity context) {
        mContext = context;
    }

    @Override
    public void setPayCallback(PayCallBack callback) {
        mPayCallback = callback;
    }

    @Override
    public void setPayInfo(String payInfo) {
        mPayInfo = payInfo;
    }

    @Override
    public void startPayment() {
        mPayCallback.onPayStart();
        try {
            Gson gson = new Gson();
            Order order = gson.fromJson(mPayInfo, Order.class);
            mPayReq.appId = order.getAppid();
            mPayReq.partnerId = order.getPartnerid();
            mPayReq.prepayId = order.getPrepayid();
            mPayReq.packageValue = order.getPackage_();
            mPayReq.nonceStr = order.getNoncestr();
            mPayReq.timeStamp = order.getTimestamp();
            mPayReq.sign = order.getSign();
        } catch (Exception e) {
            e.printStackTrace();
            mPayCallback.onPayFailed("支付订单异常");
            return;
        }
        IWXAPI api = WXAPIFactory.createWXAPI(mContext, mPayReq.appId);
        if (!api.isWXAppInstalled()) {
            mPayCallback.onPayFailed("应用程序未安装");
            return;
        }
        api.sendReq(mPayReq);
    }
}
