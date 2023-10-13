package com.achpay.wallet.base.Contract;

import com.achpay.wallet.base.BasePresenter;
import com.achpay.wallet.base.BaseView;

/**
 * Created by 95 on 2017/9/10.
 */

public interface ExchangeRateContract {
    interface View extends BaseView {

        void onExchangeRate(String rate);
    }

    interface Presenter extends BasePresenter<View> {
    }
}
