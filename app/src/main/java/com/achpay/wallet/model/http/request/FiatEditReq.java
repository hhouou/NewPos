package com.achpay.wallet.model.http.request;



public class FiatEditReq {


    private Integer baseCurrencyId;
    private String merchantCode;

    public FiatEditReq(int baseCurrencyId, String merchantCode) {
        this.baseCurrencyId = baseCurrencyId;
        this.merchantCode = merchantCode;
    }


}
