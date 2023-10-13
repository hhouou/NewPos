package com.achpay.wallet.base.Contract;

import com.achpay.wallet.base.BasePresenter;
import com.achpay.wallet.base.BaseView;

/**
 * Created by 95 on 2017/9/11.
 */

public interface WebContentContract {
    interface View extends BaseView{

    }

    interface Presenter extends BasePresenter<View>{
        String getPhone();
    }
}
