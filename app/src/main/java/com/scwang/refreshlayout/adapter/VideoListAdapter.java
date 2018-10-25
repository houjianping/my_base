package com.scwang.refreshlayout.adapter;

import android.widget.ImageView;

import com.androidapp.adapter.BaseQuickAdapter;
import com.androidapp.adapter.BaseViewHolder;
import com.androidapp.utils.ImageLoaderUtils;
import com.scwang.refreshlayout.R;
import com.scwang.refreshlayout.bean.VideoItem;
import com.scwang.refreshlayout.ui.activity.SimplePlayer;

public class VideoListAdapter extends BaseQuickAdapter<VideoItem, BaseViewHolder> {

    public VideoListAdapter() {
        super(R.layout.item_video_list);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, VideoItem item) {
        viewHolder.setText(R.id.video_title, item.getTitle())
                .setText(R.id.video_desc, item.getDescription())
                .setText(R.id.video_actor, item.getVideosource())
                .setText(R.id.video_time, String.format(mContext.getResources().getString(R.string.video_play_times), String.valueOf(item.getPlayCount())));
        ImageLoaderUtils.display(mContext, (ImageView) viewHolder.getView(R.id.video_thumb), item.getCover());
    }
}
