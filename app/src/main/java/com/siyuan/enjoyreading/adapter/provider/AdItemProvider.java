package com.siyuan.enjoyreading.adapter.provider;

import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidapp.adapter.BaseViewHolder;
import com.androidapp.adapter.provider.BaseItemProvider;
import com.androidapp.utils.ImageLoaderUtils;
import com.androidapp.utils.ScreenUtil;
import com.androidapp.utils.ToastUtils;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.entity.AdItem;
import com.siyuan.enjoyreading.entity.MultipleEntity;
import com.siyuan.enjoyreading.util.IntentUtil;

public class AdItemProvider extends BaseItemProvider<MultipleEntity, BaseViewHolder> {

    @Override
    public int viewType() {
        return MultipleItemQuickAdapter.ITEM_AD;
    }

    @Override
    public int layout() {
        return R.layout.listitem_ad;
    }

    @Override
    public void convert(BaseViewHolder viewHolder, MultipleEntity multipleEntity, int position) {
        AdItem item = (AdItem) multipleEntity;
        ImageView ivAdImageView = viewHolder.getView(R.id.iv_ad);
        setSizeByADSize(ivAdImageView, item.getWidth(), item.getHeight());
        ImageLoaderUtils.display(mContext, ivAdImageView, item.getImageUrl());
    }

    /**
     * 根据Html页面内容的宽高，转换为客户端的尺寸（等比缩放或等比放大）
     * @param ad_width
     * @param ad_height
     */
    private void setSizeByADSize(ImageView view, int ad_width, int ad_height) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            int height = (int) (ad_height * ScreenUtil.getScreenWidth(mContext) / (ad_width * 1.0));
            layoutParams = new ViewGroup.LayoutParams(ScreenUtil.getScreenWidth(mContext), height);
        } else {
            int height = (int) (ad_height * ScreenUtil.getScreenWidth(mContext) / (ad_width * 1.0));
            layoutParams.width = ScreenUtil.getScreenWidth(mContext);
            layoutParams.height = height;
        }
        view.setLayoutParams(layoutParams);
    }

    @Override
    public void onClick(BaseViewHolder helper, MultipleEntity data, int position) {
        super.onClick(helper, data, position);
        AdItem item = (AdItem) data;
        if (!TextUtils.isEmpty(item.getAction())) {
            try {
                mContext.startActivity(IntentUtil.getIntent(item.getAction()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}