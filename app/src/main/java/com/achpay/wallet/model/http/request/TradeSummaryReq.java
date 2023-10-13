package com.achpay.wallet.model.http.request;

public class TradeSummaryReq {

    private String startTime;
    private String endTime;

    public TradeSummaryReq(String startTime,String endTime){
        this.startTime = startTime;
        this.endTime = endTime;

    }
}
