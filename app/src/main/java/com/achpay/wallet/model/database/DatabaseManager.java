package com.achpay.wallet.model.database;

import android.database.sqlite.SQLiteDatabase;

import javax.inject.Inject;

import com.achpay.wallet.app.App;
import com.achpay.wallet.app.Constants;
import com.achpay.wallet.model.database.bean.User;
import com.achpay.wallet.model.database.dao.DaoMaster;
import com.achpay.wallet.model.database.dao.DaoSession;
import com.achpay.wallet.model.database.dao.UserDao;


/**
 * Created by 95 on 2017/5/3.
 */

public class DatabaseManager implements DBHelper {

    private SQLiteDatabase database;
    private DaoMaster daoMaster;
    private DaoSession daoSession;
    private MyOpenHelper databaseOpenHelper;

    @Inject
    DatabaseManager() {
        databaseOpenHelper = new MyOpenHelper(App.getInstance().getApplicationContext(), Constants.DATABASE_NAME);
        database = databaseOpenHelper.getWritableDatabase();
        daoMaster = new DaoMaster(database);
        daoSession = daoMaster.newSession();
    }

    @Override
    public User getDefaultUser() {
        UserDao userDao = daoSession.getUserDao();
        User user = userDao.queryBuilder().unique();
        return user;
    }


    @Override
    public void setDefaultUser(User user) {
        UserDao userDao = daoSession.getUserDao();
        userDao.insertOrReplace(user);
    }

    @Override
    public void deleteUser() {
        daoSession.getUserDao().deleteAll();
    }



    @Override
    public void updateUser(User user) {
        UserDao userDao = daoSession.getUserDao();
        userDao.insertOrReplaceInTx(user);
    }
}
