package com.siyuan.enjoyreading.adapter.provider;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.androidapp.adapter.BaseViewHolder;
import com.androidapp.adapter.provider.BaseItemProvider;
import com.androidapp.utils.ImageLoaderUtils;
import com.bumptech.glide.Glide;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.adapter.MultipleItemQuickAdapter;
import com.siyuan.enjoyreading.entity.MultipleEntity;
import com.siyuan.enjoyreading.entity.NewsItem;
import com.siyuan.enjoyreading.entity.VideoItem;
import com.siyuan.enjoyreading.ui.activity.currency.FullPagePlayerActivity;

public class VideoItemProvider extends BaseItemProvider<MultipleEntity, BaseViewHolder> {

    @Override
    public int viewType() {
        return MultipleItemQuickAdapter.ITEM_VIDEO_LIST;
    }

    @Override
    public int layout() {
        return R.layout.item_video_list;
    }

    @Override
    public void convert(BaseViewHolder viewHolder, MultipleEntity multipleEntity, int position) {
        final VideoItem item = (VideoItem) multipleEntity;

        ImageLoaderUtils.displayRound(mContext, (ImageView) viewHolder.getView(R.id.iv_avatar), item.getCoverUrl());

        viewHolder.setText(R.id.tv_content, item.getContent());
        viewHolder.setText(R.id.tv_name, item.getUserName());

        StandardGSYVideoPlayer videoPlayer = ((StandardGSYVideoPlayer) viewHolder.getView(R.id.video_player));
        videoPlayer.setUp(item.getVideoUrl(), true, "");
        videoPlayer.getTitleTextView().setVisibility(View.VISIBLE); //增加title
        videoPlayer.getBackButton().setVisibility(View.VISIBLE); //设置返回键

        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsItem newsItem = new NewsItem();
                newsItem.setMp4_url(item.getVideoUrl());
                newsItem.setCover(item.getCoverUrl());
                newsItem.setTitle(item.getContent());
                mContext.startActivity(FullPagePlayerActivity.getIntent(mContext, newsItem));
            }
        });
        videoPlayer.setIsTouchWiget(true); //是否可以滑动调整
        videoPlayer.setHideKey(true);
        videoPlayer.setThumbPlay(true);
        videoPlayer.setFullHideStatusBar(false);
        videoPlayer.setSaveBeforeFullSystemUiVisibility(((Activity) mContext).getWindow().getDecorView().getSystemUiVisibility());
        //增加封面
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(mContext).load(item.getCoverUrl()).into(imageView);
        videoPlayer.setThumbImageView(imageView);
        videoPlayer.getBackButton().setVisibility(View.GONE);
        videoPlayer.setDismissControlTime(1500);
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