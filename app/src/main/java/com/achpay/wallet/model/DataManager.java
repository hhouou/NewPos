package com.achpay.wallet.model;

import com.achpay.wallet.model.database.DBHelper;
import com.achpay.wallet.model.http.HttpHelper;
import com.achpay.wallet.model.database.bean.User;
import com.achpay.wallet.model.http.request.BillListReq;
import com.achpay.wallet.model.http.request.ExchangeRateReq;
import com.achpay.wallet.model.http.request.FiatEditReq;
import com.achpay.wallet.model.http.request.LoginReq;
import com.achpay.wallet.model.http.request.OrderStatusReq;
import com.achpay.wallet.model.http.request.PayOrderReq;
import com.achpay.wallet.model.http.request.TradeSummaryReq;
import com.achpay.wallet.model.http.response.AccountReponse;
import com.achpay.wallet.model.http.response.BillListReponse;
import com.achpay.wallet.model.http.response.CoinListReponse;
import com.achpay.wallet.model.http.response.FiatListReponse;
import com.achpay.wallet.model.http.response.Package;
import com.achpay.wallet.model.http.response.PayOrderReponse;
import com.achpay.wallet.model.http.response.TradeSummaryReponse;
import com.achpay.wallet.model.http.response.UserReponse;
import com.achpay.wallet.model.prefs.PreferencesHelper;

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by 95 on 2017/4/30.
 */

public class DataManager implements HttpHelper, PreferencesHelper, DBHelper
{
    HttpHelper mHttpHelper;
    PreferencesHelper mPreferencesHelper;
    DBHelper mDBHelper;


    public DataManager(HttpHelper helper, PreferencesHelper preferencesHelper, DBHelper dbHelper) {
        mHttpHelper = helper;
        mPreferencesHelper = preferencesHelper;
        mDBHelper = dbHelper;
    }


    @Override
    public void setDefaultUser(com.achpay.wallet.model.database.bean.User user) {
        mDBHelper.setDefaultUser(user);
    }

    @Override
    public void deleteUser() {
        mDBHelper.deleteUser();
    }



    @Override
    public void updateUser(com.achpay.wallet.model.database.bean.User user) {
        mDBHelper.updateUser(user);
    }

    @Override
    public User getDefaultUser() {
        return mDBHelper.getDefaultUser();
    }

    @Override
    public String getUserReplyContent() {
        return mPreferencesHelper.getUserReplyContent();
    }



    @Override
    public Flowable<Package<UserReponse>> login(LoginReq body) {
        return mHttpHelper.login(body);
    }

    @Override
    public Flowable<Package> logout() {
        return mHttpHelper.logout();
    }

    @Override
    public Flowable<Package<List<CoinListReponse>>> coinList() {
        return mHttpHelper.coinList();
    }

    @Override
    public Flowable<Package<BillListReponse>> lastOneBill() {
        return mHttpHelper.lastOneBill();
    }

    @Override
    public Flowable<Package<List<FiatListReponse>>> fiatList() {
        return mHttpHelper.fiatList();
    }

    @Override
    public Flowable<Package<Object>> fiatEdit(FiatEditReq body) {
        return mHttpHelper.fiatEdit(body);
    }

    @Override
    public Flowable<Package<PayOrderReponse>> payOrder(PayOrderReq body) {
        return mHttpHelper.payOrder(body);
    }

    @Override
    public Flowable<Package<AccountReponse>> account(String merchantCode) {
        return mHttpHelper.account(merchantCode);
    }

    @Override
    public Flowable<Package<List<BillListReponse>>> billList(BillListReq body) {
        return mHttpHelper.billList(body);
    }

    @Override
    public Flowable<Package<BillListReponse>> billDetails(String sysOrderNo) {
        return mHttpHelper.billDetails(sysOrderNo);
    }

    @Override
    public Flowable<Package<BillListReponse>> orderStatus(OrderStatusReq body) {
        return mHttpHelper.orderStatus(body);
    }

    @Override
    public Flowable<Package<String>> exchangeRate(ExchangeRateReq body) {
        return mHttpHelper.exchangeRate(body);
    }

    @Override
    public Flowable<Package<List<FiatListReponse>>> onFiatPostList() {
        return mHttpHelper.onFiatPostList();
    }

    @Override
    public Flowable<Package<List<CoinListReponse>>> onCoinPostList() {
        return mHttpHelper.onCoinPostList();
    }

    @Override
    public Flowable<Package<TradeSummaryReponse>> onTradeSummary(TradeSummaryReq body) {
        return mHttpHelper.onTradeSummary(body);
    }
}
