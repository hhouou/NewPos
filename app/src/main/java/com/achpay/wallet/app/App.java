package com.achpay.wallet.app;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import java.util.HashSet;
import java.util.Set;

import com.achpay.wallet.base.SimpleActivity;
import com.achpay.wallet.di.component.AppComponent;
import com.achpay.wallet.di.component.DaggerAppComponent;
import com.achpay.wallet.di.module.AppModule;
import com.achpay.wallet.ui.activity.LoginActivity;
import com.achpay.wallet.ui.main.MainActivity;
import com.achpay.wallet.util.CrachUtils.CrashHandler;
import com.achpay.wallet.util.LocaleManager;
import com.achpay.wallet.util.SpUtils;

/**
 * Created by 95 on 2017/4/29.
 */

public class App extends Application {

    private static App instance = null;
    public static AppComponent appComponent;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;
    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static LocaleManager localeManager;



    public static synchronized App getInstance() {
        return instance;
    }

    @Override
    protected void attachBaseContext(Context context) {
        localeManager = new LocaleManager(context);
        super.attachBaseContext(context);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        localeManager.setLocale(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        getScreenSize();
        setApplication(this);
    }

    public static synchronized void setApplication(@NonNull App application) {

        //注册监听每个activity的生命周期,便于堆栈式管理
        application.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {

            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                AppManager.getAppManager().addActivity(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {
            }

            @Override
            public void onActivityResumed(Activity activity) {
            }

            @Override
            public void onActivityPaused(Activity activity) {
            }

            @Override
            public void onActivityStopped(Activity activity) {
            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }

            @Override
            public void onActivityDestroyed(Activity activity) {
                AppManager.getAppManager().removeActivity(activity);
            }
        });
    }


    public void getScreenSize() {
        WindowManager manager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = manager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_WIDTH;
            SCREEN_WIDTH = SCREEN_HEIGHT;
            SCREEN_HEIGHT = t;
        }
    }

    public static void setToken(String token) {
        SpUtils.putString(instance, "token", token);
    }

    public static String getToken() {
        return SpUtils.getString(instance, "token", "");
    }

    public static void setMerchantCode(String merchantCode) {
        SpUtils.putString(instance, "merchantCode", merchantCode);
    }

    public static String getMerchantCode() {
        return SpUtils.getString(instance, "merchantCode");
    }

    public static void setMerchantName(String merchantName) {
        SpUtils.putString(instance, "merchantName", merchantName);
    }

    public static String getMerchantName() {
        return SpUtils.getString(instance, "merchantName");
    }

    public static void setCurrencyCode(String currencyCode) {
        SpUtils.putString(instance, "currencyCode", currencyCode);
    }

    public static String getCurrencyCode() {
        return SpUtils.getString(instance, "currencyCode");
    }

    public void exitLogin(Class<?> cls) {
        setToken("");
        setCurrencyCode("");
        setMerchantCode("");
        setMerchantName("");

        if (!AppManager.getAppManager().isOpenActivity(cls)) {
            setToken("");
            setCurrencyCode("");
            setMerchantCode("");
            setMerchantName("");
            Intent intent = new Intent(this, cls);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }

    }


    public static AppComponent getAppComponent() {
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder().appModule(new AppModule(App.getInstance())).build();
        }
        return appComponent;
    }


}
