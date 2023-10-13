package com.achpay.wallet.presenter;

import android.util.Log;

import com.achpay.wallet.base.Contract.OrderDetailsContract;
import com.achpay.wallet.base.RxPresenter;
import com.achpay.wallet.model.DataManager;
import com.achpay.wallet.model.UserManager;
import com.achpay.wallet.model.http.response.BillListReponse;
import com.achpay.wallet.util.RxUtil;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by 95 on 2017/9/10.
 */

public class OrderDetailsPresenter extends RxPresenter<OrderDetailsContract.View> implements OrderDetailsContract.Presenter {

    UserManager userManager;
    DataManager dataManager;

    @Inject
    public OrderDetailsPresenter(DataManager dataManager1, UserManager userManager1) {
        dataManager = dataManager1;
        userManager = userManager1;

    }

    public void getDetails(String sysOrderNo){
        addSubscribe(dataManager.billDetails(sysOrderNo)
        .compose(RxUtil.unifySchedulerHelper())
        .subscribe(new Consumer<BillListReponse>() {
            @Override
            public void accept(BillListReponse detals) throws Exception {
                mView.onDetailsSeccuss(detals);

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                Log.e("skdf",throwable.getMessage());

            }
        }));

    }




}
