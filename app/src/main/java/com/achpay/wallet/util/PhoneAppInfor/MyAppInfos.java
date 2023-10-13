package com.achpay.wallet.util.PhoneAppInfor;

import android.graphics.drawable.Drawable;

/**
 * Created by hy on 2018/1/16.
 */

public class MyAppInfos {

    private Drawable image;
    private String packageName;
    private String AppName;
    private long firstInstallTime;
    private long lastUpdateTime;

    public MyAppInfos(Drawable image, String appName) {
        this.image = image;
        this.packageName = appName;
    }

    public MyAppInfos() {
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String appName) {
        this.packageName = appName;
    }

    public String getAppName() {
        return AppName;
    }

    public void setAppName(String appName) {
        AppName = appName;
    }

    public long getFirstInstallTime() {
        return firstInstallTime;
    }

    public void setFirstInstallTime(long firstInstallTime) {
        this.firstInstallTime = firstInstallTime;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
