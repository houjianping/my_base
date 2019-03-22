package com.siyuan.enjoyreading.adapter.provider;

import android.widget.ImageView;
import android.widget.Toast;

import com.androidapp.adapter.BaseViewHolder;
import com.androidapp.adapter.provider.BaseItemProvider;
import com.androidapp.utils.ImageLoaderUtils;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.entity.MultipleEntity;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.entity.NewsItem;

public class NewsItemProvider extends BaseItemProvider<MultipleEntity,BaseViewHolder> {

    @Override
    public int viewType() {
        return MultipleItemQuickAdapter.ITEM_NEWS;
    }

    @Override
    public int layout() {
        return R.layout.item_news_list;
    }

    @Override
    public void convert(BaseViewHolder viewHolder, MultipleEntity multipleEntity, int position) {
        NewsItem item = (NewsItem) multipleEntity;
        viewHolder.setText(R.id.video_title, item.getTitle())
                .setText(R.id.video_desc, item.getDescription())
                .setText(R.id.video_actor, item.getVideosource())
                .setText(R.id.video_time, String.format(mContext.getResources().getString(R.string.video_play_times), String.valueOf(item.getPlayCount())));
        ImageLoaderUtils.display(mContext, (ImageView) viewHolder.getView(R.id.video_thumb), item.getCover());
    }

    @Override
    public void onClick(BaseViewHolder helper, MultipleEntity data, int position) {
        Toast.makeText(mContext, "click", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onLongClick(BaseViewHolder helper, MultipleEntity data, int position) {
        return true;
    }
}