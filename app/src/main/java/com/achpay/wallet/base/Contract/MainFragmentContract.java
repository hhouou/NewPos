package com.achpay.wallet.base.Contract;

import com.achpay.wallet.base.BasePresenter;
import com.achpay.wallet.base.BaseView;

/**
 * Created by 95 on 2017/9/17.
 */

public interface MainFragmentContract {

    interface View extends BaseView{

    }

    interface Presenter extends BasePresenter<View>{
    }
}
