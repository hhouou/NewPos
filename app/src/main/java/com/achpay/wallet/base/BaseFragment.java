package com.achpay.wallet.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import javax.inject.Inject;
import com.achpay.wallet.app.App;
import com.achpay.wallet.di.component.DaggerFragmentComponent;
import com.achpay.wallet.di.component.FragmentComponent;
import com.achpay.wallet.di.module.FragmentModule;
import com.achpay.wallet.ui.dialog.DialogLodingFragment;
import com.achpay.wallet.util.ToastUtil;

/**
 * Created by 95 on 2017/5/3.
 */

public abstract class BaseFragment<T extends BasePresenter> extends SimpleFragment implements BaseView {

    @Inject
    protected T mPresenter;
    private DialogLodingFragment lodingFragment;


    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .fragmentModule(getFragmentModule())
                .appComponent(App.getAppComponent())
                .build();
    }

    protected FragmentModule getFragmentModule() {
        return new FragmentModule(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle bSavedInstanceState) {
        initInject();
        mPresenter.attachView(this);
        super.onViewCreated(view, bSavedInstanceState);


    }



    @Override
    public void onDestroyView() {
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroyView();
    }
    

    @Override
    public void showErrorMsg(String msg) {
        ToastUtil.shortShow(msg);
    }


    @Override
    public void stateLoading() {
        lodingFragment = DialogLodingFragment.init(true);
        lodingFragment.show(getFragmentManager(), "dialog-loading");
    }

    @Override
    public void stateStop() {
        if (null != lodingFragment) lodingFragment.dismiss();

    }

    @Override
    public void stateError() {


    }

    @Override
    public void stateMain() {

    }

    @Override
    public void userNightMode(boolean isNight) {

    }

    protected abstract void initInject();





}
