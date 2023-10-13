package com.achpay.wallet.model.http.response;

import java.util.List;

public class TradeSummaryReponse  {


    private String totalAmount;
    private String payTotal;
    private List<ListBean> list;

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPayTotal() {
        return payTotal;
    }

    public void setPayTotal(String payTotal) {
        this.payTotal = payTotal;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

  public static class ListBean {
        private String coinCode;
        private String coinPayCount;
        private String fiatCode;
        private String fiatPayAmount;

        public String getCoinCode() {
            return coinCode;
        }

        public void setCoinCode(String coinCode) {
            this.coinCode = coinCode;
        }

        public String getCoinPayCount() {
            return coinPayCount;
        }

        public void setCoinPayCount(String coinPayCount) {
            this.coinPayCount = coinPayCount;
        }

        public String getFiatCode() {
            return fiatCode;
        }

        public void setFiatCode(String fiatCode) {
            this.fiatCode = fiatCode;
        }

        public String getFiatPayAmount() {
            return fiatPayAmount;
        }

        public void setFiatPayAmount(String fiatPayAmount) {
            this.fiatPayAmount = fiatPayAmount;
        }
    }
}
