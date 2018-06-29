package com.scwang.refreshlayout.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;

import com.androidapp.base.activity.BaseActivity;
import com.androidapp.base.utils.StatusBarUtil;
import com.androidapp.share.bean.ShareContent;
import com.androidapp.share.bean.ShareEnum;
import com.androidapp.share.util.ShareUtil;
import com.androidapp.tablayout.CommonTabLayout;
import com.androidapp.tablayout.listener.CustomTabEntity;
import com.androidapp.tablayout.listener.OnTabSelectListener;
import com.scwang.refreshlayout.R;
import com.scwang.refreshlayout.ui.fragment.IndexMainFragment;
import com.scwang.refreshlayout.ui.fragment.OrderMainFragment;
import com.scwang.refreshlayout.ui.fragment.PersonalMainFragment;
import com.scwang.refreshlayout.ui.fragment.RoomMainFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private String[] mTitles = {"首页", "订单管理", "", "大厅", "个人中心"};
    private int[] mIconUnselectIds = {
            R.mipmap.ic_home_normal, R.mipmap.ic_video_normal, R.mipmap.ic_center, R.mipmap.ic_video_normal, R.mipmap.ic_care_normal};
    private int[] mIconSelectIds = {
            R.mipmap.ic_home_selected, R.mipmap.ic_video_selected, R.mipmap.ic_center, R.mipmap.ic_video_selected, R.mipmap.ic_care_selected};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private CommonTabLayout tabLayout;
    private IndexMainFragment mainFragment;
    private OrderMainFragment mOrderMainFragment;
    private PersonalMainFragment videoMainFragment;
    private RoomMainFragment roomMainFragment;
    private int currentTabPosition = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("currentTabPosition", currentTabPosition);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        int current = savedInstanceState.getInt("currentTabPosition");
        if (current != currentTabPosition) {
            switchTo(current);
        }
    }

    @Override
    protected void initContentView(Bundle bundle) {
        setContentView(R.layout.act_main);
        tabLayout = (CommonTabLayout) findViewById(R.id.tab_layout);
        initTab();
        initFragment(bundle);
        //状态栏透明和间距处理
        StatusBarUtil.darkMode(this);
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {
        tabLayout.showMsg(0, 20);
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
    }

    /**
     * 初始化tab
     */
    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i], !TextUtils.isEmpty(mTitles[i])));
        }
        tabLayout.setTabData(mTabEntities);
        //点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                if (position == 2) {
                    ShareUtil shareUtil = new ShareUtil(MainActivity.this,"分享标题", R.mipmap.ic_launcher);
                    shareUtil.setShareCallback(new ShareUtil.ShareCallback() {
                        @Override
                        public void onShareStart(ShareEnum shareEnum) {
                        }
                        @Override
                        public void onShareSuccess(ShareEnum shareEnum) {
                        }
                        @Override
                        public void onShareFailed(ShareEnum shareEnum) {
                        }
                        @Override
                        public void onShareCancel(ShareEnum shareEnum) {
                        }
                    });
                    ShareContent shareContent = new ShareContent();
                    shareContent.setUrl("");
                    shareContent.setTitle("");
                    shareContent.setLogo("");
                    shareContent.setText("");
                    shareContent.setShareObject(1);
                    shareUtil.show(shareContent);
                } else {
                    switchTo(position);
                }
            }

            @Override
            public void onTabReselect(int position) {
            }
        });
    }

    /**
     * 初始化碎片
     */
    private void initFragment(Bundle savedInstanceState) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (savedInstanceState != null) {
            mainFragment = (IndexMainFragment) getSupportFragmentManager().findFragmentByTag("IndexMainFragment");
            mOrderMainFragment = (OrderMainFragment) getSupportFragmentManager().findFragmentByTag("OrderFragment");
            videoMainFragment = (PersonalMainFragment) getSupportFragmentManager().findFragmentByTag("videoMainFragment");
            roomMainFragment = (RoomMainFragment) getSupportFragmentManager().findFragmentByTag("roomMainFragment");
        } else {
            mainFragment = new IndexMainFragment();
            mOrderMainFragment = new OrderMainFragment();
            videoMainFragment = new PersonalMainFragment();
            roomMainFragment = new RoomMainFragment();
            transaction.add(R.id.fl_body, mainFragment, "IndexMainFragment");
            transaction.add(R.id.fl_body, mOrderMainFragment, "OrderFragment");
            transaction.add(R.id.fl_body, videoMainFragment, "videoMainFragment");
            transaction.add(R.id.fl_body, roomMainFragment, "roomMainFragment");
        }
        transaction.commit();
        switchTo(currentTabPosition);
        tabLayout.setCurrentTab(currentTabPosition);
    }

    /**
     * 切换
     */
    private void switchTo(int position) {
        currentTabPosition = position;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                transaction.hide(mainFragment);
                transaction.hide(mOrderMainFragment);
                transaction.hide(videoMainFragment);
                transaction.hide(roomMainFragment);
                transaction.show(mainFragment);
                transaction.commitNowAllowingStateLoss();
                break;
            case 1:
                transaction.hide(mainFragment);
                transaction.hide(videoMainFragment);
                transaction.hide(roomMainFragment);
                transaction.show(mOrderMainFragment);
                transaction.commitNowAllowingStateLoss();
                break;
            case 3:
                transaction.hide(mainFragment);
                transaction.hide(mOrderMainFragment);
                transaction.hide(videoMainFragment);
                transaction.show(roomMainFragment);
                transaction.commitAllowingStateLoss();
                break;
            case 4:
                transaction.hide(mainFragment);
                transaction.hide(mOrderMainFragment);
                transaction.hide(roomMainFragment);
                transaction.show(videoMainFragment);
                transaction.commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }

    public class TabEntity implements CustomTabEntity {
        private String title;
        private int selectedIcon;
        private int unSelectedIcon;
        private boolean isSelectAble;

        TabEntity(String title, int selectedIcon, int unSelectedIcon, boolean isSelectAble) {
            this.title = title;
            this.selectedIcon = selectedIcon;
            this.unSelectedIcon = unSelectedIcon;
            this.isSelectAble = isSelectAble;
        }

        @Override
        public String getTabTitle() {
            return title;
        }

        @Override
        public int getTabSelectedIcon() {
            return selectedIcon;
        }

        @Override
        public int getTabUnselectedIcon() {
            return unSelectedIcon;
        }

        @Override
        public boolean selectAble() {
            return isSelectAble;
        }

        @Override
        public int getIconSize() {
            return isSelectAble ? 0 : 120;
        }
    }

    @Override
    protected boolean isTranslucent() {
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
