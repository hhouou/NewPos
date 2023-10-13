package com.achpay.wallet.app;

import android.os.Environment;

import com.achpay.wallet.R;

import java.io.File;

/**
 * Created by 95 on 2017/9/10.
 */

public class Constants {



    //================= DB ====================
    public static final String DATABASE_NAME = "MiShiMiAi.db";

    //================= 缓存文件目录 ====================
    public static final String PATH_DATA = App.getInstance().getCacheDir().getAbsolutePath() + File.separator + "mmdata";
    public static final String PATH_CACHE = PATH_DATA + "/NetCache";

    //================= PREFERENCE ====================
    public static final String SP_USERREPLY_CONTENT = "userreply_content";
    public static final String SP_ISSHOWSMFRAGMENT = "sp_isshowSmFragment";

    public static final String SETTING_LANGUAGE = "SETTING_LANGUAGE";//语言



    //================= TIME====================
    public static final long ONE_MINUTE_MILLIONS = 60 * 1000;
    public static final long ONE_HOUR_MILLIONS = 60 * ONE_MINUTE_MILLIONS;
    public static final long ONE_DAY_MILLIONS = 24 * ONE_HOUR_MILLIONS;


    //================= 排队状态显示时间====================
    public static int REJECTIONTIME = 1;


    //================= 保存本地数据sp ====================


    //================= 不需要加密的接口 ====================
    public static final String POST_LOGIN = "login";
    public static final String POST_LOGOUT = "logout";


    //=========首页导航栏========
    public static final int TYPE_MAIN_PAYMENT = 1;//Payment
    public static final int TYPE_MAIN_TRANSACTIONS = 2;//Transactions
    public static final int TYPE_MAIN_MINE = 3;//mine


    public static final String NET_ERROR = "网络异常请重试";


    //=============路径=============
    public static final String PATH_COMMON = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;


}
