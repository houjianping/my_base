package com.siyuan.enjoyreading.adapter.provider;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidapp.adapter.BaseViewHolder;
import com.androidapp.adapter.provider.BaseItemProvider;
import com.androidapp.banner.Banner;
import com.androidapp.banner.listener.OnBannerListener;
import com.androidapp.banner.loader.ImageLoader;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.entity.BannerItem;
import com.siyuan.enjoyreading.entity.BannerItemEntity;
import com.siyuan.enjoyreading.entity.HeaderItem;
import com.siyuan.enjoyreading.entity.MultipleEntity;
import com.siyuan.enjoyreading.ui.fragment.OrderFragment;

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
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(item.getBannerItems());
        banner.setOnBannerListener(mOnBannerListener);
        banner.start();
    }

    private OnBannerListener mOnBannerListener = new OnBannerListener() {
        @Override
        public void OnBannerClick(int position) {
            Toast.makeText(mContext, "si=" + position, Toast.LENGTH_SHORT).show();
        }
    };

    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            imageView.setImageResource(((BannerItem) path).pic);
        }
    }
}