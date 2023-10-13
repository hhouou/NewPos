package com.achpay.wallet.presenter;

import com.achpay.wallet.base.Contract.ExchangeRateContract;
import com.achpay.wallet.base.Contract.MainContract;
import com.achpay.wallet.base.RxPresenter;
import com.achpay.wallet.model.DataManager;
import com.achpay.wallet.model.UserManager;
import com.achpay.wallet.model.http.request.ExchangeRateReq;
import com.achpay.wallet.util.RxUtil;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by 95 on 2017/9/10.
 */

public class ExchangeRatePresenter extends RxPresenter<ExchangeRateContract.View> implements ExchangeRateContract.Presenter {

    UserManager userManager;
    DataManager dataManager;

    @Inject
    public ExchangeRatePresenter(DataManager dataManager1, UserManager userManager1) {
        dataManager = dataManager1;
        userManager = userManager1;

    }


    public void getExchangeRate(String coinName, String fiatName){
        addSubscribe(dataManager.exchangeRate(new ExchangeRateReq(coinName,fiatName))
        .compose(RxUtil.unifySchedulerHelper())
        .subscribe(new Consumer<String>() {
            @Override
            public void accept(String rate) throws Exception {
                mView.onExchangeRate(rate);


            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mView.showErrorMsg(throwable.getMessage());

            }
        }));
    }


}
