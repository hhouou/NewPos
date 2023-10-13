package com.achpay.wallet.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.achpay.wallet.R;
import com.achpay.wallet.app.App;
import com.achpay.wallet.app.AppManager;
import com.achpay.wallet.base.BaseActivity;
import com.achpay.wallet.base.Contract.WelcomeContract;
import com.achpay.wallet.presenter.WelcomePresenter;
import com.achpay.wallet.ui.main.MainActivity;
import com.achpay.wallet.util.StrUtils;
import com.achpay.wallet.util.SystemUtil;

/**
 * @desc 启动屏
 * Created by hy on 21/10/13.
 */
public class WelcomeActivity extends BaseActivity<WelcomePresenter> implements WelcomeContract.View{

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }


    @Override
    protected void initEventAndData() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (StrUtils.isNotBlank(App.getToken())){
                    Intent intent = new Intent(mContext, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        }, 2000);

        SystemUtil.updateLocale(this);

    }



}
