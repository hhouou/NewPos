package com.achpay.wallet.base.Contract;

import com.achpay.wallet.base.BasePresenter;
import com.achpay.wallet.base.BaseView;
import com.achpay.wallet.model.event.StatusEvent;
import com.achpay.wallet.model.http.request.PayOrderReq;
import com.achpay.wallet.model.http.response.BillListReponse;
import com.achpay.wallet.model.http.response.PayOrderReponse;

/**
 * Created by 95 on 2017/9/10.
 */

public interface CoinReceiveContract {
    interface View extends BaseView {


        void payOrderSuceess(PayOrderReponse o);

        void refreshStatus(StatusEvent event);

        void orderStatusSucess(BillListReponse order);

    }

    interface Presenter extends BasePresenter<View> {

        void payOrder(PayOrderReq pay);
        void orderStatus(String sysOrderNum);
    }
}
