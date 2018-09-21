package com.scwang.refreshlayout.ui.ui;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidapp.base.activity.BaseActivity;
import com.bumptech.glide.Glide;
import com.scwang.refreshlayout.R;
import com.scwang.refreshlayout.ui.MainActivity;
import com.scwang.refreshlayout.ui.ui.common.YuedanWebActivity;

public class SplashActivity extends BaseActivity {

    private static final int MSG_FINISH_CURRENT = 1;
    private static final int MSG_UPDATE_TIME = 2;
    private View mContentView;
    private View mBottomLogo;
    private ImageView mAdImageView;
    private TextView mSkipTextView;
    private int mMaxTime = 6;
    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == mSkipTextView) {
                redirectTo();
            } else if (v == mAdImageView) {
                doStartActivity(YuedanWebActivity.class, null);
            }
        }
    };
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (isFinishing()) {
                return;
            }
            switch (msg.what) {
                case MSG_FINISH_CURRENT:
                    redirectTo();
                    break;
                case MSG_UPDATE_TIME:
                    mMaxTime--;
                    if (mMaxTime >= 0)
                        mSkipTextView.setText("跳过(" + mMaxTime + ")");
                    mHandler.sendEmptyMessageDelayed(MSG_UPDATE_TIME, 1000);
                    break;
            }
        }
    };

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.app_start);
        mContentView = View.inflate(this, R.layout.app_start, null);
        setContentView(mContentView);
    }

    @Override
    protected void initView() {
        mSkipTextView = (TextView) findViewById(R.id.tv_skip);
        mBottomLogo = findViewById(R.id.bottom_logo);
        mAdImageView = (ImageView) findViewById(R.id.app_start_view);
        mSkipTextView.setOnClickListener(mOnClickListener);
        mAdImageView.setOnClickListener(mOnClickListener);
        mAdImageView.setClickable(false);
    }

    @Override
    protected void initData() {
        AlphaAnimation aa = new AlphaAnimation(0.5f, 1.0f);
        aa.setDuration(1800);
        mContentView.startAnimation(aa);
        aa.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                showAds();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationStart(Animation animation) {
            }
        });
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
    }

    private void showAds() {
        mAdImageView.setClickable(true);
        mSkipTextView.setVisibility(View.VISIBLE);
        mBottomLogo.setVisibility(View.GONE);
        Glide.with(getBaseContext()).load(R.drawable.splash_bg).into(mAdImageView);
        mHandler.sendEmptyMessageDelayed(MSG_FINISH_CURRENT, mMaxTime * 1000);
        mHandler.sendEmptyMessageDelayed(MSG_UPDATE_TIME, 1000);
    }

    private void redirectTo() {
        if (isInForeground)
            doStartActivityThenKill(MainActivity.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mMaxTime <= 1) {
            redirectTo();
        }
    }
}
