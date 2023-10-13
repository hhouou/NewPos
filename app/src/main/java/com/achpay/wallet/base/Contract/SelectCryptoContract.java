package com.achpay.wallet.base.Contract;

import com.achpay.wallet.base.BasePresenter;
import com.achpay.wallet.base.BaseView;
import com.achpay.wallet.model.http.response.CoinListReponse;

import java.util.List;

/**
 * Created by 95 on 2017/9/10.
 */

public interface SelectCryptoContract {
    interface View extends BaseView {

        void getListSuceess(List<CoinListReponse> o);
    }

    interface Presenter extends BasePresenter<View> {
    }
}
