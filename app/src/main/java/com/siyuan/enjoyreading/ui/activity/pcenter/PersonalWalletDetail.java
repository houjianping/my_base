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

import java.util.ArrayList;
import java.util.List;

public class PersonalWalletDetail extends BaseActivity {

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.act_wallet_detail);
    }

    @Override
    protected void initView() {
    }
    @Override
    protected void initData() {
    }
}
