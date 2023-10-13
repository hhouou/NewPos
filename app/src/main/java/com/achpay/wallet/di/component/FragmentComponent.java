package com.achpay.wallet.di.component;

import android.app.Activity;
import com.achpay.wallet.di.module.FragmentModule;
import com.achpay.wallet.di.scope.FragmentScope;
import com.achpay.wallet.ui.fragment.MineFragment;
import com.achpay.wallet.ui.fragment.TransactionsFragment;
import com.achpay.wallet.ui.fragment.PaymentFragment;
import com.achpay.wallet.ui.main.MainFragment;

import dagger.Component;

/**
 * Created by 95 on 2017/5/3.
 */

@FragmentScope
@Component(dependencies = AppComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();

    void inject(MainFragment fragment);

    void inject(TransactionsFragment fragment);
    void inject(PaymentFragment fragment);
    void inject(MineFragment fragment);


}
