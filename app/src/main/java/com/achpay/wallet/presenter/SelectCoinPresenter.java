package com.achpay.wallet.presenter;

import com.achpay.wallet.R;
import com.achpay.wallet.app.App;
import com.achpay.wallet.base.Contract.SelectCoinContract;
import com.achpay.wallet.base.RxPresenter;
import com.achpay.wallet.model.DataManager;
import com.achpay.wallet.model.UserManager;
import com.achpay.wallet.model.http.response.CoinListReponse;
import com.achpay.wallet.util.RxUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

/**
 * Created by 95 on 2021/10/13.
 */

public class SelectCoinPresenter extends RxPresenter<SelectCoinContract.View> implements SelectCoinContract.Presenter {

    UserManager userManager;
    DataManager dataManager;

    @Inject
    public SelectCoinPresenter(DataManager dataManager1, UserManager userManager1) {
        dataManager = dataManager1;
        userManager = userManager1;

    }

    public void getCoinList() {
        mView.stateLoading();
        addSubscribe(dataManager.coinList()
                .compose(RxUtil.unifySchedulerHelper())
                .subscribe(new Consumer<List<CoinListReponse>>() {
                    @Override
                    public void accept(List<CoinListReponse> o) throws Exception {
                        mView.stateStop();
                        List<CoinListReponse> walletList = new ArrayList<>();
                        List<CoinListReponse> fiatList = new ArrayList<>();
                        List<CoinListReponse> argentinaList = new ArrayList<>();
                        List<CoinListReponse> ethList = new ArrayList<>();
                        for (CoinListReponse coin : o) {
//                            if (coin.getCurrencyCode().equals("BTC") || coin.getCurrencyCode().equals("Solana Pay")) {
//                                walletList.add(coin);
////                            } else if (coin.getCurrencyCode().equals("Credit Card")) {
////                                fiatList.add(coin);
////                            }else if (coin.getCurrencyCode().equals("khipu")){
////                                argentinaList.add(coin);
//                            }else {
//                                ethList.add(coin);
//                            }
                            if (!coin.getPayType().contains("c_")){
                                walletList.add(coin);
                            }else if (coin.getPayType().equals("c_SOL")){
                                walletList.add(coin);
                            } else {
                                ethList.add(coin);
                            }
                        }

                        mView.getListSuceess(walletList, fiatList,argentinaList, ethList);

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        mView.stateStop();
                        mView.showErrorMsg(App.getInstance().getString(R.string.error_pay_failed));
                    }
                }));
    }


}
