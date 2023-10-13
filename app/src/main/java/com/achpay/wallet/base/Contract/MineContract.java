package com.achpay.wallet.base.Contract;

import com.achpay.wallet.base.BasePresenter;
import com.achpay.wallet.base.BaseView;
import com.achpay.wallet.model.http.response.AccountReponse;

/**
 * Created by 95 on 2017/9/10.
 */

public interface MineContract {
    interface View extends BaseView {

        void signOutSuceess();

        void accountSuceess(AccountReponse account);
    }

    interface Presenter extends BasePresenter<View> {

        void signOut();
        void account();
    }
}
