package com.achpay.wallet.base;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.achpay.wallet.R;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.Utils;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityBase;
import me.imid.swipebacklayout.lib.app.SwipeBackActivityHelper;

/**
 * Created by kongcraft on 2017/5/11.
 */

public abstract class RootActivity<T extends BasePresenter> extends BaseActivity<T> implements SwipeBackActivityBase {

    private static final int STATE_MAIN = 0x00;
    private static final int STATE_LOADING = 0x01;
    private static final int STATE_ERROR = 0x02;
    public LinearLayout viewError;
    public FrameLayout viewLoading;
    public ViewGroup viewMain;
    private int currentState = STATE_MAIN;
    private SwipeBackActivityHelper mHelper;

    @Override
    protected void initEventAndData() {
        viewMain = (ViewGroup) findViewById(R.id.view_main);
        if (viewMain == null) {
            throw new IllegalStateException(
                    "The subclass of RootActivity must contain a View named view_main.");
        }
        if (!(viewMain.getParent() instanceof ViewGroup)) {
            throw new IllegalStateException(
                    "view_main's ParentView should be a ViewGroup");
        }
        ViewGroup parent = (ViewGroup) viewMain.getParent();
        View.inflate(mContext, R.layout.view_error, parent);
        View.inflate(mContext, R.layout.view_loading, parent);
        viewError = (LinearLayout) parent.findViewById(R.id.view_error);
        viewLoading = (FrameLayout) parent.findViewById(R.id.view_loading);
        viewError.setVisibility(View.GONE);
        viewLoading.setVisibility(View.GONE);
        viewMain.setVisibility(View.VISIBLE);
    }

    @Override
    public void stateError() {
        if (currentState == STATE_ERROR)
            return;
        hideCurrentView();
        currentState = STATE_ERROR;
        viewError.setVisibility(View.VISIBLE);
    }

    @Override
    public void stateLoading() {
        if (currentState == STATE_LOADING)
            return;
        hideCurrentView();
        currentState = STATE_LOADING;
        viewLoading.setVisibility(View.VISIBLE);
        //ivLoading.start();
    }

    @Override
    public void stateMain() {
        if (currentState == STATE_MAIN)
            return;
        hideCurrentView();
        currentState = STATE_MAIN;
        viewMain.setVisibility(View.VISIBLE);
    }

    private void hideCurrentView() {
        switch (currentState) {
            case STATE_MAIN:
                viewMain.setVisibility(View.GONE);
                break;
            case STATE_LOADING:
                viewLoading.setVisibility(View.GONE);
                break;
            case STATE_ERROR:
                viewError.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }



}