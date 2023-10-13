package com.achpay.wallet.di.module;

import javax.inject.Singleton;

import com.achpay.wallet.app.App;
import com.achpay.wallet.model.DataManager;
import com.achpay.wallet.model.UserManager;
import com.achpay.wallet.model.database.DBHelper;
import com.achpay.wallet.model.database.DatabaseManager;
import com.achpay.wallet.model.http.HttpHelper;
import com.achpay.wallet.model.http.RetrofitHelper;
import com.achpay.wallet.model.prefs.ImplPreferencesHelper;
import com.achpay.wallet.model.prefs.PreferencesHelper;
import dagger.Module;
import dagger.Provides;

/**
 * Created by 95 on 2017/4/29.
 */

@Module
public class AppModule {
    private final App application;

    public AppModule(App app){
        application = app;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    HttpHelper provideHttpHelper(RetrofitHelper helper){
        return helper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(ImplPreferencesHelper preferencesHelper){
        return preferencesHelper;
    }

    @Provides
    @Singleton
    DBHelper provideDBHelper(DatabaseManager databaseManager){
        return databaseManager;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(HttpHelper httpHelper, PreferencesHelper preferencesHelper,DBHelper dbHelper){
        return new DataManager(httpHelper,preferencesHelper,dbHelper);
    }

    @Singleton
    @Provides
    UserManager provideUserManager(DBHelper dbHelper){
        return new UserManager(dbHelper);
    }
}
