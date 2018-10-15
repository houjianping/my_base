package com.androidapp.mvp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.androidapp.base.R;
import com.androidapp.base.adapter.BaseQuickAdapter;
import com.androidapp.base.utils.StatusBarUtil;
import com.androidapp.base.utils.ToastUtils;
import com.androidapp.smartrefresh.layout.api.RefreshLayout;
import com.androidapp.smartrefresh.layout.listener.OnLoadMoreListener;
import com.androidapp.smartrefresh.layout.listener.OnRefreshListener;
import com.androidapp.widget.CommonTitleBar;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.widget.LinearLayout.VERTICAL;

public abstract class MvpBaseListActivity<M extends MvpBaseModel, P extends MvpBasePresenter> extends RxAppCompatActivity {

    public Context mContext;
    protected CommonTitleBar mTitleBar;
    protected TextView mTvCenterTitle;

    protected P mPresenter;
    protected Unbinder unbinder;

    protected abstract int getLayoutId();

    protected abstract void onInitView(Bundle bundle);

    protected abstract void onEvent();

    protected abstract MvpBaseView getView();

    public RecyclerView mRecyclerView;
    public RefreshLayout mRefreshLayout;

    protected Class getModelClazz() {
        return (Class<M>) MvpContractProxy.getModelClazz(getClass(), 0);
    }

    protected Class getPresenterClazz() {
        return (Class<P>) MvpContractProxy.getPresnterClazz(getClass(), 1);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getLayoutId() == 0 ? R.layout.base_list_activity : getLayoutId());
        unbinder = ButterKnife.bind(this);
        bindMVP();
        mTitleBar = (CommonTitleBar) findViewById(R.id.titlebar);
        if (null != mTitleBar) {
            initTitle();
        }
        initListView();
        onInitView(savedInstanceState);
        onEvent();
        if (isTranslucent()) {
            StatusBarUtil.darkMode(this, getStatusBarColor());
        }
    }

    private void initListView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRefreshLayout = (RefreshLayout) findViewById(R.id.refreshLayout);
        if (isDividerItemDecorationEnable())
            mRecyclerView.addItemDecoration(new DividerItemDecoration(this, VERTICAL));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        if (getListViewAdapter() != null) {
            mRecyclerView.setAdapter(getListViewAdapter());
        }
        if (getOnRefreshListener() != null) {
            mRefreshLayout.setOnRefreshListener(getOnRefreshListener());
        }
        if (getOnLoadMoreListener() != null) {
            mRefreshLayout.setOnLoadMoreListener(getOnLoadMoreListener());
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

    protected int getStatusBarColor() {
        return Color.TRANSPARENT;
    }

    public void showErrorWithStatus(String msg) {
    }

    /**
     * 下拉刷新回调
     *
     * @return
     */
    protected abstract OnRefreshListener getOnRefreshListener();

    /**
     * 加载更多回调
     *
     * @return
     */
    protected abstract OnLoadMoreListener getOnLoadMoreListener();

    /**
     * 数据源
     *
     * @return
     */
    protected abstract BaseQuickAdapter getListViewAdapter();

    /**
     * 添加列表头部视图
     *
     * @param headerView  视图
     * @param headerIndex 顶部层级 0 1 2 3
     */
    protected void addHeaderView(View headerView, int headerIndex) {
        if (getListViewAdapter() != null) {
            getListViewAdapter().addHeaderView(headerView, headerIndex);
        }
    }

    ;

    /**
     * 添加列表底部视图
     *
     * @param footerView 视图
     * @param viewIndex  层级 0 1 2 3
     */
    protected void addFooterView(View footerView, int viewIndex) {
        if (getListViewAdapter() != null) {
            getListViewAdapter().addFooterView(footerView, viewIndex);
        }
    }

    protected boolean isDividerItemDecorationEnable() {
        return true;
    }
}

