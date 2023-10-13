package com.achpay.wallet.di.component;

import android.app.Activity;

import com.achpay.wallet.di.module.ActivityModule;
import com.achpay.wallet.di.scope.ActivityScope;
import com.achpay.wallet.ui.activity.ArgentinaPayActivity;
import com.achpay.wallet.ui.activity.CoinReceiveActivity;
import com.achpay.wallet.ui.activity.FiatCardPayActivity;
import com.achpay.wallet.ui.activity.LoginActivity;
import com.achpay.wallet.ui.activity.OrderDetailsActivity;
import com.achpay.wallet.ui.activity.SelectCoinActivity;
import com.achpay.wallet.ui.activity.SettngActivity;
import com.achpay.wallet.ui.activity.SignUpActivity;
import com.achpay.wallet.ui.activity.SummaryActivity;
import com.achpay.wallet.ui.activity.TransactionSummaryActivity;
import com.achpay.wallet.ui.activity.WebviewActivity;
import com.achpay.wallet.ui.main.MainActivity;
import com.achpay.wallet.ui.activity.WelcomeActivity;
import com.achpay.wallet.ui.rate.ExchangeRateActivity;
import com.achpay.wallet.ui.rate.SelectCryptoActivity;
import com.achpay.wallet.ui.rate.SelectFiatActivity;

import dagger.Component;

/**
 * Created by 95 on 2017/4/29.
 */

@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    void inject(MainActivity activity);
    void inject(WelcomeActivity activity);
    void inject(LoginActivity activity);
    void inject(SignUpActivity activity);
    void inject(SelectCoinActivity activity);
    void inject(SettngActivity activity);
    void inject(CoinReceiveActivity activity);
    void inject(ExchangeRateActivity activity);
    void inject(SelectCryptoActivity activity);
    void inject(SelectFiatActivity activity);
    void inject(OrderDetailsActivity activity);
    void inject(TransactionSummaryActivity activity);
    void inject(SummaryActivity activity);
    void inject(FiatCardPayActivity activity);
    void inject(ArgentinaPayActivity activity);
    void inject(WebviewActivity activity);

}
