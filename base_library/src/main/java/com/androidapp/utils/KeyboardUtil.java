package com.androidapp.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

/**
 * @author: 范建海
 * @createTime: 2016/10/30 13:42
 * @className: KeyboardUtil
 * @description: 输入法操作工具类
 * @changed by:
 */
public class KeyboardUtil {
    /**
     * 隐藏键盘
     *
     * @param context 上下文
     * @param view    The currently focused view
     */
    public static void hideInputMethod(Context context, View view) {
        if (context == null || view == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 显示输入键盘
     *
     * @param context 上下文
     * @param view    The currently focused view, which would like to receive soft keyboard input
     */
    public static void showInputMethod(Context context, View view) {
        if (context == null || view == null) {
            return;
        }

        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(view, 0);
        }
    }

    /**
     * 隐藏输入法
     *
     * @param mAct activity
     */
    public static void hideInputMethod(Activity mAct) {
        try {// hide keybord anyway
            View v = mAct.getWindow().getCurrentFocus();
            if (v != null) {
                InputMethodManager imm = (InputMethodManager) mAct.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
        } catch (Exception e) {
        }
    }

    /**
     * 显示输入法
     *
     * @param mAct activity
     */
    public static void showInputMethod(final Activity mAct) {
        View v = mAct.getCurrentFocus();
        if (null == v) {
            return;
        }
        ((InputMethodManager) mAct.getSystemService(Activity.INPUT_METHOD_SERVICE)).showSoftInput(v, 0);
    }

    /**
     * 显示和隐藏软键盘 View ： EditText、TextView isShow : true = show , false = hide
     *
     * @param context
     * @param view
     * @param isShow
     */
    public static void popSoftKeyboard(Context context, View view,
                                       boolean isShow) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (isShow) {
            view.requestFocus();
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        } else {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * 显示软键盘
     *
     * @param view
     */
    public static void showSoftKeyboard(View view) {
        Context context = view.getContext();
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }

    /**
     * 隐藏软键盘
     *
     * @param view
     */
    public static void hideSoftKeyboard(View view) {
        Context context = view.getContext();
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    /**
     * 该类用于监听软键盘是否显示并获取其高度
     * @param activity
     * @param onSoftKeyBoardChangeListener
     */
    public static void addKeyboardListener(Activity activity, final OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
        /* 获取activity的根视图 */
        final View rootView = activity.getWindow().getDecorView();
        final int[] rootViewVisibleHeight = new int[1];
        /* 监听视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变 */
        rootView.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        /* 获取当前根视图在屏幕上显示的大小 */
                        Rect r = new Rect();
                        rootView.getWindowVisibleDisplayFrame(r);
                        int visibleHeight = r.height();
                        if (rootViewVisibleHeight[0] == 0) {
                            rootViewVisibleHeight[0] = visibleHeight;
                            return;
                        }
                        /* 根视图显示高度没有变化，可以看作软键盘显示／隐藏状态没有改变 */
                        if (rootViewVisibleHeight[0] == visibleHeight) {
                            return;
                        }
                        /* 根视图显示高度变小超过200，可以看作软键盘显示了 */
                        if (rootViewVisibleHeight[0] - visibleHeight > 200) {
                            if (onSoftKeyBoardChangeListener != null) {
                                onSoftKeyBoardChangeListener
                                        .keyBoardShow(rootViewVisibleHeight[0]
                                                - visibleHeight);
                            }
                            rootViewVisibleHeight[0] = visibleHeight;
                            return;
                        }
                        /* 根视图显示高度变大超过200，可以看作软键盘隐藏了 */
                        if (visibleHeight - rootViewVisibleHeight[0] > 200) {
                            if (onSoftKeyBoardChangeListener != null) {
                                onSoftKeyBoardChangeListener
                                        .keyBoardHide(visibleHeight
                                                - rootViewVisibleHeight[0]);
                            }
                            rootViewVisibleHeight[0] = visibleHeight;
                            return;
                        }
                    }
                });
    }

    public interface OnSoftKeyBoardChangeListener {
        void keyBoardShow(int height);
        void keyBoardHide(int height);
    }
}
