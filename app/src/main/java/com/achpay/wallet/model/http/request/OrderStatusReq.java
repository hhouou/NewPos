package com.achpay.wallet.model.http.request;


public class OrderStatusReq {

    private String sysOrderNum;

    public OrderStatusReq(String sysOrderNum) {
        this.sysOrderNum = sysOrderNum;
    }

    public String getSysOrderNum() {
        return sysOrderNum;
    }

    public void setSysOrderNum(String sysOrderNum) {
        this.sysOrderNum = sysOrderNum;
    }
}
