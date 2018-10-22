package com.android.payment.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.android.payment.R;
import com.android.payment.biz.WxPay;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class BaseWXEntryActivity extends Activity implements IWXAPIEventHandler {

    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, getString(R.string.WEIXIN_ID));
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp resp) {
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                WxPay.getPayCallBack().onPaySuccess();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                WxPay.getPayCallBack().onPayCancel();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                WxPay.getPayCallBack().onPayFailed("请求被拒绝");
                break;
            default:
                WxPay.getPayCallBack().onPayFailed("未知错误");
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }
}
