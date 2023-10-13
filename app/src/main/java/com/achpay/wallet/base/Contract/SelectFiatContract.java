package com.achpay.wallet.base.Contract;

import com.achpay.wallet.base.BasePresenter;
import com.achpay.wallet.base.BaseView;
import com.achpay.wallet.model.http.response.FiatListReponse;

import java.util.List;

/**
 * Created by 95 on 2017/9/10.
 */

public interface SelectFiatContract {
    interface View extends BaseView {

        void onFiatlist(List<FiatListReponse> list);
    }

    interface Presenter extends BasePresenter<View> {
    }
}
