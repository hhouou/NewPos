package com.achpay.wallet.presenter;

import android.util.Log;

import com.achpay.wallet.base.Contract.MainContract;
import com.achpay.wallet.base.Contract.TransactionSummaryContract;
import com.achpay.wallet.base.RxPresenter;
import com.achpay.wallet.model.DataManager;
import com.achpay.wallet.model.UserManager;
import com.achpay.wallet.model.http.request.TradeSummaryReq;
import com.achpay.wallet.model.http.response.BillListReponse;
import com.achpay.wallet.model.http.response.TradeSummaryReponse;
import com.achpay.wallet.util.RxUtil;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by 95 on 2017/9/10.
 */

public class TransactionSummaryPresenter extends RxPresenter<TransactionSummaryContract.View> implements TransactionSummaryContract.Presenter {

    UserManager userManager;
    DataManager dataManager;

    @Inject
    public TransactionSummaryPresenter(DataManager dataManager1, UserManager userManager1) {
        dataManager = dataManager1;
        userManager = userManager1;

    }




    public void onTradeSummary(TradeSummaryReq req){
        addSubscribe(dataManager.onTradeSummary(req)
                .compose(RxUtil.unifySchedulerHelper())
                .subscribe(new Consumer<TradeSummaryReponse>() {
                    @Override
                    public void accept(TradeSummaryReponse reponse) throws Exception {
                        mView.onSummarySeccuss(reponse);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e("skdf",throwable.getMessage());

                    }
                }));

    }


}
