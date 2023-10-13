package com.achpay.wallet.model.http.response;


import android.content.Context;

import com.achpay.wallet.R;
import com.achpay.wallet.util.TimeUtils;

public class BillListReponse {


    private String coboRealCount;
    private String coinFiatRate;
    private String createName;
    private String currencyAmount;
    private String currencyCode;
    private String currencyType;
    private String digitalCurrencyCode;
    private String exchangeRate;
    private String fee;
    private String icon;
    private String id;
    private String merchantCode;
    private String merchantFee;
    private int payStatus;
    private String payStatusStr;
    private String realCount;
    private String shouldCount;
    private String sysOrderNum;
    private String toAddress;
    private String token;
    private long updateTime;
    private long payTime;
    private String usdCoinRate;
    private String usdtCount;
    private String version;
    private String paymentMethod;

    private String tradeHash;
    private String fromAddress;
    private long remainingPaymentTime; //剩余支付时间（倒计时多少秒）

    private long createTime;
    private String digitalCurrencyAddress;


    public String getCoboRealCount() {
        return coboRealCount;
    }

    public void setCoboRealCount(String coboRealCount) {
        this.coboRealCount = coboRealCount;
    }

    public String getCoinFiatRate() {
        return coinFiatRate;
    }

    public void setCoinFiatRate(String coinFiatRate) {
        this.coinFiatRate = coinFiatRate;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public String getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCurrencyAmount(String currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public String getDigitalCurrencyCode() {
        return digitalCurrencyCode;
    }

    public void setDigitalCurrencyCode(String digitalCurrencyCode) {
        this.digitalCurrencyCode = digitalCurrencyCode;
    }

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getMerchantFee() {
        return merchantFee;
    }

    public void setMerchantFee(String merchantFee) {
        this.merchantFee = merchantFee;
    }

    public int getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(int payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayStatusStr() {
        return payStatusStr;
    }

    public void setPayStatusStr(String payStatusStr) {
        this.payStatusStr = payStatusStr;
    }

    public String getRealCount() {
        return realCount;
    }

    public void setRealCount(String realCount) {
        this.realCount = realCount;
    }

    public String getShouldCount() {
        return shouldCount;
    }

    public void setShouldCount(String shouldCount) {
        this.shouldCount = shouldCount;
    }

    public String getSysOrderNum() {
        return sysOrderNum;
    }

    public void setSysOrderNum(String sysOrderNum) {
        this.sysOrderNum = sysOrderNum;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUpdateTime() {
        return TimeUtils.formatUTC(updateTime,null);
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getPayTime() {
        return TimeUtils.formatUTC(payTime,null);
    }

    public void setPayTime(long payTime) {
        this.payTime = payTime;
    }

    public String getUsdCoinRate() {
        return usdCoinRate;
    }

    public void setUsdCoinRate(String usdCoinRate) {
        this.usdCoinRate = usdCoinRate;
    }

    public String getUsdtCount() {
        return usdtCount;
    }

    public void setUsdtCount(String usdtCount) {
        this.usdtCount = usdtCount;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getTradeHash() {
        return tradeHash;
    }

    public void setTradeHash(String tradeHash) {
        this.tradeHash = tradeHash;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public long getRemainingPaymentTime() {
        return remainingPaymentTime;
    }

    public void setRemainingPaymentTime(long remainingPaymentTime) {
        this.remainingPaymentTime = remainingPaymentTime;
    }

    public String getCreateTime() {
        return TimeUtils.formatUTC(createTime,null);
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getDigitalCurrencyAddress() {
        return digitalCurrencyAddress;
    }

    public void setDigitalCurrencyAddress(String digitalCurrencyAddress) {
        this.digitalCurrencyAddress = digitalCurrencyAddress;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPayStatusStr(Context context) {
        String status = context.getString(R.string.pay_status_pending);
        switch (payStatus) {
            case 0:
                status = context.getString(R.string.pay_status_pending);
                break;
            case 1:
                status = context.getString(R.string.pay_status_payment_success);
                break;
            case 2:
                status = context.getString(R.string.pay_status_payment_success);
                break;
            case 3:
                status = context.getString(R.string.pay_status_failed);
                break;
            case 4:
                status = context.getString(R.string.pay_status_time_out);
                break;
            case 5:
                status = context.getString(R.string.pay_status_refund_success);
                break;
        }
        return status;
    }



    public  int getPayStatusColor() {
        int color = R.color.color_green_07C06D;
        switch (payStatus) {
            case 0:
                color = R.color.color_yellow_FFCA1A;
                break;
            case 1:
            case 2:
                color = R.color.color_green_07C06D;
                break;
            case 3:
            case 4:
            case 5:
                color = R.color.color_red_FF4242;
                break;
        }
        return color;
    }

    public  int getPayStatusDrawable() {
        int color = R.drawable.bg_circle_green_07dc060;
        switch (payStatus) {
            case 0:
                color = R.drawable.bg_circle_yellow_ffca1a;
                break;
            case 1:
            case 2:
                color = R.drawable.bg_circle_green_07dc060;
                break;
            case 3:
            case 4:
            case 5:
                color = R.drawable.bg_circle_red;
                break;
        }
        return color;
    }

}
