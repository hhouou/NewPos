package com.achpay.wallet.presenter;


import javax.inject.Inject;

import com.achpay.wallet.app.App;
import com.achpay.wallet.base.Contract.MineContract;
import com.achpay.wallet.base.RxPresenter;
import com.achpay.wallet.model.DataManager;
import com.achpay.wallet.model.UserManager;
import com.achpay.wallet.model.http.response.AccountReponse;
import com.achpay.wallet.util.RxUtil;

import io.reactivex.functions.Consumer;

/**
 * Created by 95 on 2017/9/11.
 */

public class MinePresenter extends RxPresenter<MineContract.View> implements MineContract.Presenter {

    private final UserManager userManager;
    private DataManager dataManager;

    @Inject
    public MinePresenter(DataManager dataManager1, UserManager userManager1) {
        dataManager = dataManager1;
        userManager = userManager1;
    }

    @Override
    public void signOut(){
        addSubscribe(dataManager.logout()
        .compose(RxUtil.unifySchedulerHelper())
        .subscribe(new Consumer() {
            @Override
            public void accept(Object o) throws Exception {
                mView.signOutSuceess();

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mView.showErrorMsg(throwable.toString());
            }
        }));
    }

    @Override
    public void account(){
        addSubscribe(dataManager.account(App.getMerchantCode())
                .compose(RxUtil.unifySchedulerHelper())
                .subscribe(new Consumer<AccountReponse>() {
                    @Override
                    public void accept(AccountReponse o) throws Exception {
                        mView.accountSuceess(o);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showErrorMsg(throwable.toString());
                    }
                }));
    }

}
