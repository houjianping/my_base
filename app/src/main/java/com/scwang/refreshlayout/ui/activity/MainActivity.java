package com.scwang.refreshlayout.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.widget.Toast;

import com.androidapp.activity.BaseActivity;
import com.androidapp.utils.StatusBarUtil;
import com.androidapp.share.bean.ShareContent;
import com.androidapp.share.bean.ShareEnum;
import com.androidapp.share.util.ShareUtil;
import com.androidapp.tablayout.CommonTabLayout;
import com.androidapp.tablayout.listener.CustomTabEntity;
import com.androidapp.tablayout.listener.OnTabSelectListener;
import com.androidapp.utils.ToastUtils;
import com.scwang.refreshlayout.R;
import com.scwang.refreshlayout.ui.fragment.IndexMainFragment;
import com.scwang.refreshlayout.ui.fragment.OrderMainFragment;
import com.scwang.refreshlayout.ui.fragment.PCenterFragment;
import com.scwang.refreshlayout.ui.fragment.RoomMainFragment;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private String[] mTitles = {"首页", "专栏", "发现", "个人中心"};

    private int[] mIconUnselectIds = {
            R.mipmap.ic_home_normal, R.mipmap.ic_column_normal, R.mipmap.ic_discover_normal, R.mipmap.ic_pcenter_normal};

    private int[] mIconSelectIds = {
            R.mipmap.ic_home_selected, R.mipmap.ic_column_selected, R.mipmap.ic_discover_selected, R.mipmap.ic_pcenter_selected};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private CommonTabLayout tabLayout;
    private IndexMainFragment mainFragment;
    private OrderMainFragment mOrderMainFragment;//专栏
    private RoomMainFragment roomMainFragment;
    private PCenterFragment videoMainFragment;
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
        tabLayout = findViewById(R.id.tab_layout);
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
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i], !TextUtils.isEmpty(mTitles[i]), null, null));
        }
        tabLayout.setTabData(mTabEntities);
        //点击监听
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switchTo(position);
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
            videoMainFragment = (PCenterFragment) getSupportFragmentManager().findFragmentByTag("videoMainFragment");
            roomMainFragment = (RoomMainFragment) getSupportFragmentManager().findFragmentByTag("roomMainFragment");
        } else {
            mainFragment = new IndexMainFragment();
            mOrderMainFragment = new OrderMainFragment();
            videoMainFragment = new PCenterFragment();
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
        try {
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
                case 2:
                    transaction.hide(mainFragment);
                    transaction.hide(mOrderMainFragment);
                    transaction.hide(videoMainFragment);
                    transaction.show(roomMainFragment);
                    transaction.commitAllowingStateLoss();
                    break;
                case 3:
                    transaction.hide(mainFragment);
                    transaction.hide(mOrderMainFragment);
                    transaction.hide(roomMainFragment);
                    transaction.show(videoMainFragment);
                    transaction.commitAllowingStateLoss();
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public class TabEntity implements CustomTabEntity {
        private String title;
        private int selectedIcon;
        private int unSelectedIcon;
        private boolean isSelectAble;
        private String selectedWebIcon;
        private String unSelectedWebIcon;

        TabEntity(String title, int selectedIcon, int unSelectedIcon, boolean isSelectAble, String selectedWebIcon, String unSelectedWebIcon) {
            this.title = title;
            this.selectedIcon = selectedIcon;
            this.unSelectedIcon = unSelectedIcon;
            this.isSelectAble = isSelectAble;
            this.selectedIcon = selectedIcon;
            this.unSelectedWebIcon = unSelectedWebIcon;
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

        @Override
        public String getTabWebSelectedIcon() {
            return selectedWebIcon;
        }

        @Override
        public String getTabWebUnSelectedIcon() {
            return unSelectedWebIcon;
        }
    }

    private long firstTime = 0;
    @Override
    public void onBackPressed() {
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            ToastUtils.show("再按一次退出程序");
            firstTime = secondTime;
        } else{
            finish();
        }
    }
}
