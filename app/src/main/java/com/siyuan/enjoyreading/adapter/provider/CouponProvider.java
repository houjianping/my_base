package com.siyuan.enjoyreading.adapter.provider;

import android.content.Intent;

import com.androidapp.adapter.BaseViewHolder;
import com.androidapp.adapter.provider.BaseItemProvider;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.entity.Coupon;
import com.siyuan.enjoyreading.entity.MultipleEntity;
import com.siyuan.enjoyreading.ui.activity.pcenter.PersonalWalletDetail;

public class CouponProvider extends BaseItemProvider<MultipleEntity,BaseViewHolder> {

    @Override
    public int viewType() {
        return MultipleItemQuickAdapter.ITEM_COUPON;
    }

    @Override
    public int layout() {
        return R.layout.listitem_coupon_item;
    }

    @Override
    public void convert(BaseViewHolder viewHolder, MultipleEntity multipleEntity, int position) {
        Coupon item = (Coupon) multipleEntity;
        int bgColor = mContext.getResources().getColor(R.color.app_color_theme_0 + position % 10);
        viewHolder.getView(R.id.card_view).setBackgroundColor(bgColor);
    }

    @Override
    public void onClick(BaseViewHolder helper, MultipleEntity data, int position) {
        mContext.startActivity(new Intent(mContext, PersonalWalletDetail.class));
    }
}