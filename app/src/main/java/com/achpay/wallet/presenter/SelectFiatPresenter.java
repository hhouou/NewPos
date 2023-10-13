package com.achpay.wallet.presenter;

import android.text.TextUtils;

import com.achpay.wallet.app.App;
import com.achpay.wallet.base.Contract.SelectCryptoContract;
import com.achpay.wallet.base.Contract.SelectFiatContract;
import com.achpay.wallet.base.RxPresenter;
import com.achpay.wallet.model.DataManager;
import com.achpay.wallet.model.UserManager;
import com.achpay.wallet.model.http.response.FiatListReponse;
import com.achpay.wallet.util.RxUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by 95 on 2017/9/10.
 */

public class SelectFiatPresenter extends RxPresenter<SelectFiatContract.View> implements SelectFiatContract.Presenter {

    UserManager userManager;
    DataManager dataManager;

    @Inject
    public SelectFiatPresenter(DataManager dataManager1, UserManager userManager1) {
        dataManager = dataManager1;
        userManager = userManager1;

    }


    public void fiatList(){
        addSubscribe(dataManager.onFiatPostList()
                .compose(RxUtil.unifySchedulerHelper())
                .subscribe(new Consumer<List<FiatListReponse>>() {
                    @Override
                    public void accept(List<FiatListReponse> list) throws Exception {

//                        List<FiatListReponse>newList = new ArrayList<>();
//                        for (FiatListReponse fiat:list){
//                            if (!TextUtils.equals("ETH",fiat.getCurrencyCode())){
//                         /               newList.add(fiat);
//                            }
//                        }
                        mView.onFiatlist(list);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                    }
                }));
    }


}
