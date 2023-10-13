package com.achpay.wallet.model.database;

import android.content.Context;
import android.util.Log;

import org.greenrobot.greendao.database.Database;

import com.achpay.wallet.model.database.dao.DaoMaster;

/**
 * Created by 95 on 2017/5/3.
 */

public class MyOpenHelper extends DaoMaster.OpenHelper {
    public MyOpenHelper(Context context, String name){
        super(context,name);
    }


    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {

        switch (newVersion){
            case 1:
                Log.i("onUpgrade",1+"case"+oldVersion+"xin"+newVersion);
                DaoMaster.createAllTables(db, true);
                break;
            case 2:
                Log.i("onUpgrade",2+"case"+oldVersion+"xin"+newVersion);
//                MigrationHelper.getInstance().migrate(db, UserDao.class);
                break;
            case 3:
                Log.i("onUpgrade",3+"case"+oldVersion+"xin"+newVersion);
//                MigrationHelper.getInstance().migrate(db, UserDao.class);
                break;
        }
    }
}
