package com.achpay.wallet.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.achpay.wallet.R;
import com.achpay.wallet.app.App;
import com.achpay.wallet.app.AppManager;
import com.achpay.wallet.base.BaseActivity;
import com.achpay.wallet.base.Contract.MainContract;
import com.achpay.wallet.presenter.MainPresenter;
import com.achpay.wallet.ui.activity.LoginActivity;
import com.achpay.wallet.util.WsManager;

/**
 * Created by 95 on 2017/9/10.
 */

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) return;
        loadRootFragment(R.id.view_main, new MainFragment());

    }


    @Override
    protected void initEventAndData() {
        WsManager.getInstance().init();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().AppExit();
        WsManager.getInstance().disconnect();

    }


}
