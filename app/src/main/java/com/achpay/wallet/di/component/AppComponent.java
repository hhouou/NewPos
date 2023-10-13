package com.achpay.wallet.di.component;

import javax.inject.Singleton;

import com.achpay.wallet.app.App;
import com.achpay.wallet.di.module.AppModule;
import com.achpay.wallet.di.module.HttpModule;
import com.achpay.wallet.model.DataManager;
import com.achpay.wallet.model.UserManager;
import com.achpay.wallet.model.prefs.ImplPreferencesHelper;
import dagger.Component;

/**
 * Created by 95 on 2017/4/29.
 */

@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {
    App getContext();

    DataManager getDataManager();

    UserManager getUserManger();

    ImplPreferencesHelper preferencesHelper(); //提供sp帮助类
}
