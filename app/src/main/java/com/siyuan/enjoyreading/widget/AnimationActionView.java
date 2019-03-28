package com.siyuan.enjoyreading.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

import com.androidapp.utils.JsonUtils;
import com.androidapp.utils.ScreenUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.siyuan.enjoyreading.App;
import com.siyuan.enjoyreading.R;
import com.siyuan.enjoyreading.api.ApiConfig;
import com.siyuan.enjoyreading.entity.ShowSendItemInfo;
import com.siyuan.enjoyreading.entity.SmallCategoryItem;

import java.util.ArrayList;

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
        setBackgroundResource(R.color.white);
        getBackground().setAlpha(255);
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
        ShowSendItemInfo showSendItemInfo1 = new Gson().fromJson("{\"icon\":\"https://i.3km.biz/static/plus/one@3x.png\",\"title\":\"快捷充值\",\"sub_title\":\"充值越多奖励越大\",\"des\":\"最高得10000奖励~\",\"guide_icon\":\"\",\"show_type\":\"0\",\"app_jump\":\"{\\\"type\\\":30}\",\"show_num\":\"-1\",\"title_color\":\"#666666\"}", new TypeToken<ShowSendItemInfo>() {}.getType());
        ShowSendItemInfo showSendItemInfo2 = new Gson().fromJson("{\"icon\":\"https://i.3km.biz/static/plus/two@3x.png\",\"title\":\"发动态\",\"sub_title\":\"把自己show出来\",\"des\":\"20元红包\",\"guide_icon\":\"\",\"show_type\":\"0\",\"app_jump\":\"{\\\"type\\\":52}\",\"show_num\":\"-1\",\"title_color\":\"#666666\"}", new TypeToken<ShowSendItemInfo>() {}.getType());
        ShowSendItemInfo showSendItemInfo3 = new Gson().fromJson("{\"icon\":\"https://i.3km.biz/static/plus/three@3x.png\",\"title\":\"约玩\",\"sub_title\":\"下个单 约人玩\",\"des\":\"首单免费\",\"guide_icon\":\"\",\"show_type\":\"0\",\"app_jump\":\"{\\\"type\\\":18}\",\"show_num\":\"-1\",\"title_color\":\"#666666\"}", new TypeToken<ShowSendItemInfo>() {}.getType());
        ShowSendItemInfo showSendItemInfo4 = new Gson().fromJson("{\"icon\":\"https://i.3km.biz/static/plus/four@3x.png\",\"title\":\"陪玩\",\"sub_title\":\"陪人玩 赚钱花\",\"des\":\"\",\"guide_icon\":\"\",\"show_type\":\"0\",\"app_jump\":\"{\\\"type\\\":17}\",\"show_num\":\"-1\",\"title_color\":\"#666666\"}", new TypeToken<ShowSendItemInfo>() {}.getType());
        try {
            itemOne.setData(this, showSendItemInfo1);
            itemTwo.setData(this, showSendItemInfo2);
            itemThree.setData(this, showSendItemInfo3);
            itemFour.setData(this, showSendItemInfo4);
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

    private void skipService() {
        hide();
    }

    private void skipNeed() {
        hide();
    }

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

    private AlphaAnimation getAlphaAnimation(float fromAlpha, float toAlpha, int animationDuration) {
        AlphaAnimation anim = new AlphaAnimation(fromAlpha, toAlpha); // alpha的渐变动画
        anim.setDuration(animationDuration);
        return anim;
    }

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
