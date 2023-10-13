package com.achpay.wallet.presenter;

import javax.inject.Inject;

import com.achpay.wallet.base.Contract.WelcomeContract;
import com.achpay.wallet.base.RxPresenter;
import com.achpay.wallet.model.DataManager;
import com.achpay.wallet.model.UserManager;

/**
 * Created by 95 on 2017/9/10.
 */

public class WelcomePresenter extends RxPresenter<WelcomeContract.View> implements WelcomeContract.Presenter {

    UserManager userManager;
    DataManager dataManager;

    @Inject
    public WelcomePresenter(DataManager dataManager1, UserManager userManager1) {
        dataManager = dataManager1;
        userManager = userManager1;

    }



}
