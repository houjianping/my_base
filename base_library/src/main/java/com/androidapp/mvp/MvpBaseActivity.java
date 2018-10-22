package com.androidapp.mvp;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.androidapp.base.R;
import com.androidapp.utils.StatusBarUtil;
import com.androidapp.utils.ToastUtils;
import com.androidapp.widget.CommonTitleBar;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class MvpBaseActivity<M extends MvpBaseModel, P extends MvpBasePresenter> extends RxAppCompatActivity {

    protected CommonTitleBar mTitleBar;
    protected TextView mTvCenterTitle;

    protected P mPresenter;
    protected Unbinder unbinder;

    protected abstract int getLayoutId();

    protected abstract void onInitView(Bundle bundle);

    protected abstract void onEvent();

    protected abstract MvpBaseView getView();

    protected Class getModelClazz() {
        return (Class<M>) MvpContractProxy.getModelClazz(getClass(), 0);
    }

    protected Class getPresenterClazz() {
        return (Class<P>) MvpContractProxy.getPresnterClazz(getClass(), 1);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
//            设置布局资源文件
            setContentView(getLayoutId());
//            注解绑定
            unbinder = ButterKnife.bind(this);
            bindMVP();
            mTitleBar = (CommonTitleBar) findViewById(R.id.titlebar);
            if (null != mTitleBar) {
                initTitle();
            }
            onInitView(savedInstanceState);
            onEvent();
        }
        if (isTranslucent()) {
            StatusBarUtil.darkMode(this, getStatusBarColor());
        }
    }

    /**
     * 获取presenter 实例
     */
    private void bindMVP() {
        if (getPresenterClazz() != null) {
            mPresenter = getPresenterImpl();
            mPresenter.mContext = this;
            bindVM();
        }
    }

    private <T> T getPresenterImpl() {
        return MvpContractProxy.getInstance().presenter(getPresenterClazz());
    }

    @Override
    protected void onStart() {
        if (mPresenter == null) {
            bindMVP();
        }
        super.onStart();
    }

    private void bindVM() {
        if (mPresenter != null && !mPresenter.isViewBind() && getModelClazz() != null && getView() != null) {
            MvpContractProxy.getInstance().bindModel(getModelClazz(), mPresenter);
            MvpContractProxy.getInstance().bindView(getView(), mPresenter);
            mPresenter.mContext = this;
        }
    }

    /**
     * activity摧毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (mPresenter != null) {
            MvpContractProxy.getInstance().unbindView(getView(), mPresenter);
            MvpContractProxy.getInstance().unbindModel(getModelClazz(), mPresenter);
            mPresenter = null;
        }
    }

    public void startActivity(Class c) {
        try {
            Intent intent = new Intent(this, c);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    protected void initTitle() {
        //mTitleBar.setBackgroundColor(Color.WHITE);
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

    public void showErrorWithStatus(String msg) {
    }
}

