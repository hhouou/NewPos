package com.achpay.wallet.model.http.response;


public class PayOrderReponse {

//{"payAcount":"0.00000348","payAcountUnit":"BTC","payAmount":"1","payAmountUnit":"CNY","payStatusStr":"pending","qrContent":"bc1q75s3yh7f8hp0p7shut47pc22tqx7auy79suvxy","qrcodeLink":"","remainingPaymentTime":"1799","sysOrderNum":"APP145034075752851456"}}
    private String sysOrderNum;
    private String qrcodeLink;
    private String qrContent;
    private Integer remainingPaymentTime;
    private String payAmount;
    private String payAmountUnit;
    private String payAcount;
    private String payAcountUnit;
    private Double fee;
    private String email;
    private String payStatusStr;
    private String shopifyCancelUrl;
    private String orderCtreateTime;
    private String webUrl;

    public String getSysOrderNum() {
        return sysOrderNum;
    }

    public void setSysOrderNum(String sysOrderNum) {
        this.sysOrderNum = sysOrderNum;
    }

    public String getQrcodeLink() {
        return qrcodeLink==null?"":qrcodeLink;
    }

    public void setQrcodeLink(String qrcodeLink) {
        this.qrcodeLink = qrcodeLink;
    }

    public String getQrContent() {
        return qrContent;
    }

    public void setQrContent(String qrContent) {
        this.qrContent = qrContent;
    }

    public Integer getRemainingPaymentTime() {
        return remainingPaymentTime;
    }

    public void setRemainingPaymentTime(Integer remainingPaymentTime) {
        this.remainingPaymentTime = remainingPaymentTime;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayAmountUnit() {
        return payAmountUnit;
    }

    public void setPayAmountUnit(String payAmountUnit) {
        this.payAmountUnit = payAmountUnit;
    }

    public String getPayAcount() {
        return payAcount;
    }

    public void setPayAcount(String payAcount) {
        this.payAcount = payAcount;
    }

    public String getPayAcountUnit() {
        return payAcountUnit;
    }

    public void setPayAcountUnit(String payAcountUnit) {
        this.payAcountUnit = payAcountUnit;
    }

    public Double getFee() {
        return fee;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPayStatusStr() {
        return payStatusStr;
    }

    public void setPayStatusStr(String payStatusStr) {
        this.payStatusStr = payStatusStr;
    }

    public String getShopifyCancelUrl() {
        return shopifyCancelUrl;
    }

    public void setShopifyCancelUrl(String shopifyCancelUrl) {
        this.shopifyCancelUrl = shopifyCancelUrl;
    }

    public String getOrderCtreateTime() {
        return orderCtreateTime;
    }

    public void setOrderCtreateTime(String orderCtreateTime) {
        this.orderCtreateTime = orderCtreateTime;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }
}
