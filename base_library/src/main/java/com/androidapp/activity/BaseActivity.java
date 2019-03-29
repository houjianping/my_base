package com.androidapp.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.androidapp.base.R;
import com.androidapp.utils.StatusBarUtil;
import com.androidapp.utils.ToastUtils;
import com.androidapp.widget.CommonTitleBar;
import com.androidapp.widget.LoadingDialog;

public abstract class BaseActivity extends AppCompatActivity {

    private static final String TAG = "BaseActivity";
    public boolean isInForeground;

    protected BaseActivity mContext;
    protected CommonTitleBar mTitleBar;
    protected TextView mTvCenterTitle;
    private LoadingDialog mLoadingDialog;
    private long mLastTouchUITime;

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
        if (isTranslucent()) {
            StatusBarUtil.darkMode(this, getStatusBarColor());
        }
    }

    protected abstract void initContentView(Bundle bundle);

    protected abstract void initView();

    protected abstract void initData();

    /**
     * Bundle  传递数据
     *
     * @param extras
     */
    protected void getBundleExtras(Bundle extras) {
    }

    ;

    protected void initTitle() {
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

    protected int getStatusBarColor() {
        return Color.TRANSPARENT;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    protected void showToast(int stringId) {
        ToastUtils.show(stringId);
    }

    protected void showToast(String string) {
        ToastUtils.show(string);
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

    protected void doStartActivity(Class<?> cls) {
        doStartActivity(cls, null);
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

    public void startActivity(Class c) {
        try {
            Intent intent = new Intent(mContext, c);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showLoading(boolean cancelable) {
        LoadingDialog.Builder loadBuilder = new LoadingDialog.Builder(mContext)
                .setMessage("加载中...")
                .setCancelable(cancelable)
                .setCancelOutside(false);
        mLoadingDialog = loadBuilder.create();
        mLoadingDialog.show();
        ;
    }

    public void hideLoading() {
        try {
            if (mLoadingDialog != null && mLoadingDialog.isShowing() && !isFinishing()) {
                mLoadingDialog.cancel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            if (isFastDoubleClick()) {
                return true;
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    public boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - mLastTouchUITime;
        mLastTouchUITime = time;
        return timeD <= 300;
    }
}
