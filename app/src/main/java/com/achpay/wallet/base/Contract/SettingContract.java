package com.achpay.wallet.base.Contract;

import com.achpay.wallet.base.BasePresenter;
import com.achpay.wallet.base.BaseView;
import com.achpay.wallet.model.http.response.FiatListReponse;

import java.util.List;

/**
 * Created by Administrator on 2017/9/18 0018.
 */

public interface SettingContract {
    interface View extends BaseView {
        void getFiatListSeccess(List<FiatListReponse> o);

        void fiatEditSeccess(Object o);
    }

    interface Presenter extends BasePresenter<SettingContract.View> {

        void fiatList();

        void fiatEdit(int baseCurrencyId);
    }
}
