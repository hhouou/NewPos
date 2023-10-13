package com.achpay.wallet.base.Contract;

import com.achpay.wallet.base.BasePresenter;
import com.achpay.wallet.base.BaseView;
import com.achpay.wallet.model.event.StatusEvent;
import com.achpay.wallet.model.http.response.BillListReponse;

/**
 * Created by 95 on 2017/9/11.
 */

public interface PaymentContract {
    interface View extends BaseView {

//        void getDataSucess(BillListReponse o);

    }

    interface Presenter extends BasePresenter<View> {
    }
}
