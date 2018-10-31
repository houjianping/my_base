package com.siyuan.enjoyreading.ui.activity.currency;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.androidapp.activity.BaseActivity;
import com.bumptech.glide.Glide;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.entity.VideoItem;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;


public class FullPagePlayerActivity extends BaseActivity {

    public static final String KEY_VIDEO_URL = "video_url";
    public static final String KEY_VIDEO_THUMB = "video_thumb";
    public static final String KEY_VIDEO_TITLE = "video_title";

    private StandardGSYVideoPlayer videoPlayer;
    private OrientationUtils orientationUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.BLACK);
        }
    }

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.act_full_page_player);
    }

    @Override
    protected void initView() {
        videoPlayer = findViewById(R.id.video_player);
    }

    @Override
    protected void initData() {
        Bundle bundle = getIntent().getExtras();
        String videoTitle = bundle.getString(KEY_VIDEO_TITLE);
        String videoUrl = bundle.getString(KEY_VIDEO_URL);
        String videoCover = bundle.getString(KEY_VIDEO_THUMB);
        init(videoTitle, videoUrl, videoCover);
    }

    @Override
    protected boolean isTranslucent() {
        return false;
    }

    private void init(String title, String url, String cover) {
        videoPlayer.setUp(url, true, title);
        videoPlayer.getTitleTextView().setVisibility(View.VISIBLE); //增加title
        videoPlayer.getBackButton().setVisibility(View.VISIBLE); //设置返回键
        orientationUtils = new OrientationUtils(this, videoPlayer); //设置旋转
        //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        videoPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orientationUtils.resolveByClick();
            }
        });
        videoPlayer.setIsTouchWiget(true); //是否可以滑动调整
        videoPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        videoPlayer.setHideKey(false);
        videoPlayer.setThumbPlay(true);
        videoPlayer.setFullHideStatusBar(false);
        videoPlayer.setSaveBeforeFullSystemUiVisibility(getWindow().getDecorView().getSystemUiVisibility());
        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        Glide.with(mContext).load(cover).into(imageView);
        videoPlayer.setThumbImageView(imageView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        videoPlayer.onVideoPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        videoPlayer.onVideoResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onBackPressed() {
        //先返回正常状态
        if (orientationUtils.getScreenType() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            videoPlayer.getFullscreenButton().performClick();
            return;
        }
        //释放所有
        videoPlayer.setVideoAllCallBack(null);
        super.onBackPressed();
    }

    public static Intent getIntent(Context context, VideoItem videoItem) {
        Intent intent = new Intent(context, FullPagePlayerActivity.class);
        intent.putExtra(KEY_VIDEO_URL, videoItem.getMp4_url());
        intent.putExtra(KEY_VIDEO_THUMB, videoItem.getCover());
        intent.putExtra(KEY_VIDEO_TITLE, videoItem.getTitle());
        return intent;
    }
}
