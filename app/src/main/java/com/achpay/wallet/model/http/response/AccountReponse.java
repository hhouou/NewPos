package com.achpay.wallet.model.http.response;

public class AccountReponse {

    private String usableAmountStr;
    private String waitAmountStr;

    public String getUsableAmountStr() {
        return usableAmountStr;
    }

    public void setUsableAmountStr(String usableAmountStr) {
        this.usableAmountStr = usableAmountStr;
    }

    public String getWaitAmountStr() {
        return waitAmountStr;
    }

    public void setWaitAmountStr(String waitAmountStr) {
        this.waitAmountStr = waitAmountStr;
    }
}
