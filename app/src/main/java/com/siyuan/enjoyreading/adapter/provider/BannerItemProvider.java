package com.siyuan.enjoyreading.adapter.provider;

import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.androidapp.adapter.BaseViewHolder;
import com.androidapp.adapter.provider.BaseItemProvider;
import com.androidapp.banner.Banner;
import com.androidapp.banner.BannerConfig;
import com.androidapp.banner.listener.OnBannerListener;
import com.androidapp.utils.ScreenUtil;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.entity.BannerItemEntity;
import com.siyuan.enjoyreading.entity.MultipleEntity;
import com.siyuan.enjoyreading.util.BannerImageLoader;

public class BannerItemProvider extends BaseItemProvider<MultipleEntity,BaseViewHolder> {

    @Override
    public int viewType() {
        return MultipleItemQuickAdapter.ITEM_BANNER;
    }

    @Override
    public int layout() {
        return R.layout.listitem_movie_header;
    }

    @Override
    public void convert(BaseViewHolder viewHolder, MultipleEntity multipleEntity, int position) {
        BannerItemEntity item = (BannerItemEntity) multipleEntity;
        Banner banner = viewHolder.getView(R.id.banner);
        if (banner.getLayoutParams() instanceof RecyclerView.LayoutParams) {
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) banner.getLayoutParams();
            params.height = ScreenUtil.dip2px(mContext, 100);
            banner.setLayoutParams(params);
        }
        banner.setImageLoader(new BannerImageLoader());
        banner.setImages(item.getBannerItems());
        banner.setOnBannerListener(mOnBannerListener);
        banner.setIndicatorGravity(BannerConfig.RIGHT);
        banner.start();
    }

    private OnBannerListener mOnBannerListener = new OnBannerListener() {
        @Override
        public void OnBannerClick(int position) {
            Toast.makeText(mContext, "si=" + position, Toast.LENGTH_SHORT).show();
        }
    };
}