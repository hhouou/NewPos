package com.achpay.wallet.model.http.response;


public class FiatListReponse {

    private Integer id;
    private String currencyCode;
    private String currencyEnCode;
    private String currencyDesc;
    private Integer isAvailable;
    private String unit;
    private String symbol;
    private String imageAddress;
    private int defaults;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getCurrencyEnCode() {
        return currencyEnCode;
    }

    public void setCurrencyEnCode(String currencyEnCode) {
        this.currencyEnCode = currencyEnCode;
    }

    public String getCurrencyDesc() {
        return currencyDesc;
    }

    public void setCurrencyDesc(String currencyDesc) {
        this.currencyDesc = currencyDesc;
    }

    public Integer getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Integer isAvailable) {
        this.isAvailable = isAvailable;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public int getDefaults() {
        return defaults;
    }

    public void setDefaults(int defaults) {
        this.defaults = defaults;
    }
}
