package com.achpay.wallet.presenter;

import com.achpay.wallet.base.Contract.ExchangeRateContract;
import com.achpay.wallet.base.Contract.SelectCryptoContract;
import com.achpay.wallet.base.RxPresenter;
import com.achpay.wallet.model.DataManager;
import com.achpay.wallet.model.UserManager;
import com.achpay.wallet.model.http.response.CoinListReponse;
import com.achpay.wallet.util.RxUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by 95 on 2017/9/10.
 */

public class SelectCryptoPresenter extends RxPresenter<SelectCryptoContract.View> implements SelectCryptoContract.Presenter {

    UserManager userManager;
    DataManager dataManager;

    @Inject
    public SelectCryptoPresenter(DataManager dataManager1, UserManager userManager1) {
        dataManager = dataManager1;
        userManager = userManager1;

    }


    public void getCoinList() {
        addSubscribe(dataManager.onCoinPostList()
                .compose(RxUtil.unifySchedulerHelper())
                .subscribe(new Consumer<List<CoinListReponse>>() {
                    @Override
                    public void accept(List<CoinListReponse> o) throws Exception {
                        mView.getListSuceess(o);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showErrorMsg(throwable.toString());
                    }
                }));
    }

}
