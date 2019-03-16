package com.siyuan.enjoyreading.adapter.provider;

import android.content.Intent;

import com.androidapp.adapter.BaseViewHolder;
import com.androidapp.adapter.provider.BaseItemProvider;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.entity.MultipleEntity;
import com.siyuan.enjoyreading.ui.activity.pcenter.PersonalWalletDetail;

public class WalletItemProvider extends BaseItemProvider<MultipleEntity,BaseViewHolder> {

    @Override
    public int viewType() {
        return MultipleItemQuickAdapter.ITEM_WALLET;
    }

    @Override
    public int layout() {
        return R.layout.listitem_wallet_item;
    }

    @Override
    public void convert(BaseViewHolder viewHolder, MultipleEntity multipleEntity, int position) {
//        VideoItem item = (VideoItem) multipleEntity;
//        viewHolder.setText(R.id.video_title, item.getTitle())
//                .setText(R.id.video_desc, item.getDescription())
//                .setText(R.id.video_actor, item.getVideosource())
//                .setText(R.id.video_time, String.format(mContext.getResources().getString(R.string.video_play_times), String.valueOf(item.getPlayCount())));
//        ImageLoaderUtils.display(mContext, (ImageView) viewHolder.getView(R.id.video_thumb), item.getCover());
    }

    @Override
    public void onClick(BaseViewHolder helper, MultipleEntity data, int position) {
        mContext.startActivity(new Intent(mContext, PersonalWalletDetail.class));
    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, MultipleEntity data, int position) {
        return true;
    }
}