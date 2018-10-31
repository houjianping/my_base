package com.siyuan.enjoyreading.widget;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.androidapp.smartrefresh.layout.api.RefreshHeader;
import com.androidapp.smartrefresh.layout.api.RefreshKernel;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.constant.RefreshState;
import com.androidapp.smartrefresh.layout.constant.SpinnerStyle;
import com.siyuan.enjoyreading.R;

public class MyRefreshLottieHeader extends LinearLayout implements RefreshHeader {

    public static String REFRESH_HEADER_PULLING = null;//"下拉可以刷新";
    public static String REFRESH_HEADER_REFRESHING = null;//"正在刷新...";
    public static String REFRESH_HEADER_LOADING = null;//"正在加载...";
    public static String REFRESH_HEADER_RELEASE = null;//"释放立即刷新";
    public static String REFRESH_HEADER_FINISH = null;//"刷新完成";
    public static String REFRESH_HEADER_FAILED = null;//"刷新失败";
    public static String REFRESH_HEADER_UPDATE = null;//"上次更新 M-d HH:mm";
    public static String REFRESH_HEADER_SECONDARY = null;//"释放进入二楼";
    private TextView mTitleText;

    /**
     * The M animation view.
     */
    LottieAnimationView mAnimationView;

    /**
     * Instantiates a new My refresh lottie header.
     *
     * @param context the context
     */
    public MyRefreshLottieHeader(Context context) {
        super(context);
        if (REFRESH_HEADER_PULLING == null) {
            REFRESH_HEADER_PULLING = context.getString(com.androidapp.base.R.string.srl_header_pulling);
        }
        if (REFRESH_HEADER_REFRESHING == null) {
            REFRESH_HEADER_REFRESHING = context.getString(com.androidapp.base.R.string.srl_header_refreshing);
        }
        if (REFRESH_HEADER_LOADING == null) {
            REFRESH_HEADER_LOADING = context.getString(com.androidapp.base.R.string.srl_header_loading);
        }
        if (REFRESH_HEADER_RELEASE == null) {
            REFRESH_HEADER_RELEASE = context.getString(com.androidapp.base.R.string.srl_header_release);
        }
        if (REFRESH_HEADER_FINISH == null) {
            REFRESH_HEADER_FINISH = context.getString(com.androidapp.base.R.string.srl_header_finish);
        }
        if (REFRESH_HEADER_FAILED == null) {
            REFRESH_HEADER_FAILED = context.getString(com.androidapp.base.R.string.srl_header_failed);
        }
        if (REFRESH_HEADER_UPDATE == null) {
            REFRESH_HEADER_UPDATE = context.getString(com.androidapp.base.R.string.srl_header_update);
        }
        if (REFRESH_HEADER_SECONDARY == null) {
            REFRESH_HEADER_SECONDARY = context.getString(com.androidapp.base.R.string.srl_header_secondary);
        }
        initView(context);
    }

    /**
     * 注意不能为null
     * @return
     */
    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }


    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public void onStartAnimator(RefreshLayout layout, int height, int extendHeight) {
        mAnimationView.playAnimation();
    }

    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
        mAnimationView.cancelAnimation();
        if (success) {
            mTitleText.setText(REFRESH_HEADER_FINISH);
        } else {
            mTitleText.setText(REFRESH_HEADER_FAILED);
        }
        return 0;
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        switch (newState) {
            case None:
            case PullDownToRefresh:
                mTitleText.setText(REFRESH_HEADER_PULLING);
                break;
            case Refreshing:
            case RefreshReleased:
                mTitleText.setText(REFRESH_HEADER_REFRESHING);
                break;
            case ReleaseToRefresh:
                mTitleText.setText(REFRESH_HEADER_RELEASE);
                break;
            case ReleaseToTwoLevel:
                mTitleText.setText(REFRESH_HEADER_SECONDARY);
                break;
            case Loading:
                mTitleText.setText(REFRESH_HEADER_LOADING);
                break;
        }
    }

    private void initView(Context context) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.view_loading_lottie, this);
        mAnimationView = view.findViewById(R.id.loading_lottie);
        mTitleText = view.findViewById(R.id.loading_title);
    }

    /**
     * Set animation view json.
     *
     * @param animName json文件名
     */
    public void setAnimationViewJson(String animName){
        mAnimationView.setAnimation(animName);
    }

    /**
     * Set animation view json.
     *
     * @param anim the anim
     */
    public void setAnimationViewJson(Animation anim){
        mAnimationView.setAnimation(anim);
    }
}
