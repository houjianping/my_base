package com.androidapp.activity;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidapp.base.R;
import com.androidapp.permission.PermissionEnum;
import com.androidapp.permission.PermissionUtils;
import com.androidapp.utils.StatusBarUtil;
import com.androidapp.utils.ToastUtils;
import com.androidapp.widget.CommonTitleBar;
import com.androidapp.widget.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseActivity extends AppCompatActivity {

    final int REQUEST_CODE_PERMISSIONS = 110;

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
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            ToastUtils.show("页面不存在!");
        }
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
        try {
            startActivityForResult(intent, requestCode);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
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

    //----------------------------------以下模块是处理权限校验的------------------------------------
    public void doRequestPermission() {
        requestMorePermissions();
    }

    // 自定义申请多个权限
    private void requestMorePermissions() {
        PermissionUtils.checkMorePermissions(mContext, getPermissionEnums(), new PermissionUtils.PermissionCheckCallBack() {
            @Override
            public void onHasPermission() {
                if (getPermissionCallBack() != null) {
                    getPermissionCallBack().onHasPermission();
                }
            }

            @Override
            public void onUserHasAlreadyTurnedDown(List<PermissionEnum> permission) {
                showExplainDialog(permission, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PermissionUtils.requestMorePermissions(mContext, getPermissionEnums(), REQUEST_CODE_PERMISSIONS);
                    }
                });
            }

            @Override
            public void onUserHasAlreadyTurnedDownAndDontAsk(List<PermissionEnum> permission) {
                PermissionUtils.requestMorePermissions(mContext, getPermissionEnums(), REQUEST_CODE_PERMISSIONS);
            }
        });
    }

    /**
     * 解释权限的dialog
     */
    private void showExplainDialog(List<PermissionEnum> permission, DialogInterface.OnClickListener onClickListener) {
        new AlertDialog.Builder(mContext)
                .setTitle("申请权限")
                .setMessage("我们需要" + PermissionUtils.convertDesc(permission) + "权限")
                .setPositiveButton("确定", onClickListener)
                .show();
    }

    /**
     * 显示前往应用设置Dialog
     */
    private void showToAppSettingDialog() {
        new AlertDialog.Builder(mContext)
                .setTitle("需要权限")
                .setMessage("我们需要相关权限，才能实现功能，点击前往，将转到应用的设置界面，请开启应用的相关权限。")
                .setPositiveButton("前往", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PermissionUtils.toAppSetting(mContext);
                    }
                })
                .setNegativeButton("取消", null).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSIONS:
                PermissionUtils.onRequestMorePermissionsResult(mContext, getPermissionEnums(), new PermissionUtils.PermissionCheckCallBack() {
                    @Override
                    public void onHasPermission() {
                        if (getPermissionCallBack() != null) {
                            getPermissionCallBack().onHasPermission();
                        }
                    }

                    @Override
                    public void onUserHasAlreadyTurnedDown(List<PermissionEnum> permission) {
                        Toast.makeText(mContext, "我们需要[" + PermissionUtils.convertDesc(permission) + "]权限, 才能保证程序正常运行", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onUserHasAlreadyTurnedDownAndDontAsk(List<PermissionEnum> permission) {
                        Toast.makeText(mContext, "我们需要[" + PermissionUtils.convertDesc(permission) + "]权限, 才能保证程序正常运行", Toast.LENGTH_SHORT).show();
                        showToAppSettingDialog();
                    }
                });
        }
    }

    protected List<PermissionEnum> getPermissionEnums() {
        return new ArrayList<>();
    }

    protected PermissionUtils.PermissionRequestSuccessCallBack getPermissionCallBack() {
        return null;
    }
}
