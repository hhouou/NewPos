package com.achpay.wallet.model.http.request;



public class BillListReq {


    private int pageNum;
    private Integer pageSiz = 20;
    private String merchantCode;

    public BillListReq(int pageNum, String merchantCode) {
        this.pageNum = pageNum;
        this.merchantCode = merchantCode;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSiz() {
        return pageSiz;
    }

    public void setPageSiz(Integer pageSiz) {
        this.pageSiz = pageSiz;
    }

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }
}
