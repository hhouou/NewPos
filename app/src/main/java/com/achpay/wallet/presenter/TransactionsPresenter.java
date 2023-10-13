package com.achpay.wallet.presenter;

import androidx.annotation.NonNull;

import com.achpay.wallet.base.Contract.TransactionsContract;
import com.achpay.wallet.base.RxPresenter;
import com.achpay.wallet.component.RxBus;
import com.achpay.wallet.model.DataManager;
import com.achpay.wallet.model.event.StatusEvent;
import com.achpay.wallet.model.http.request.BillListReq;
import com.achpay.wallet.model.http.response.BillListReponse;
import com.achpay.wallet.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2017/9/14 0014.
 */

public class TransactionsPresenter extends RxPresenter<TransactionsContract.View> implements TransactionsContract.Presenter {
    DataManager dataManager;

    @Inject
    public TransactionsPresenter(DataManager dataManager1) {
        dataManager = dataManager1;
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
    public void billList(BillListReq body) {
        mView.stateLoading();
        addSubscribe(dataManager.billList(body)
                .compose(RxUtil.unifySchedulerHelper())
                .subscribe(new Consumer<List<BillListReponse>>() {
                    @Override
                    public void accept(List<BillListReponse> o) throws Exception {
                        mView.stateStop();
                        mView.getBillListSuceess(o);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.stateStop();
                        mView.stateError();

                    }
                }));
    }


}
