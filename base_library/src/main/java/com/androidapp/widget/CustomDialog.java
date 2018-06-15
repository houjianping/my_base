package com.androidapp.widget;

import android.app.Dialog;
import android.content.Context;
import android.view.Window;
import android.view.WindowManager;

import com.androidapp.base.R;

public class CustomDialog extends Dialog {

    public CustomDialog(Context context, boolean cancelable,
                        OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    public CustomDialog(Context context, int theme) {
        super(context, R.style.CustomDialog);
    }

    public CustomDialog(Context context) {
        super(context, R.style.CustomDialog);
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
}