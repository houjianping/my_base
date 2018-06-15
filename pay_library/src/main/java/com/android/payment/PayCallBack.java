package com.android.payment;

public interface PayCallBack {
    void onPayStart();
    void onPaySuccess();
    void onPayFailed(String info);
    void onPayCancel();
}
