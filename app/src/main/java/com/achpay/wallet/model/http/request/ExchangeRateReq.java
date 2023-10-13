package com.achpay.wallet.model.http.request;



public class ExchangeRateReq {


    private String coinName;
    private String fiatName;


    public ExchangeRateReq(String coinName, String fiatName) {
        this.coinName = coinName;
        this.fiatName = fiatName;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public String getFiatName() {
        return fiatName;
    }

    public void setFiatName(String fiatName) {
        this.fiatName = fiatName;
    }
}
