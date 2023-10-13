package com.achpay.wallet.base;

/**
 * Created by 95 on 2017/4/29.
 */

public interface BasePresenter <T extends BaseView> {
    void attachView(T view);
    void detachView();
}
