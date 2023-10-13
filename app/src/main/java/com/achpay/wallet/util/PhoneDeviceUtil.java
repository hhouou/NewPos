package com.achpay.wallet.util;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import android.telephony.TelephonyManager;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import static android.content.Context.TELEPHONY_SERVICE;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

/**
 * Created by 黑明阳 on 2017/9/27.
 */

public class PhoneDeviceUtil {

    private static TelephonyManager mTm = null;

    public static void getJurisdiction(Context context) {

        int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{
                    Manifest.permission.READ_PHONE_STATE}, 1);
        } else {
            //TODO
        }
    }


    //获取手机ID
    public static String getPhoneId(Context context) {
        getJurisdiction(context);
        mTm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
        String phoneid = mTm.getDeviceId();
        return phoneid;
    }

    //获取手机IMEI号
    public static String getPhoneIMEI(Context context) {
        getJurisdiction(context);

        mTm = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
        String phoneIMEI = mTm.getSubscriberId();
        return phoneIMEI;
    }

    //获取手机MAC地址
    public static String getPhoneMAC(Context context) {
        getJurisdiction(context);

        String result = "";
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        result = wifiInfo.getMacAddress();
        return result;
    }

    //获取手机型号
    public static String getPhoneType(Context context) {

        getJurisdiction(context);

        String phonetype = android.os.Build.MODEL;
        return phonetype;
    }

    public static String getPhoneIp(Context context) {
        getJurisdiction(context);

        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
                try {
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
                return ipAddress;
            }
        } else {
            //当前无网络连接,请在设置中打开网络
        }
        return null;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }




}
