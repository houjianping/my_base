package com.scwang.refreshlayout.adapter;

import android.view.View;
import android.widget.ImageView;

import com.androidapp.adapter.BaseQuickAdapter;
import com.androidapp.adapter.BaseViewHolder;
import com.androidapp.utils.ImageLoaderUtils;
import com.bumptech.glide.Glide;
import com.scwang.refreshlayout.R;
import com.scwang.refreshlayout.bean.VideoItem;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

public class VideoListAdapter extends BaseQuickAdapter<VideoItem, BaseViewHolder> {

    public VideoListAdapter() {
        super(R.layout.item_video_list);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, VideoItem item) {
        viewHolder.setText(R.id.tv_from, item.getTopicName())
                .setText(R.id.tv_play_time, String.format(mContext.getResources().getString(R.string.video_play_times), String.valueOf(item.getPlayCount()))).addOnClickListener(R.id.tv_play_time);
        ImageLoaderUtils.displayRound(mContext, (ImageView) viewHolder.getView(R.id.iv_logo), item.getTopicImg());
        StandardGSYVideoPlayer videoPlayer = viewHolder.getView(R.id.videoplayer);
        videoPlayer.setUp(item.getMp4_url(), true, item.getTitle());
        videoPlayer.getTitleTextView().setVisibility(View.VISIBLE); //增加title
        videoPlayer.getBackButton().setVisibility(View.GONE); //设置返回键
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        videoPlayer.setIsTouchWiget(true); //是否可以滑动调整
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        videoPlayer.setHideKey(false);
        videoPlayer.setThumbPlay(true);
        videoPlayer.setFullHideStatusBar(false);
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(mContext).load(item.getCover()).into(imageView);
        videoPlayer.setThumbImageView(imageView);
    }
}
