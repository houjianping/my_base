package com.siyuan.enjoyreading.ui.activity.pcenter;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.androidapp.activity.BaseActivity;
import com.androidapp.pagedgridview.PagedGridView;
import com.androidapp.utils.ToastUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.RechargeItemAdapter;
import com.siyuan.enjoyreading.api.ApiConfig;
import com.siyuan.enjoyreading.entity.RechargeBean;
import com.siyuan.enjoyreading.ui.activity.currency.AppWebActivity;

import java.util.ArrayList;
import java.util.List;

public class PersonalWalletDetail extends BaseActivity {

    private View mBillComplaint;
    private View mBillCommonProblem;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.act_wallet_detail);
    }

    @Override
    protected void initView() {
        mBillComplaint = findViewById(R.id.bill_complaint);
        mBillCommonProblem = findViewById(R.id.bill_common_problem);

        mBillComplaint.setOnClickListener(mOnClickListener);
        mBillCommonProblem.setOnClickListener(mOnClickListener);
    }
    @Override
    protected void initData() {
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mBillComplaint) {
                doStartActivity(AppWebActivity.class, AppWebActivity.getBundle("http://www.baidu.com","百度"));
            } else if (v == mBillCommonProblem) {
                doStartActivity(AppWebActivity.class, AppWebActivity.getBundle("http://www.baidu.com","百度"));
            }
        }
    };
}
