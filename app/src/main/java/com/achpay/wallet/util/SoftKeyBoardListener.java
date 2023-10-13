package com.achpay.wallet.util;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

public class SoftKeyBoardListener {
    private View rootView;
    private OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener;
    private int rootViewVisibleHeight;//纪录根视图的显示高度

    public SoftKeyBoardListener(View rootView) {
        this.rootView = rootView;
        this.rootView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //获取当前根视图在屏幕上显示的大小
                Rect r = new Rect();
                SoftKeyBoardListener.this.rootView.getWindowVisibleDisplayFrame(r);

                int visibleHeight = r.height();
                if (rootViewVisibleHeight == 0) {
                    rootViewVisibleHeight = visibleHeight;
                    return;
                }

                //根视图显示高度没有变化，可以看作软键盘显示／隐藏状态没有改变
                if (rootViewVisibleHeight == visibleHeight) {
                    return;
                }

                //根视图显示高度变小超过250，可以看作软键盘显示了
                if (rootViewVisibleHeight - visibleHeight > 250) {
                    if (onSoftKeyBoardChangeListener != null) {
                        onSoftKeyBoardChangeListener.onKeyBoardShow(rootViewVisibleHeight - visibleHeight);
                    }
                    rootViewVisibleHeight = visibleHeight;
                    return;
                }

                //根视图显示高度变大超过250，可以看作软键盘隐藏了
                if (visibleHeight - rootViewVisibleHeight > 250) {
                    if (onSoftKeyBoardChangeListener != null) {
                        onSoftKeyBoardChangeListener.onKeyBoardHide(visibleHeight - rootViewVisibleHeight);
                    }
                    rootViewVisibleHeight = visibleHeight;
                }
            }
        });
    }

    public void setOnSoftKeyBoardChangeListener(OnSoftKeyBoardChangeListener onSoftKeyBoardChangeListener) {
        this.onSoftKeyBoardChangeListener = onSoftKeyBoardChangeListener;
    }

    public interface OnSoftKeyBoardChangeListener {
        void onKeyBoardShow(int height);

        void onKeyBoardHide(int height);
    }

    public static class SimpleSoftKeyBoardChangeListener implements OnSoftKeyBoardChangeListener{

        @Override
        public void onKeyBoardShow(int height) {

        }

        @Override
        public void onKeyBoardHide(int height) {

        }
    }

}
