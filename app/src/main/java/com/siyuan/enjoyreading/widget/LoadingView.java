package com.siyuan.enjoyreading.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.siyuan.enjoyreading.R;

public class LoadingView extends RelativeLayout {

    private View mBaseView;
    private ImageView mAnimImageView;
    private AnimationDrawable animationDrawable;

    public LoadingView(Context context) {
        super(context);
        init();
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mBaseView = LayoutInflater.from(getContext()).inflate(R.layout.widget_loading, this);
        mAnimImageView = mBaseView.findViewById(R.id.iv_animation);
        mAnimImageView.setImageResource(R.drawable.refresh_anim_drawable);
        animationDrawable = (AnimationDrawable) mAnimImageView.getDrawable();
        doAction();
    }

    private void doAction() {
        if (animationDrawable.isRunning()) {
            animationDrawable.stop();
        } else {
            animationDrawable.start();
        }
    }
}
