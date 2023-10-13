package com.achpay.wallet.presenter;

import javax.inject.Inject;

import com.achpay.wallet.base.Contract.MainContract;
import com.achpay.wallet.base.RxPresenter;
import com.achpay.wallet.model.DataManager;
import com.achpay.wallet.model.UserManager;

/**
 * Created by 95 on 2017/9/10.
 */

public class MainPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    UserManager userManager;
    DataManager dataManager;

    @Inject
    public MainPresenter(DataManager dataManager1, UserManager userManager1) {
        dataManager = dataManager1;
        userManager = userManager1;

    }



}
