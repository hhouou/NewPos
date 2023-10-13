package com.achpay.wallet.base;

/**
 * Created by 95 on 2017/4/29.
 */

public interface BaseView {
    void showErrorMsg(String msg);


    void stateLoading();

    void stateError();

    void stateStop();

    void stateMain();

    void userNightMode(boolean isNight);


}
