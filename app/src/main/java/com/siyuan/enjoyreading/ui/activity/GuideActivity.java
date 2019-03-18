package com.siyuan.enjoyreading.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.androidapp.activity.BaseActivity;
import com.androidapp.banner.transformer.DepthPageTransformer;
import com.androidapp.utils.SPUtils;
import com.siyuan.enjoyreading.R;

import java.util.ArrayList;

public class GuideActivity extends BaseActivity {

    private int[] mImageIds = new int[]{R.drawable.icon_guide_0, R.drawable.icon_guide_1, R.drawable.icon_guide_2};
    private int mPaintDis;

    private ArrayList<ImageView> mImageViewList;

    private ViewPager mViewPager;
    private LinearLayout mContainerView;
    private ImageView mRedPointImageView;
    private Button mStartButton;

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.activity_guide);
    }

    @Override
    protected void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp_guide);
        mContainerView = (LinearLayout) findViewById(R.id.ll_container);
        mRedPointImageView = (ImageView) findViewById(R.id.iv_red);
        mStartButton = (Button) findViewById(R.id.start_btn);

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtils.setSharedBooleanData("appUsed", true);
                Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        initUI();
        GuideAdapter adapter = new GuideAdapter();
        mViewPager.setPageTransformer(true, new DepthPageTransformer());
        mViewPager.setAdapter(adapter);

        mRedPointImageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                mRedPointImageView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                mPaintDis = mContainerView.getChildAt(1).getLeft() - mContainerView.getChildAt(0).getLeft();
            }
        });

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mRedPointImageView.getLayoutParams();
        params.leftMargin = 45;
        mRedPointImageView.setLayoutParams(params);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                int leftMargin = (int) (mPaintDis * positionOffset) + position * mPaintDis;
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mRedPointImageView.getLayoutParams();
                params.leftMargin = leftMargin + 45;
                mRedPointImageView.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                if (position == mImageViewList.size() - 1) {
                    mStartButton.setVisibility(View.VISIBLE);
                } else {
                    mStartButton.setVisibility(View.GONE);
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    protected void initData() {
    }

    class GuideAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return mImageViewList.size();
        }
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView view = mImageViewList.get(position);
            container.addView(view);
            return view;
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    private void initUI() {
        mImageViewList = new ArrayList<>();
        for (int i = 0; i < mImageIds.length; i++) {
            ImageView view = new ImageView(this);
            view.setBackgroundResource(mImageIds[i]);
            mImageViewList.add(view);
            ImageView pointView = new ImageView(this);
            pointView.setImageResource(R.drawable.shape_point1);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (i > 0) {
                params.leftMargin = 30;
            }
            pointView.setLayoutParams(params);
            mContainerView.addView(pointView);
        }
    }
}
