package com.androidapp.base.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.androidapp.base.R;
import com.androidapp.base.utils.StatusBarUtil;
import com.androidapp.base.utils.ToastUtils;
import com.androidapp.widget.CommonTitleBar;

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";

    protected BaseActivity mContext;

    protected CommonTitleBar mTitleBar;
    protected TextView mTvCenterTitle;
    public boolean isInForeground;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (null != extras) {
            getBundleExtras(extras);
        }
        mContext = this;
        initContentView(savedInstanceState);
        mTitleBar = (CommonTitleBar) findViewById(R.id.titlebar);
        if (null != mTitleBar) {
            initTitle();
        }
        initView();
        initData();
        StatusBarUtil.darkMode(this);
    }

    protected abstract void initContentView(Bundle bundle);

    protected abstract void initView();

    protected abstract void initData();

    /**
     * Bundle  传递数据
     *
     * @param extras
     */
    protected abstract void getBundleExtras(Bundle extras);

    protected void initTitle() {
        mTitleBar.setBackgroundColor(Color.WHITE);
        mTvCenterTitle = mTitleBar.getCenterTextView();
        mTitleBar.setListener(new CommonTitleBar.OnTitleBarListener() {
            @Override
            public void onClicked(View v, int action, String extra) {
                if (action == CommonTitleBar.ACTION_LEFT_BUTTON
                        || action == CommonTitleBar.ACTION_LEFT_TEXT) {
                    onBackPressed();
                }
            }
        });
    }

    public void setCenterTitle(String string) {
        mTvCenterTitle.setText(string);
    }

    public void setCenterTitle(int id) {
        mTvCenterTitle.setText(id);
    }

    protected boolean isTranslucent() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    protected void showToast(int stringId) {
        ToastUtils.showShortToast(this, stringId);
    }

    protected void showToast(String string) {
        ToastUtils.showShortToast(this, string);
    }

    /**
     * 跳转界面并关闭当前界面
     *
     * @param cls 目标Activity
     */
    protected void doStartActivityThenKill(Class<?> cls) {
        doStartActivityThenKill(cls, null);
    }

    /**
     * @param cls    目标Activity
     * @param bundle 数据
     */
    protected void doStartActivityThenKill(Class<?> cls, Bundle bundle) {
        if (!isFinishing()) {
            doStartActivity(cls, bundle);
            finish();
        }
    }

    /**
     * @param cls    目标Activity
     * @param bundle 数据
     */
    protected void doStartActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * @param cls         目标Activity
     * @param requestCode 发送判断值
     * @param bundle      数据
     */
    protected void doStartActivityForResult(Class<?> cls, int requestCode, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onPause() {
        super.onPause();
        isInForeground = false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isInForeground = true;
    }
}