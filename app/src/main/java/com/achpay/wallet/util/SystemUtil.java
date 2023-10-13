package com.achpay.wallet.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Environment;
import android.os.LocaleList;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Locale;

import com.achpay.wallet.R;
import com.achpay.wallet.app.App;
import com.achpay.wallet.app.Constants;

/**
 * Created by 95 on 2017/5/2.
 */

public class SystemUtil {
    public static boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) App.getInstance().getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


    /**
     * 用于获取状态栏的高度。 使用Resource对象获取（推荐这种方式）
     *
     * @return 返回状态栏高度的像素值。
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }


    //手机唯一标识码
    public static String getCode() {
        return Settings.Secure.getString(App.getInstance().getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String getAppPackageName() {
        ActivityManager activityManager = (ActivityManager) App.getInstance().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfo = activityManager.getRunningTasks(1);
        ComponentName componentInfo = taskInfo.get(0).topActivity;
        Log.d("lixx", "当前应用:" + componentInfo.getPackageName());
        return componentInfo.getPackageName();
    }


    /**
     * 2  * 获取版本号
     * 3  * @return 当前应用的版本号
     * 4
     */
    public static String getVersionName() {
        try {
            PackageManager manager = App.getInstance().getPackageManager();
            PackageInfo info = manager.getPackageInfo(App.getInstance().getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 2  * 获取版本号
     * 3  * @return 当前应用的版本号
     * 4
     */
    public static int getVersionCode() {
        try {
            PackageManager manager = App.getInstance().getPackageManager();
            PackageInfo info = manager.getPackageInfo(App.getInstance().getPackageName(), 0);
            int version = info.versionCode;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }



    public static Context updateLocale(Context context) {
        Locale locale = getLocale(context);
        Locale.setDefault(locale);

        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        config.setLocale(locale);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            LocaleList localeList = new LocaleList(locale);
            LocaleList.setDefault(localeList);
            config.setLocales(localeList);
            context.createConfigurationContext(config);
        }
        resources.updateConfiguration(config, displayMetrics);


        return context;
    }


    public static Locale getLocale(Context context) {
        Locale locale;
        String language = getAppLanguage(context);
        if (TextUtils.isEmpty(language)) {
            Locale sysLocale = context.getResources().getConfiguration().locale;
            String sysLanguage = sysLocale.getLanguage();

            if (TextUtils.equals("in", sysLanguage)||TextUtils.equals("id", sysLanguage)) {
                locale = new Locale("in");
                setLanguage(context.getString(R.string.user_language_indonesian));
            }else if (TextUtils.equals("es", sysLanguage)) {
                locale = new Locale("es");
                setLanguage(context.getString(R.string.user_language_español));
            }  else if (TextUtils.equals("pt", sysLanguage)) {
                locale = new Locale("pt");
                setLanguage(context.getString(R.string.user_language_português));
            }
            else {
                locale = new Locale("en");
                setLanguage(context.getString(R.string.user_language_english));
            }
        }

        else {
            if (TextUtils.equals(language, context.getString(R.string.user_language_english))) {//中文;
                locale = new Locale("en");
                setLanguage(context.getString(R.string.user_language_english));
            } else if (TextUtils.equals(language, context.getString(R.string.user_language_indonesian))) {
                locale = new Locale("in");
            } else if (TextUtils.equals(language, context.getString(R.string.user_language_español))) {
                locale = new Locale("es");
            } else if (TextUtils.equals(language, context.getString(R.string.user_language_português))) {
                locale = new Locale("pt");
            }
            else {
                locale = new Locale("en");
                setLanguage(context.getString(R.string.user_language_english));
            }
        }
        return locale;
    }

    public static void setLanguage(String language) {
    SpUtils.putString(App.getInstance(), Constants.SETTING_LANGUAGE,language);

    }

    public static String getAppLanguage(Context context) {
        return SpUtils.getString(context, Constants.SETTING_LANGUAGE);
    }

}
