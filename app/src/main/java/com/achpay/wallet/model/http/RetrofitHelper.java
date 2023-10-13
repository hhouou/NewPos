package com.achpay.wallet.model.http;



import javax.inject.Inject;
import com.achpay.wallet.model.http.api.PosApi;
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

import java.util.List;

import io.reactivex.Flowable;

/**
 * Created by 95 on 2017/4/30.
 */

public class RetrofitHelper implements HttpHelper {

    private PosApi mApi;

    @Inject
    public RetrofitHelper(PosApi djApi) {
        mApi = djApi;
    }


    @Override
    public Flowable<Package<UserReponse>> login(LoginReq body) {
        return mApi.login(body);
    }

    @Override
    public Flowable<Package> logout() {
        return mApi.logout();
    }

    @Override
    public Flowable<Package<List<CoinListReponse>>> coinList() {
        return mApi.coinList();
    }

    @Override
    public Flowable<Package<BillListReponse>> lastOneBill() {
        return mApi.lastOneBill();
    }

    @Override
    public Flowable<Package<List<FiatListReponse>>> fiatList() {
        return mApi.fiatList();
    }

    @Override
    public Flowable<Package<Object>> fiatEdit(FiatEditReq body) {
        return mApi.fiatEdit(body);
    }

    @Override
    public Flowable<Package<PayOrderReponse>> payOrder(PayOrderReq body) {
        return mApi.payOrder(body);
    }

    @Override
    public Flowable<Package<AccountReponse>> account(String merchantCode) {
        return mApi.account(merchantCode);
    }

    @Override
    public Flowable<Package<List<BillListReponse>>> billList(BillListReq body) {
        return mApi.billList(body);
    }

    @Override
    public Flowable<Package<BillListReponse>> billDetails(String sysOrderNo) {
        return mApi.billDetails(sysOrderNo);
    }

    @Override
    public Flowable<Package<BillListReponse>> orderStatus(OrderStatusReq body) {
        return mApi.orderStatus(body);
    }

    @Override
    public Flowable<Package<String>> exchangeRate(ExchangeRateReq body) {
        return mApi.exchangeRate(body);
    }

    @Override
    public Flowable<Package<List<FiatListReponse>>> onFiatPostList() {
        return mApi.onFiatPostList();
    }

    @Override
    public Flowable<Package<List<CoinListReponse>>> onCoinPostList() {
        return mApi.onCoinPostList();
    }

    @Override
    public Flowable<Package<TradeSummaryReponse>> onTradeSummary(TradeSummaryReq body) {
        return mApi.onTradeSummary(body);
    }
}
