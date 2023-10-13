package com.achpay.wallet.presenter;


import androidx.annotation.NonNull;

import javax.inject.Inject;

import com.achpay.wallet.base.Contract.PaymentContract;
import com.achpay.wallet.base.RxPresenter;
import com.achpay.wallet.component.RxBus;
import com.achpay.wallet.model.DataManager;
import com.achpay.wallet.model.UserManager;
import com.achpay.wallet.model.event.StatusEvent;
import com.achpay.wallet.model.http.response.BillListReponse;
import com.achpay.wallet.util.RxUtil;

import io.reactivex.functions.Consumer;

/**
 * Created by 95 on 2017/9/11.
 */

public class PaymentPresenter extends RxPresenter<PaymentContract.View> implements PaymentContract.Presenter {

    private final UserManager userManager;
    private DataManager dataManager;


    @Inject
    public PaymentPresenter(DataManager dataManager1, UserManager userManager1) {
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
                       // mView.refreshStatus(event);
                    }
                }));
    }

    public void lastOneBill() {
        addSubscribe(dataManager.lastOneBill()
                .compose(RxUtil.unifySchedulerHelper())
                .subscribe(new Consumer<BillListReponse>() {
                    @Override
                    public void accept(BillListReponse o) throws Exception {
//                        mView.getDataSucess(o);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.showErrorMsg(throwable.toString());
                    }
                }));
    }


}
