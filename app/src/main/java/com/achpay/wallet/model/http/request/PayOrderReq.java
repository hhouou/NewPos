package com.achpay.wallet.model.http.request;


public class PayOrderReq {


    private String fiatType;
    private String fiatAmount;
    private String coinType;
    private String merchantCode;
    private String solReference;


    //evv
    private String cardCcv;
    //B
    private String cardExpMonth;
    //年
    private String cardExpYear;
    //卡号
    private String cardNo;
    //邮箱
    private String email;
    private String firstName;
    private String lastName;

    public PayOrderReq( String fiatType, String fiatAmount, String coinType, String merchantCode,
            String firstName,String lastName, String email, String cardNo, String cardExpYear, String cardExpMonth,String cardCcv) {
        this.fiatType = fiatType;
        this.fiatAmount = fiatAmount;
        this.coinType = coinType;
        this.merchantCode = merchantCode;

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.cardNo = cardNo;
        this.cardExpYear = cardExpYear;
        this.cardExpMonth = cardExpMonth;
        this.cardCcv = cardCcv;
    }

    public PayOrderReq(String fiatType, String fiatAmount, String coinType, String merchantCode, String solReference) {
        this.fiatType = fiatType;
        this.fiatAmount = fiatAmount;
        this.coinType = coinType;
        this.merchantCode = merchantCode;
        this.solReference = solReference;
    }

    public PayOrderReq(String fiatType, String fiatAmount, String coinType, String merchantCode) {
        this.fiatType = fiatType;
        this.fiatAmount = fiatAmount;
        this.coinType = coinType;
        this.merchantCode = merchantCode;
    }

    public String getFiatType() {
        return fiatType;
    }

    public void setFiatType(String fiatType) {
        this.fiatType = fiatType;
    }

    public String getFiatAmount() {
        return fiatAmount;
    }

    public void setFiatAmount(String fiatAmount) {
        this.fiatAmount = fiatAmount;
    }

    public String getCoinType() {
        return coinType;
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getSolReference() {
        return solReference;
    }

    public void setSolReference(String solReference) {
        this.solReference = solReference;
    }


    public String getCardCcv() {
        return cardCcv;
    }

    public void setCardCcv(String cardCcv) {
        this.cardCcv = cardCcv;
    }

    public String getCardExpMonth() {
        return cardExpMonth;
    }

    public void setCardExpMonth(String cardExpMonth) {
        this.cardExpMonth = cardExpMonth;
    }

    public String getCardExpYear() {
        return cardExpYear;
    }

    public void setCardExpYear(String cardExpYear) {
        this.cardExpYear = cardExpYear;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
