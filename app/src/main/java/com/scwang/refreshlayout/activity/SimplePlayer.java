package com.scwang.refreshlayout.activity;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.androidapp.base.activity.BaseActivity;
import com.scwang.refreshlayout.R;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;


public class SimplePlayer extends BaseActivity {

    StandardGSYVideoPlayer videoPlayer;

    OrientationUtils orientationUtils;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.BLACK);
        }
    }

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_simple_play);
    }

    @Override
    protected void initView() {
        videoPlayer =  (StandardGSYVideoPlayer)findViewById(R.id.video_player);
    }

    @Override
    protected void initData() {
        init();
    }

    @Override
    protected boolean isTranslucent() {
        return false;
    }

    private void init() {
        String source1 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
        videoPlayer.setUp(source1, true, "视频名称");
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
        videoPlayer.startPlayLogic();
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
}
