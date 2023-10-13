package com.achpay.wallet.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.achpay.wallet.R;
import com.achpay.wallet.app.App;
import com.achpay.wallet.app.AppManager;
import com.achpay.wallet.util.StatusBarUtil;
import com.gyf.immersionbar.ImmersionBar;

import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by 95 on 2017/4/29.
 */

public abstract class SimpleActivity extends SupportActivity {
    private Unbinder mUnbinder;
    protected Activity mContext;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(App.localeManager.setLocale(base));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        mContext = this;
        mUnbinder = ButterKnife.bind(this);

        ImmersionBar.with(this)
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .init();


        View view = findViewById(R.id.iv_back);
        if (view != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }


        onViewCreated();
        initEventAndData();

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    protected void onViewCreated(){

    }


    protected abstract int getLayout();
    protected abstract void initEventAndData();


}
