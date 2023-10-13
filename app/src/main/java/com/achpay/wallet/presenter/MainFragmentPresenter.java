package com.achpay.wallet.presenter;

import javax.inject.Inject;

import com.achpay.wallet.app.App;
import com.achpay.wallet.base.Contract.MainContract;
import com.achpay.wallet.base.RxPresenter;
import com.achpay.wallet.model.DataManager;
import com.achpay.wallet.model.UserManager;
import com.achpay.wallet.model.http.response.FiatListReponse;
import com.achpay.wallet.util.RxUtil;

import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by 95 on 2017/9/10.
 */

public class MainFragmentPresenter extends RxPresenter<MainContract.View> implements MainContract.Presenter {

    UserManager userManager;
    DataManager dataManager;

    @Inject
    public MainFragmentPresenter(DataManager dataManager1, UserManager userManager1) {
        dataManager = dataManager1;
        userManager = userManager1;

        fiatList();
    }


    public void fiatList(){
        addSubscribe(dataManager.fiatList()
                .compose(RxUtil.unifySchedulerHelper())
                .subscribe(new Consumer<List<FiatListReponse>>() {
                    @Override
                    public void accept(List<FiatListReponse> list) throws Exception {
                        for (FiatListReponse fiat:list){
                            if (fiat.getDefaults()==1){
                                App.setCurrencyCode(fiat.getCurrencyCode());
                            }
                        }

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                }));
    }


}
