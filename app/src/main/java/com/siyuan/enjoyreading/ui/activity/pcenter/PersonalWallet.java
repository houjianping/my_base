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
import com.siyuan.enjoyreading.api.ApiConfig;
import com.siyuan.enjoyreading.entity.RechargeBean;
import com.siyuan.enjoyreading.widget.RadioGroupPlus;

import java.util.ArrayList;
import java.util.List;

public class PersonalWallet extends BaseActivity {

    private RechargeItemAdapter mRechargeItemAdapter;
    private RadioGroupPlus mRadioGroupPlus;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.act_personal_wallet);
    }

    @Override
    protected void initView() {
        PagedGridView pagedGridView = findViewById(R.id.gv_recharge);
        mRadioGroupPlus = findViewById(R.id.rgp_group);
        List<RechargeBean> rechargeItems = new Gson().fromJson(ApiConfig.RECHARGE_LIST, new TypeToken<ArrayList<RechargeBean>>() {
        }.getType());
        mRechargeItemAdapter = new RechargeItemAdapter(this, rechargeItems);
        pagedGridView.setAdapter(mRechargeItemAdapter);
        pagedGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mRechargeItemAdapter.setSelected(mRechargeItemAdapter.getItem(position));
            }
        });
        findViewById(R.id.rl_alipay).setOnClickListener(mOnclickListener);
        findViewById(R.id.rl_wxpay).setOnClickListener(mOnclickListener);
        findViewById(R.id.rl_yepay).setOnClickListener(mOnclickListener);
        mRadioGroupPlus.check(R.id.rb_zfb);
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

    private View.OnClickListener mOnclickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rl_alipay:
                    mRadioGroupPlus.check(R.id.rb_zfb);
                    break;
                case R.id.rl_wxpay:
                    mRadioGroupPlus.check(R.id.rb_wx);
                    break;
                case R.id.rl_yepay:
                    mRadioGroupPlus.check(R.id.rb_ye);
                    break;
            }
        }
    };
}
