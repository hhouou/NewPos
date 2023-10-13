package com.achpay.wallet.model.http;

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
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 95 on 2017/4/30.
 */

public interface HttpHelper {


    Flowable<Package<UserReponse>>login(LoginReq body);

    Flowable<Package> logout();
    Flowable<Package<List<CoinListReponse>>> coinList();

    Flowable<Package<BillListReponse>> lastOneBill();

    Flowable<Package<List<FiatListReponse>>> fiatList();

    Flowable<Package<Object>> fiatEdit(FiatEditReq body);


    Flowable<Package<PayOrderReponse>> payOrder(PayOrderReq body);

    Flowable<Package<AccountReponse>> account(String merchantCode);

    Flowable<Package<List<BillListReponse>>> billList(BillListReq body);

    Flowable<Package<BillListReponse>> billDetails(String sysOrderNo);


    Flowable<Package<BillListReponse>> orderStatus(OrderStatusReq body);

    Flowable<Package<String>> exchangeRate(ExchangeRateReq body);

    Flowable<Package<List<FiatListReponse>>> onFiatPostList();

    Flowable<Package<List<CoinListReponse>>> onCoinPostList();

    Flowable<Package<TradeSummaryReponse>> onTradeSummary(TradeSummaryReq body);


}
