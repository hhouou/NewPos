package com.achpay.wallet.base.Contract;

import com.achpay.wallet.base.BasePresenter;
import com.achpay.wallet.base.BaseView;

/**
 * Created by Administrator on 2017/9/18 0018.
 */

public interface BaseContract {
    interface View extends BaseView {
    }

    interface Presenter extends BasePresenter<BaseContract.View> {

    }
}
