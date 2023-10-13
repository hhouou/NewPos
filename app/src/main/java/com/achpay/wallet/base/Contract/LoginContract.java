package com.achpay.wallet.base.Contract;

import com.achpay.wallet.base.BasePresenter;
import com.achpay.wallet.base.BaseView;

/**
 * Created by 95 on 2017/9/10.
 */

public interface LoginContract {
    interface View extends BaseView {


        void loginSuccess();
    }

    interface Presenter extends BasePresenter<View> {

        void login(String username, String password);
    }
}
