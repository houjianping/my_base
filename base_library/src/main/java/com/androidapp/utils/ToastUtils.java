package com.androidapp.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.androidapp.base.R;

public class ToastUtils {

    private static Context sContext;
    private Toast mToast;

    public static void setContext(Context context) {
        sContext = context;
    }

    private ToastUtils(String msg) {
        LayoutInflater inflater = (LayoutInflater) sContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.widget_toast, null);
        TextView mTextView = (TextView) view.findViewById(R.id.tv_msg);
        mTextView.setText(msg);
        if (mToast == null) {
            mToast = new Toast(sContext);
        }
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.setView(view);
    }

    private void show() {
        mToast.show();
    }

    private void hide() {
        if (mToast != null) {
            mToast.cancel();
        }
    }

    public static void show(CharSequence message) {
        ToastUtils toastUtils = new ToastUtils(message.toString());
        toastUtils.show();
    }

    public static void show(int strResId) {
        show(sContext.getResources().getText(strResId));
    }
}
