package com.achpay.wallet.util.PhoneAppInfor;

import android.Manifest;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 扫描本地安装的应用,工具类
 */
public class ApkTool {
    static String TAG = "ApkTool";
    public static List mLocalInstallApps = null;

    public static String scanLocalInstallAppList(PackageManager packageManager) {
        List myAppList = new ArrayList();
        Gson gson = new Gson();
        try {
            List packageInfos = packageManager.getInstalledPackages(0);
            for (int i = 0; i < packageInfos.size(); i++) {
                PackageInfo packageInfo = (PackageInfo) packageInfos.get(i);
                //过滤掉系统app
                if ((ApplicationInfo.FLAG_SYSTEM & packageInfo.applicationInfo.flags) != 0) {
                    continue;
                }
                MyAppInfos myAppInfo = new MyAppInfos();
                myAppInfo.setPackageName(packageInfo.packageName);
                myAppInfo.setAppName(packageInfo.applicationInfo.loadLabel(packageManager).toString());
                //应用装时间
                long firstInstallTime = packageInfo.firstInstallTime;
                myAppInfo.setFirstInstallTime(firstInstallTime);
                //应用最后一次更新时间
                long lastUpdateTime = packageInfo.lastUpdateTime;
                myAppInfo.setLastUpdateTime(lastUpdateTime);

                if (packageInfo.applicationInfo.loadIcon(packageManager) == null) {
                    continue;
                }
//                myAppInfo.setImage(packageInfo.applicationInfo.loadIcon(packageManager));
                myAppList.add(myAppInfo);
            }
        } catch (Exception e) {
            Log.e(TAG, "===============获取应用包信息失败");
        }
        return gson.toJson(myAppList);
    }

    //获取手机IMEI号
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static String getImeiList(Context context) {
        Gson gson = new Gson();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        List imeiList = new ArrayList();
        if (null == telephonyManager) return "";

        try {
           int count= telephonyManager.getPhoneCount();
        }catch (Throwable ex){
            return "";

        }
            for (int slot = 0; slot < telephonyManager.getPhoneCount(); slot++) {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    return "";
                }
                String imei = "";
                try {
                    imei = telephonyManager.getDeviceId(slot);
                }catch (Exception e){
                    imei = "";
                }catch (Throwable ex){
                    imei = "";

                }

                imeiList.add(imei);
            }


         return gson.toJson(imeiList);
    }



    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前系统上的语言列表(Locale列表)
     *
     * @return  语言列表
     */
    public static Locale[] getSystemLanguageList() {
        return Locale.getAvailableLocales();
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return  系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return  手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return  手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }
}