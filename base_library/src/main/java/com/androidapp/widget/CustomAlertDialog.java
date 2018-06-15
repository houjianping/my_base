package com.androidapp.widget;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.androidapp.base.R;

public class CustomAlertDialog extends Dialog {

    private Context mContext;
    private View mContentView;

    public CustomAlertDialog(Context context, boolean custom) {
        super(context, R.style.CustomDialog);
        if (!custom) {
            mContext = context;
            mContentView = LayoutInflater.from(context).inflate(R.layout.widget_alert_dialog, null);
            addContentView(mContentView
                    , new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
        transparent();
    }

    public CustomAlertDialog(Context context, boolean custom, boolean showCloseBtn) {
        super(context, R.style.CustomDialog);
        if (!custom) {
            mContext = context;
            mContentView = LayoutInflater.from(context).inflate(R.layout.widget_alert_dialog, null);
            addContentView(mContentView
                    , new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            setCloseButtonVisible(showCloseBtn);
        }
        transparent();
    }

    @Override
    public void show() {
        super.show();
        Window win = this.getWindow();
        win.getDecorView().setPadding(0, 0, 0, 0);
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        win.setAttributes(lp);
    }

    public CustomAlertDialog setPositiveButton(String stringId, View.OnClickListener positiveListener) {
        Button btOK = (Button) mContentView.findViewById(R.id.bt_ok);
        Button btCancel = (Button) mContentView.findViewById(R.id.bt_cancel);
        btOK.setText(stringId);
        btOK.setVisibility(TextUtils.isEmpty(stringId) ? View.GONE : View.VISIBLE);
        btOK.setOnClickListener(positiveListener);
        if (View.VISIBLE == btOK.getVisibility() && View.VISIBLE == btCancel.getVisibility()) {
            mContentView.findViewById(R.id.ll_lable).setVisibility(View.VISIBLE);
        } else {
            mContentView.findViewById(R.id.ll_lable).setVisibility(View.GONE);
        }
        return this;
    }

    public CustomAlertDialog setNegativeButton(String stringId, View.OnClickListener negativeListener) {
        Button btOK = (Button) mContentView.findViewById(R.id.bt_ok);
        Button btCancel = (Button) mContentView.findViewById(R.id.bt_cancel);
        btCancel.setText(stringId);
        btCancel.setVisibility(TextUtils.isEmpty(stringId) ? View.GONE : View.VISIBLE);
        btCancel.setOnClickListener(negativeListener);
        if (View.VISIBLE == btOK.getVisibility() && View.VISIBLE == btCancel.getVisibility()) {
            mContentView.findViewById(R.id.ll_lable).setVisibility(View.VISIBLE);
            btCancel.setBackgroundResource(R.drawable.button_selector_left);
            btOK.setBackgroundResource(R.drawable.button_selector_right);
        } else {
            mContentView.findViewById(R.id.ll_lable).setVisibility(View.GONE);
        }
        return this;
    }

    public void setTitle(String titleMsg) {
        TextView titleTextView = (TextView) mContentView.findViewById(R.id.title);
        titleTextView.setVisibility(View.VISIBLE);
        if(!TextUtils.isEmpty(titleMsg)) {
            titleTextView.setText(titleMsg);
            titleTextView.setVisibility(View.VISIBLE);
        }
    }

    public void setMessage(String msg, int textColor) {
        TextView messageTextView = (TextView) mContentView.findViewById(R.id.message);
        if (!TextUtils.isEmpty(msg))
            messageTextView.setText(msg);
        if (textColor > 0)
            messageTextView.setTextColor(textColor);
    }

    public void setOKText(String text, int textColor, int bgResId) {
        Button btOK = (Button) mContentView.findViewById(R.id.bt_ok);
        btOK.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(text)) {
            btOK.setText(text);
        }
        if (bgResId > 0) {
            btOK.setBackgroundResource(bgResId);
        }
        if (textColor > 0) {
            btOK.setTextColor(textColor);
        }
    }

    public void setCancelText(String text, int textColor,int bgResId) {
        Button btCancel = (Button) mContentView.findViewById(R.id.bt_cancel);
        btCancel.setVisibility(View.VISIBLE);
        if (!TextUtils.isEmpty(text)) {
            btCancel.setText(text);
        }
        if (bgResId > 0) {
            btCancel.setBackgroundResource(bgResId);
        }
        if (textColor > 0) {
            btCancel.setTextColor(textColor);
        }
    }

    public void setCloseButtonVisible(boolean showCloseBtn) {
        if (mContentView != null && mContentView.findViewById(R.id.tv_close) != null) {
            mContentView.findViewById(R.id.tv_close).setVisibility(showCloseBtn ? View.VISIBLE : View.GONE);
            mContentView.findViewById(R.id.tv_close).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
    }

    private void transparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}

