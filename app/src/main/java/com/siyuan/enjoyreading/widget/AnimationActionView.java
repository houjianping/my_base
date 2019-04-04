package com.siyuan.enjoyreading.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.androidapp.utils.ScreenUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siyuan.enjoyreading.App;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.api.ApiConfig;
import com.siyuan.enjoyreading.entity.Movie;
import com.siyuan.enjoyreading.entity.ShowSendItemInfo;

import java.util.ArrayList;
import java.util.List;

public class AnimationActionView extends LinearLayout {

    public AnimationActionView buttonView;
    private View closeView, rlSendNeed, rlSendServie;
    private ShowSendItemView itemOne, itemTwo, itemThree, itemFour;

    private int animationDuration = 300;
    private int height = ScreenUtil.dip2px(App.getInstance(),350);

    private TranslateAnimation showAnimation1 = getTranslateAnimation(height, 0, animationDuration + 100, true);
    private TranslateAnimation showAnimation2 = getTranslateAnimation(height, 0, animationDuration + 100, true);
    private TranslateAnimation hideAnimation1 = getTranslateAnimation(0, height, animationDuration - 50, true);
    private TranslateAnimation hideAnimation2 = getTranslateAnimation(0, height, animationDuration - 50, true);
    private RotateAnimation hideAnimation3 = getRotateAnimation(180, 0);
    private RotateAnimation showAnimation5 = getRotateAnimation(0, 180);

    public AnimationActionView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnimationActionView(Context context) {
        this(context, null, 0);
    }

    public AnimationActionView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        buttonView = this;
        initView();
    }

    private void initView() {
        View mView;
        mView = LayoutInflater.from(getContext()).inflate(R.layout.show_send_view_male, this);
        closeView = mView.findViewById(R.id.send_type_msg_bar);
        itemOne = (ShowSendItemView) mView.findViewById(R.id.itemOne);
        itemTwo = (ShowSendItemView) mView.findViewById(R.id.itemTwo);
        itemThree = (ShowSendItemView) mView.findViewById(R.id.itemThree);
        itemFour = (ShowSendItemView) mView.findViewById(R.id.itemFour);
        rlSendNeed = mView.findViewById(R.id.ll_left);
        rlSendServie = mView.findViewById(R.id.ll_right);
        setOnClickListener(onClickListener);
        Interpolator interpolator = new OvershootInterpolator();
        showAnimation1.setInterpolator(interpolator);
        showAnimation2.setInterpolator(interpolator);
        closeView.setOnClickListener(onClickListener);
    }


    private void refData() {
        List<ShowSendItemInfo> showSendItemInfos = new Gson().fromJson(ApiConfig.GET_ANIM_ACTION_DATA, new TypeToken<ArrayList<ShowSendItemInfo>>() {
        }.getType());
        try {
            itemOne.setData(this, showSendItemInfos.get(0));
            itemTwo.setData(this, showSendItemInfos.get(1));
            itemThree.setData(this, showSendItemInfos.get(2));
            itemFour.setData(this, showSendItemInfos.get(3));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private OnClickListener onClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.send_type_msg_bar:
                    hide();
                    break;
            }
        }
    };

    public void show() {
        refData();
        setVisibility(VISIBLE);
        handler.sendEmptyMessageDelayed(1, 0);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 90 + 45);
        rotateAnimation.setDuration(animationDuration);
        rotateAnimation.setFillAfter(true);
    }

    public void hide() {
        handler.sendEmptyMessageDelayed(3, 100);
        handler.sendEmptyMessageDelayed(5, (int) (animationDuration));
    }

    private Handler handler = new Handler() {
        @SuppressLint("ResourceAsColor")
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    rlSendNeed.setVisibility(View.VISIBLE);
                    rlSendNeed.startAnimation(showAnimation1);
                    closeView.startAnimation(showAnimation5);
                    sendEmptyMessageDelayed(2, 100);
                    break;
                case 2:
                    rlSendServie.setVisibility(View.VISIBLE);
                    rlSendServie.startAnimation(showAnimation2);
                    break;
                case 3:
                    rlSendNeed.startAnimation(hideAnimation1);
                    closeView.startAnimation(hideAnimation3);
                    sendEmptyMessageDelayed(4, 100);
                    break;
                case 4:
                    rlSendServie.startAnimation(hideAnimation2);
                    break;
                case 5:
                    setVisibility(View.GONE);
                    buttonView = null;
                    break;
            }
        }

        ;
    };

    private RotateAnimation getRotateAnimation(int fromDegrees, int toDegrees) {
        RotateAnimation rotateAnimation = new RotateAnimation(fromDegrees, toDegrees, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(animationDuration);
        rotateAnimation.setFillAfter(true);
        return rotateAnimation;
    }

    private TranslateAnimation getTranslateAnimation(int y1, int y2, int duration, boolean fillBeFore) {
        TranslateAnimation animation = new TranslateAnimation(0, 0, y1, y2);
        animation.setDuration(duration);
        animation.setFillAfter(fillBeFore);
        return animation;
    }

    public class OvershootInterpolator implements Interpolator {

        private final float mTension;

        private OvershootInterpolator() {
            mTension = 1.8f;
        }

        public OvershootInterpolator(float tension) {
            mTension = tension;
        }

        @Override
        public float getInterpolation(float t) {
            t -= 1.1f;
            return (t * t * (((mTension + 1) * t) + mTension)) + 1.0f;
        }
    }
}
