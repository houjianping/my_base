package com.android.payment.biz;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;

import com.alipay.sdk.app.PayTask;
import com.android.payment.PayCallBack;
import com.android.payment.bean.AliPayResult;

public class AliPay extends BasePay {

    private static final int SDK_PAY_FLAG = 1;

    private PayCallBack mPayCallback;
    private String mPayInfo;
    private Activity mContext;

    public AliPay(Activity context) {
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
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(mContext);
                String result = alipay.pay(mPayInfo, true);
                handlerResult(SDK_PAY_FLAG, result);
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    private void handlerResult(int what, String result) {
        switch (what) {
            case SDK_PAY_FLAG: {
                AliPayResult payResult = new AliPayResult(result);
                String resultStatus = payResult.getResultStatus();
                if (TextUtils.equals(resultStatus, "9000")) {
                    mPayCallback.onPaySuccess();
                } else {
                    if (TextUtils.equals(resultStatus, "8000")) {
                        mPayCallback.onPaySuccess();
                    } else if (TextUtils.equals(resultStatus, "6001") || TextUtils.equals(resultStatus, "4000")) {
                        mPayCallback.onPayCancel();
                    } else {
                        mPayCallback.onPayFailed(resultStatus);
                    }
                }
                break;
            }
            default:
                break;
        }
    }
}
