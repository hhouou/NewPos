package com.achpay.wallet.base.Contract;

import com.achpay.wallet.base.BasePresenter;
import com.achpay.wallet.base.BaseView;
import com.achpay.wallet.model.http.response.BillListReponse;

/**
 * Created by 95 on 2017/9/10.
 */

public interface OrderDetailsContract {
    interface View extends BaseView {


        void onDetailsSeccuss(BillListReponse detals);
    }

    interface Presenter extends BasePresenter<View> {

    }
}
