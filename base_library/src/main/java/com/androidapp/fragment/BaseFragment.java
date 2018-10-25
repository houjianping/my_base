package com.androidapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidapp.utils.ToastUtils;

public abstract class BaseFragment extends Fragment {

    public Context mContext;
    private View mViewHolder;

    /**
     * 刷新数据，判断是否是首次
     */
    protected abstract void loadData(boolean force);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mViewHolder == null) {
            mViewHolder = inflater.inflate(getLayoutId(), container, false);
            initView(mViewHolder, savedInstanceState);
        }
        return mViewHolder;
    }

    protected abstract void initView(View view, Bundle savedInstanceState);

    /**
     * 得到视图的ID
     * @return
     */
    protected abstract int getLayoutId();

    protected void showToast(int stringId) {
        ToastUtils.show(stringId);
    }

    protected void showToast(String string) {
        ToastUtils.show(string);
    }

    /**
     * @param cls    目标Activity
     * @param bundle 数据
     */
    protected void doStartActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(getActivity(), cls);
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
        Intent intent = new Intent(getActivity(), cls);
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }
}

