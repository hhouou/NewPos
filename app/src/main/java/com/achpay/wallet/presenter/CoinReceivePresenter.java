package com.achpay.wallet.presenter;

import androidx.annotation.NonNull;

import com.achpay.wallet.base.Contract.CoinReceiveContract;
import com.achpay.wallet.base.RxPresenter;
import com.achpay.wallet.component.RxBus;
import com.achpay.wallet.model.DataManager;
import com.achpay.wallet.model.UserManager;
import com.achpay.wallet.model.event.StatusEvent;
import com.achpay.wallet.model.http.request.OrderStatusReq;
import com.achpay.wallet.model.http.request.PayOrderReq;
import com.achpay.wallet.model.http.response.BillListReponse;
import com.achpay.wallet.model.http.response.PayOrderReponse;
import com.achpay.wallet.util.RxUtil;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by 95 on 2017/9/10.
 */

public class CoinReceivePresenter extends RxPresenter<CoinReceiveContract.View> implements CoinReceiveContract.Presenter {

    UserManager userManager;
    DataManager dataManager;

    @Inject
    public CoinReceivePresenter(DataManager dataManager1, UserManager userManager1) {
        dataManager = dataManager1;
        userManager = userManager1;

        refreshEvent();
    }

    private void refreshEvent() {
        addSubscribe(RxBus.getDefault().toFlowable(StatusEvent.class)
                .compose(RxUtil.<StatusEvent>rxSchedulerHelper())
                .subscribe(new Consumer<StatusEvent>() {
                    @Override
                    public void accept(@NonNull StatusEvent event) throws Exception {
                        mView.refreshStatus(event);
                    }
                }));
    }


    @Override
    public void payOrder(PayOrderReq pay) {
        mView.stateLoading();
        addSubscribe(dataManager.payOrder(pay)
                .compose(RxUtil.unifySchedulerHelper())
                .subscribe(new Consumer<PayOrderReponse>() {
                    @Override
                    public void accept(PayOrderReponse o) throws Exception {
                        mView.stateStop();
                        mView.payOrderSuceess(o);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.stateStop();
                        mView.showErrorMsg(throwable.getMessage());
                    }
                }));
    }
    @Override
    public void orderStatus(String sysOrderNum) {
        addSubscribe(dataManager.orderStatus(new OrderStatusReq(sysOrderNum))
                .compose(RxUtil.unifySchedulerHelper())
                .subscribe(new Consumer<BillListReponse>() {
                    @Override
                    public void accept(BillListReponse o) throws Exception {
                        mView.orderStatusSucess(o);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                }));
    }


}
