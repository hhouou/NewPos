package com.achpay.wallet.model.http.response;


public class CoinListReponse {


    private String currencyCode;
    private String currencyFullName;
    private String imageAddress;
    private String payType;

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyFullName() {
        return currencyFullName;
    }

    public void setCurrencyFullName(String currencyFullName) {
        this.currencyFullName = currencyFullName;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
