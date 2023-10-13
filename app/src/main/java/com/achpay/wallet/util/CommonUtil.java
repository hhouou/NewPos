package com.achpay.wallet.util;

import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 95 on 2017/5/5.
 */

public class CommonUtil {
    public static String getMD5(String s) {
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
        };
        try {
            byte[] strTemp = s.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte b = md[i];
                str[k++] = hexDigits[b >> 4 & 0xf];
                str[k++] = hexDigits[b & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static String encryptPassword(String password) {
        if (password != null && password.length() > 0) {
            String base64Str = Base64.encodeToString(password.getBytes(), Base64.DEFAULT);
            String md5Str = getMD5(password);
            return md5Str.substring(0, 16) + base64Str + md5Str.substring(16);
        }
        return "";
    }

    public static String decryptPassword(String password) {
        if (password != null && password.length() > 32) {
            String base64Str = password.substring(16, password.length() - 16);
            try {
                return new String(Base64.decode(base64Str, Base64.DEFAULT), "UTF-8");
            } catch (UnsupportedEncodingException e) {

                e.printStackTrace();
            }
        }
        return "";
    }
    public static ArrayList<String> geteducationList(){
        ArrayList<String> list = new ArrayList<>();
        list.add("研究生及以上");
        list.add("大学本科");
        list.add("大专");
        list.add("高中");
        list.add("中专");
        list.add("初中及以下");
        return list;
    }
    public static ArrayList<String> getproviceList(){
        ArrayList<String> list = new ArrayList<>();
        list.add("北京");
        list.add("上海");
        list.add("广州");
        return list;
    }
    public static ArrayList<Map<String,String>> getmarriageList(){
        ArrayList<Map<String,String>> list = new ArrayList<>();
        Map<String,String>map1=new HashMap<>();
        map1.put("key","0");
        map1.put("value","未婚");
        list.add(map1);
        Map<String,String>map2=new HashMap<>();
        map2.put("key","1");
        map2.put("value","已婚");
        list.add(map2);
        return list;
    }
    public static ArrayList<String> getrelationList(){
        ArrayList<String> list = new ArrayList<>();
        list.add("父子");
        list.add("母子");
        list.add("父女");
        list.add("母女");
        list.add("同事");
        list.add("同学");
        return list;
    }
    public static ArrayList< Map<String,String>> getBankList(){
        ArrayList< Map<String,String>> list = new ArrayList<>();
        Map<String,String>map=new HashMap<>();
        map.put("value","工商银行");
        list.add(map);
        Map<String,String>map1=new HashMap<>();
        map.put("value","交通银行");
        list.add(map1);
        Map<String,String>map2=new HashMap<>();
        map.put("value","中国银行");
        list.add(map2);

        return list;
    }
    public static ArrayList<String> getsalaryList(){
        ArrayList<String> list = new ArrayList<>();
        list.add("1000-2000");
        list.add("2000-3000");
        list.add("3000-4000");
        list.add("4000-5000");
        list.add("5000-8000");
        list.add("10000以上");
        return list;
    }
    public static ArrayList<String> getBorrowMoneyList(){
        ArrayList<String> list = new ArrayList<>();
        list.add("1000-2000");
        list.add("2000-3000");
        list.add("3000-4000");
        list.add("4000-5000");
        list.add("5000-8000");
        list.add("10000");
        return list;
    }
    public static ArrayList<String> getBorrowTimeList(){
        ArrayList<String> list = new ArrayList<>();
        list.add("一天");
        list.add("一周");
        list.add("一个月");
        list.add("三个月");
        list.add("六个月");
        list.add("一年");
        return list;
    }
    public static final int USER_INFO_MARRIAGE = 0;
    public static final int USER_INFO_EDUCATION = 1;
    public static final int USER_INFO_INCOME = 2;
    public static final int USER_INFO_PROVINCE = 3;
    public static final int RELATION_INFO_URGENCY = 4;
    public static final int RELATION_INFO_COMMON = 5;
    public static final String LINKMAN_INFO_URGENCY = "urgencylinkman";
    public static final String LINKMAN_INFO_COMMON = "commonlinkman";

    public static final String BORROWMONEY_NUM = "borrowmoneynum";
    public static final String BORROWMONEY_TIME = "borrowmoneytime";

    public static boolean isMobileNO(String mobiles) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String telRegex = "[1][3578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        return mobiles.matches(telRegex);
    }

}
