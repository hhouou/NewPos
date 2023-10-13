package com.achpay.wallet.model.database;

import com.achpay.wallet.model.database.bean.User;

/**
 * Created by 95 on 2017/5/3.
 */

public interface DBHelper {
    //查找默认用户
    User getDefaultUser();

    //更新用戶
    void updateUser(User user);

    void setDefaultUser(User user);

    //删除用户
    void deleteUser();


}
