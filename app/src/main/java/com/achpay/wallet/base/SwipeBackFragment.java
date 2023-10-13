package com.achpay.wallet.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.FloatRange;
import androidx.annotation.Nullable;

import com.achpay.wallet.util.ToastUtil;
import me.yokeyword.fragmentation.SwipeBackLayout;
import me.yokeyword.fragmentation_swipeback.core.ISwipeBackFragment;
import me.yokeyword.fragmentation_swipeback.core.SwipeBackFragmentDelegate;

/**
 * Created by 95 on 2017/9/17.
 */

public abstract class SwipeBackFragment<T extends BasePresenter> extends BaseFragment<T> implements ISwipeBackFragment {
    protected abstract void initInject();

    final SwipeBackFragmentDelegate mDelegate = new SwipeBackFragmentDelegate(this);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDelegate.onCreate(savedInstanceState);
        SwipeBackLayout layout = mDelegate.getSwipeBackLayout();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bSavedInstanceState) {
        View view = super.onCreateView(inflater, viewGroup, bSavedInstanceState);
        return attachToSwipeBack(view);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
         super.onViewCreated(view, savedInstanceState);
        mDelegate.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View attachToSwipeBack(View view) {
        return mDelegate.attachToSwipeBack(view);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mDelegate.onHiddenChanged(hidden);
    }

    public SwipeBackLayout getSwipeBackLayout() {
        return mDelegate.getSwipeBackLayout();
    }

    public void setSwipeBackEnable(boolean enable) {
        mDelegate.setSwipeBackEnable(enable);
    }

    public void setParallaxOffset(@FloatRange(from = 0.0f, to = 1.0f) float offset) {
        mDelegate.setParallaxOffset(offset);
    }

    @Override
    public void onDestroyView() {
        mDelegate.onDestroyView();
        super.onDestroyView();
    }

    @Override
    public void showErrorMsg(String msg) {
        ToastUtil.shortShow(msg);
    }
}
