package com.achpay.wallet.presenter;

import com.achpay.wallet.app.App;
import com.achpay.wallet.base.Contract.MainContract;
import com.achpay.wallet.base.Contract.SettingContract;
import com.achpay.wallet.base.RxPresenter;
import com.achpay.wallet.model.DataManager;
import com.achpay.wallet.model.UserManager;
import com.achpay.wallet.model.http.request.FiatEditReq;
import com.achpay.wallet.model.http.response.FiatListReponse;
import com.achpay.wallet.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by 95 on 2017/9/10.
 */

public class SettingPresenter extends RxPresenter<SettingContract.View> implements SettingContract.Presenter {

    UserManager userManager;
    DataManager dataManager;

    @Inject
    public SettingPresenter(DataManager dataManager1, UserManager userManager1) {
        dataManager = dataManager1;
        userManager = userManager1;

    }

    @Override
    public void fiatList(){
        mView.stateLoading();
        addSubscribe(dataManager.fiatList()
        .compose(RxUtil.unifySchedulerHelper())
        .subscribe(new Consumer<List<FiatListReponse>>() {
            @Override
            public void accept(List<FiatListReponse> o) throws Exception {
                mView.stateStop();
                mView.getFiatListSeccess(o);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mView.stateStop();
            }
        }));
    }
   @Override
    public void fiatEdit(int baseCurrencyId){
       mView.stateLoading();
        addSubscribe(dataManager.fiatEdit(new FiatEditReq(baseCurrencyId, App.getMerchantCode()))
        .compose(RxUtil.unifySchedulerHelper())
        .subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                mView.stateStop();
                mView.fiatEditSeccess(o);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                mView.stateStop();
            }
        }));
    }



}
