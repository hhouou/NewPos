package com.achpay.wallet.util;

import android.content.Context;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.achpay.wallet.app.Constants;

/**
 * Created by hy on 2017/9/15.
 */

public class TimeUtils {


    private static SimpleDateFormat sdf = null;

    public static String formatUTC(long l, String strPattern) {
        if (TextUtils.isEmpty(strPattern)) {
            strPattern = "yyyy-MM-dd HH:mm:ss";
        }
        if (sdf == null) {
            try {
                sdf = new SimpleDateFormat(strPattern, Locale.CHINA);
            } catch (Throwable e) {
            }
        } else {
            sdf.applyPattern(strPattern);
        }
        return sdf == null ? "NULL" : sdf.format(l);
    }


    public static String getnowtime(String strPattern, Context context) {
        if (TextUtils.isEmpty(strPattern)) {
            strPattern = "yyyy-MM-dd HH:mm:ss";
        }
        if (sdf == null) {
            try {
                sdf = new SimpleDateFormat(strPattern, Locale.CHINA);
            } catch (Throwable e) {
            }
        } else {
            sdf.applyPattern(strPattern);
        }
        Date nowDate = new Date(System.currentTimeMillis());//获取当前时间
        String str  = sdf.format(nowDate);
//        String longDate = String.valueOf(nowDate.getTime() / 1000);
        return str;
    }

    //    yyyy-MM-dd HH:mm:ss 转  yyyy-MM-dd
    public static String timeToFormat(String inTime) {
        if (null == inTime) return "";
        String result = "";
        try {
            SimpleDateFormat s1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat s2 = new SimpleDateFormat("yyyy-MM-dd");
            Date tempDate = null;
            tempDate = s1.parse(inTime);
            result = s2.format(s2.parse(s1.format(tempDate)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean isShow( String time) {
        if ("".equals(time)) return true;
        int num = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Long curTime = System.currentTimeMillis();
            Long loanApplyData = (sdf.parse(time)).getTime();
            Long timeCA=curTime - loanApplyData;
            num = (int) (timeCA / (1000 * 60));

        } catch (ParseException e) {
            e.printStackTrace();
        }
        int rejectionTime= Constants.REJECTIONTIME==0?1: Constants.REJECTIONTIME;

        return num >= rejectionTime;
    }


    // 两次点击按钮之间的点击间隔不能少于2000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 2000;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }
}
