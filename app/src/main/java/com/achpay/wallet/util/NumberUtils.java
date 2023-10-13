package com.achpay.wallet.util;

import android.text.TextUtils;

import java.math.BigDecimal;
import java.util.Locale;

public class NumberUtils {
    /**
     * 判断是否是两位小数
     *
     * @param amount 数字
     * @return true or false
     */
    public static boolean isTwoDecimal(String amount) {
        if (amount.contains(".")) {//是小数
            if (amount.length() - (amount.indexOf(".") + 1) == 2) {
                return true;
            }
        }
        return false;
    }
    /**
     * 判断是否是几位小数
     *
     * @param amount 数字
     * @return true or false
     */
    public static boolean isNDecimal(int n,String amount) {
        if (amount.contains(".")) {//是小数
            if (amount.length() - (amount.indexOf(".") + 1) == n) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是一位小数
     *
     * @param amount 数字
     * @return true or false
     */
    public static boolean isOneDecimal(String amount) {
        if (amount.contains(".")) {//是小数
            if (amount.length() - (amount.indexOf(".") + 1) == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是八位小数
     *
     * @param amount 数字
     * @return true or false
     */
    public static boolean isEightDecimal(String amount) {
        if (amount.contains(".")) {//是小数
            if (amount.length() - (amount.indexOf(".") + 1) == 8) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是小数
     *
     * @param amount 数字
     * @return true or false
     */
    public static boolean isDecimal(String amount) {
        return amount.contains(".");
    }

    /**
     * 判断是否大于0
     *
     * @param amount 数字
     * @return true or false
     */
    public static boolean isGreaterThanZero(String amount) {
        double result;
        try {
            result = Double.parseDouble(amount);
        } catch (Exception e) {
            return false;
        }
        return result > 0;
    }

    /**
     * 将double数字转换为两位小数的
     * DecimalFormat is a concrete subclass of NumberFormat that formats decimal numbers.
     *
     * @param d
     * @return
     */
//    public static String formatDouble2(double d) {
//        return String.format(Locale.ENGLISH, "%.2f", d);
//    }

    //小数点后两位四舍五入
    public static String formatDouble2(String d) {
        BigDecimal bigDecimal = new BigDecimal(d);
        String bg = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        return bg;
    }

    /**
     * 将double数字转换为四位小数的
     * DecimalFormat is a concrete subclass of NumberFormat that formats decimal numbers.
     *
     * @param d
     * @return
     */
    public static String formatDouble4(double d) {
        return String.format(Locale.ENGLISH, "%.4f", d);
    }

    /**
     * 将double数字转换为8位小数的
     * DecimalFormat is a concrete subclass of NumberFormat that formats decimal numbers.
     *
     * @param d
     * @return
     */
    public static String formatDouble8(double d) {
        return String.format(Locale.ENGLISH, "%.8f", d);
    }

    public static boolean isWrongPercent(String percent) {
        if (TextUtils.isEmpty(percent)) {
            return true;
        }
        double percentage = Double.parseDouble(percent);
        if (percentage < 0 || percentage > 1) {
            return true;
        }
        return false;
    }

    public static boolean isWrongCash(String cash) {
        if (TextUtils.isEmpty(cash)) {
            return true;
        }
        double percentage = Double.parseDouble(cash);
        if (percentage < 0) {
            return true;
        }
        return false;
    }
}
