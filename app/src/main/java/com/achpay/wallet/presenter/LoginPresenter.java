package com.achpay.wallet.presenter;

import com.achpay.wallet.app.App;
import com.achpay.wallet.base.Contract.LoginContract;
import com.achpay.wallet.base.RxPresenter;
import com.achpay.wallet.model.DataManager;
import com.achpay.wallet.model.UserManager;
import com.achpay.wallet.model.http.request.LoginReq;
import com.achpay.wallet.model.http.response.Package;
import com.achpay.wallet.model.http.response.UserReponse;
import com.achpay.wallet.util.RxUtil;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by 95 on 2017/9/10.
 */

public class LoginPresenter extends RxPresenter<LoginContract.View> implements LoginContract.Presenter {

    UserManager userManager;
    DataManager dataManager;

    @Inject
    public LoginPresenter(DataManager dataManager1, UserManager userManager1) {
        dataManager = dataManager1;
        userManager = userManager1;

    }

    @Override
    public void login(String username, String password) {
        mView.stateLoading();
        addSubscribe(dataManager.login(new LoginReq(username,password))
                .compose(RxUtil.unifySchedulerHelper())
                .subscribe(new Consumer<UserReponse>() {
                    @Override
                    public void accept(UserReponse data) throws Exception {
                        mView.stateStop();
                        App.setToken(data.getToken());
                        App.setMerchantCode(data.getMerchantCode());
                        App.setMerchantName(data.getMerchantName());
                        App.setCurrencyCode(data.getCurrencyCode());

                        mView.loginSuccess();

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.stateStop();
                        mView.showErrorMsg(throwable.getMessage());
                    }
                }));
    }


}
