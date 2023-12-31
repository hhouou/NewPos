package com.achpay.wallet.model.database.dao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.achpay.wallet.model.database.bean.Banner;
import com.achpay.wallet.model.database.bean.LhbOpen;
import com.achpay.wallet.model.database.bean.LoanDay;
import com.achpay.wallet.model.database.bean.LoanMoney;
import com.achpay.wallet.model.database.bean.LoanMyMessage;
import com.achpay.wallet.model.database.bean.LoanUseOther;
import com.achpay.wallet.model.database.bean.User;
import com.achpay.wallet.model.database.bean.UserAddress;

import com.achpay.wallet.model.database.dao.BannerDao;
import com.achpay.wallet.model.database.dao.LhbOpenDao;
import com.achpay.wallet.model.database.dao.LoanDayDao;
import com.achpay.wallet.model.database.dao.LoanMoneyDao;
import com.achpay.wallet.model.database.dao.LoanMyMessageDao;
import com.achpay.wallet.model.database.dao.LoanUseOtherDao;
import com.achpay.wallet.model.database.dao.UserDao;
import com.achpay.wallet.model.database.dao.UserAddressDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig bannerDaoConfig;
    private final DaoConfig lhbOpenDaoConfig;
    private final DaoConfig loanDayDaoConfig;
    private final DaoConfig loanMoneyDaoConfig;
    private final DaoConfig loanMyMessageDaoConfig;
    private final DaoConfig loanUseOtherDaoConfig;
    private final DaoConfig userDaoConfig;
    private final DaoConfig userAddressDaoConfig;

    private final BannerDao bannerDao;
    private final LhbOpenDao lhbOpenDao;
    private final LoanDayDao loanDayDao;
    private final LoanMoneyDao loanMoneyDao;
    private final LoanMyMessageDao loanMyMessageDao;
    private final LoanUseOtherDao loanUseOtherDao;
    private final UserDao userDao;
    private final UserAddressDao userAddressDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        bannerDaoConfig = daoConfigMap.get(BannerDao.class).clone();
        bannerDaoConfig.initIdentityScope(type);

        lhbOpenDaoConfig = daoConfigMap.get(LhbOpenDao.class).clone();
        lhbOpenDaoConfig.initIdentityScope(type);

        loanDayDaoConfig = daoConfigMap.get(LoanDayDao.class).clone();
        loanDayDaoConfig.initIdentityScope(type);

        loanMoneyDaoConfig = daoConfigMap.get(LoanMoneyDao.class).clone();
        loanMoneyDaoConfig.initIdentityScope(type);

        loanMyMessageDaoConfig = daoConfigMap.get(LoanMyMessageDao.class).clone();
        loanMyMessageDaoConfig.initIdentityScope(type);

        loanUseOtherDaoConfig = daoConfigMap.get(LoanUseOtherDao.class).clone();
        loanUseOtherDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        userAddressDaoConfig = daoConfigMap.get(UserAddressDao.class).clone();
        userAddressDaoConfig.initIdentityScope(type);

        bannerDao = new BannerDao(bannerDaoConfig, this);
        lhbOpenDao = new LhbOpenDao(lhbOpenDaoConfig, this);
        loanDayDao = new LoanDayDao(loanDayDaoConfig, this);
        loanMoneyDao = new LoanMoneyDao(loanMoneyDaoConfig, this);
        loanMyMessageDao = new LoanMyMessageDao(loanMyMessageDaoConfig, this);
        loanUseOtherDao = new LoanUseOtherDao(loanUseOtherDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);
        userAddressDao = new UserAddressDao(userAddressDaoConfig, this);

        registerDao(Banner.class, bannerDao);
        registerDao(LhbOpen.class, lhbOpenDao);
        registerDao(LoanDay.class, loanDayDao);
        registerDao(LoanMoney.class, loanMoneyDao);
        registerDao(LoanMyMessage.class, loanMyMessageDao);
        registerDao(LoanUseOther.class, loanUseOtherDao);
        registerDao(User.class, userDao);
        registerDao(UserAddress.class, userAddressDao);
    }
    
    public void clear() {
        bannerDaoConfig.clearIdentityScope();
        lhbOpenDaoConfig.clearIdentityScope();
        loanDayDaoConfig.clearIdentityScope();
        loanMoneyDaoConfig.clearIdentityScope();
        loanMyMessageDaoConfig.clearIdentityScope();
        loanUseOtherDaoConfig.clearIdentityScope();
        userDaoConfig.clearIdentityScope();
        userAddressDaoConfig.clearIdentityScope();
    }

    public BannerDao getBannerDao() {
        return bannerDao;
    }

    public LhbOpenDao getLhbOpenDao() {
        return lhbOpenDao;
    }

    public LoanDayDao getLoanDayDao() {
        return loanDayDao;
    }

    public LoanMoneyDao getLoanMoneyDao() {
        return loanMoneyDao;
    }

    public LoanMyMessageDao getLoanMyMessageDao() {
        return loanMyMessageDao;
    }

    public LoanUseOtherDao getLoanUseOtherDao() {
        return loanUseOtherDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public UserAddressDao getUserAddressDao() {
        return userAddressDao;
    }

}
