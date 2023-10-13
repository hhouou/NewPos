package com.achpay.wallet.model.http.api;


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
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by 95 on 2021/10/14.
 */

public interface PosApi {


    /**
     * self-登录
     */
    @POST("app/login")
    Flowable<Package<UserReponse>> login(@Body LoginReq body);

    /**
     * self-退出
     */
    @POST("app/logout")
    Flowable<Package> logout();

    /**
     * 数字货币列表接口
     */
    @POST("app/currency/coin/list")
    Flowable<Package<List<CoinListReponse>>> coinList();





    /**
     * 最新的一条订单接口
     */
    @POST("app/bill/lastOne")
    Flowable<Package<BillListReponse>> lastOneBill();

    /**
     * 法币列表接口
     */
    @POST("app/currency/fiat/list")
    Flowable<Package<List<FiatListReponse>>> fiatList();



    /**
     * 默认法币设置接口
     */
    @POST("app/currency/fiat/edit")
    Flowable<Package<Object>> fiatEdit(@Body FiatEditReq body);

    /**
     * 下单接口
     */
    @POST("app/pay")
    Flowable<Package<PayOrderReponse>> payOrder(@Body PayOrderReq body);

    /**
     * 商户账户余额接口
     */
    @POST("app/account")
    Flowable<Package<AccountReponse>> account(@Query("merchantCode") String merchantCode);

    /**
     * 账单流水接口
     */
    @POST("app/bill/list")
    Flowable<Package<List<BillListReponse>>> billList(@Body BillListReq body);



    /**
     * 账单流水接口
     */
    @POST("app/bill/detail")
    @FormUrlEncoded
    Flowable<Package<BillListReponse>> billDetails(@Field("sysOrderNo") String sysOrderNo);

    /**
     * 根据订单号查询订单状态接口
     */
    @POST("app/bill/orderStatus")
    Flowable<Package<BillListReponse>> orderStatus(@Body OrderStatusReq body);


    /**
     * 汇率
     */
    @POST("app/currency/rate")
    Flowable<Package<String>> exchangeRate(@Body ExchangeRateReq body);


    /**
     * 法币列表接口
     */
    @POST("app/currency/pos/list")
    Flowable<Package<List<FiatListReponse>>> onFiatPostList();




    /**
     * 数字货币列表接口
     */
    @POST("app/currency/rate/coin/list")
    Flowable<Package<List<CoinListReponse>>> onCoinPostList();



    /**
     * 数字货币列表接口
     */
    @POST("app/trade/summary")
    Flowable<Package<TradeSummaryReponse>> onTradeSummary(@Body TradeSummaryReq body);

}