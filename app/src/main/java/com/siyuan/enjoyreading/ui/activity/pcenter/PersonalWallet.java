package com.siyuan.enjoyreading.ui.activity.pcenter;

import android.content.Intent;
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
import com.siyuan.enjoyreading.adapter.RecommendItemAdapter;
import com.siyuan.enjoyreading.api.ApiConfig;
import com.siyuan.enjoyreading.entity.Movie;
import com.siyuan.enjoyreading.entity.RechargeBean;
import com.siyuan.enjoyreading.ui.activity.knwoledge.KnowledgeDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class PersonalWallet extends BaseActivity {

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.act_personal_wallet);
    }

    @Override
    protected void initView() {
        PagedGridView pagedGridView = findViewById(R.id.gv_recharge);
        List<RechargeBean> rechargeItems = new Gson().fromJson(ApiConfig.RECHARGE_LIST, new TypeToken<ArrayList<RechargeBean>>() {
        }.getType());
        pagedGridView.setAdapter(new RechargeItemAdapter(this, rechargeItems));
        pagedGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.show("Hello Recharge");
            }
        });
    }
    @Override
    protected void initData() {
        mTitleBar.getRightImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PersonalWallet.this, PersonalWalletList.class));
            }
        });
    }
}
