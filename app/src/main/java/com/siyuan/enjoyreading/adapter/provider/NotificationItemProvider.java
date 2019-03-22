package com.siyuan.enjoyreading.adapter.provider;

import android.content.Intent;

import com.androidapp.adapter.BaseViewHolder;
import com.androidapp.adapter.provider.BaseItemProvider;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.entity.MultipleEntity;
import com.siyuan.enjoyreading.ui.activity.pcenter.PersonalWalletDetail;

public class NotificationItemProvider extends BaseItemProvider<MultipleEntity,BaseViewHolder> {

    @Override
    public int viewType() {
        return MultipleItemQuickAdapter.ITEM_NOTIFICATION;
    }

    @Override
    public int layout() {
        return R.layout.listitem_notification_item;
    }

    @Override
    public void convert(BaseViewHolder viewHolder, MultipleEntity multipleEntity, int position) {
    }

    @Override
    public void onClick(BaseViewHolder helper, MultipleEntity data, int position) {
    }
}