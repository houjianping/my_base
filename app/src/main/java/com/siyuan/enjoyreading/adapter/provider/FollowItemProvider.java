package com.siyuan.enjoyreading.adapter.provider;

import android.widget.ImageView;

import com.androidapp.adapter.BaseViewHolder;
import com.androidapp.adapter.provider.BaseItemProvider;
import com.androidapp.utils.ImageLoaderUtils;
import com.bumptech.glide.Glide;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.entity.FollowItem;
import com.siyuan.enjoyreading.entity.Movie;
import com.siyuan.enjoyreading.entity.MultipleEntity;
import com.siyuan.enjoyreading.ui.activity.knwoledge.ArticleDetailActivity;

public class FollowItemProvider extends BaseItemProvider<MultipleEntity,BaseViewHolder> {

    @Override
    public int viewType() {
        return MultipleItemQuickAdapter.ITEM_FOLLOW;
    }

    @Override
    public int layout() {
        return R.layout.listitem_follow;
    }

    @Override
    public void convert(BaseViewHolder viewHolder, MultipleEntity multipleEntity, int position) {
        FollowItem item = (FollowItem) multipleEntity;
        ImageLoaderUtils.displayRound(mContext, (ImageView) viewHolder.getView(R.id.icon), item.picaddr);
    }

    @Override
    public void onClick(BaseViewHolder helper, MultipleEntity data, int position) {
        try {
            mContext.startActivity(ArticleDetailActivity.getIntent(mContext, "https://mil.sina.cn/2018-11-06/detail-ihmutuea7434493.d.html?from=wap"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, MultipleEntity data, int position) {
        return true;
    }
}