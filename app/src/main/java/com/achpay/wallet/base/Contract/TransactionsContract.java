package com.achpay.wallet.base.Contract;


import com.achpay.wallet.base.BasePresenter;
import com.achpay.wallet.base.BaseView;
import com.achpay.wallet.model.event.StatusEvent;
import com.achpay.wallet.model.http.request.BillListReq;
import com.achpay.wallet.model.http.response.BillListReponse;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14 0014.
 */

public interface TransactionsContract {
    interface View extends BaseView {

        void getBillListSuceess(List<BillListReponse> o);

        void refreshStatus(StatusEvent event);
    }

    interface Presenter extends BasePresenter<TransactionsContract.View> {
        void billList(BillListReq body);
    }
}
