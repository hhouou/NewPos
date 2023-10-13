package com.achpay.wallet.base;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.view.View;

import androidx.appcompat.app.AppCompatDelegate;

import javax.inject.Inject;
import com.achpay.wallet.app.App;
import com.achpay.wallet.di.component.ActivityComponent;
import com.achpay.wallet.di.component.DaggerActivityComponent;
import com.achpay.wallet.di.module.ActivityModule;
import com.achpay.wallet.ui.dialog.DialogLodingFragment;
import com.achpay.wallet.util.ToastUtil;

/**
 * Created by 95 on 2017/4/29.
 */

public abstract class BaseActivity<T extends BasePresenter> extends SimpleActivity implements BaseView{

    @Inject
    protected T mPresenter;
    private DialogLodingFragment lodingFragment;

    protected abstract void initInject();

    protected ActivityComponent getActivityComponent(){
        return DaggerActivityComponent.builder()
                .appComponent(App.getAppComponent())
                .activityModule(getActivityModule())
                .build();
    }

    protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }

    @Override
    protected void onViewCreated() {
        super.onViewCreated();
        initInject();
        if (mPresenter != null)
            mPresenter.attachView(this);
    }



    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.detachView();
        super.onDestroy();
    }


    @Override
    public void stateError() {

    }

    @Override
    public void stateLoading() {

        lodingFragment = DialogLodingFragment.init(true);
        lodingFragment.show(getSupportFragmentManager(), "dialog-loading");
    }

    @Override
    public void stateStop() {
        if (null != lodingFragment) lodingFragment.dismiss();
    }

    @Override
    public void stateMain() {

    }


    @Override
    public void showErrorMsg(String msg) {
        ToastUtil.shortShow(msg);
    }

    @Override
    public void userNightMode(boolean isNight) {
        if (isNight){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        recreate();
    }
    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics() );
        return res;
    }

    public void onBack(View view){
        finish();
    }

}
