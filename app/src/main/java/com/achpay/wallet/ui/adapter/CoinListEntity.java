package com.achpay.wallet.ui.adapter;

import com.achpay.wallet.model.http.response.CoinListReponse;
import com.chad.library.adapter.base.entity.MultiItemEntity;

public class CoinListEntity implements MultiItemEntity {

    public static final int TYPE_TITLE = 1;
    public static final int TYPE_COMMENT = 2;

    private String title;
    private CoinListReponse coinListReponse;
    private int itemType;


    public CoinListEntity(String title){
        this.itemType = 1;
        this.title = title;

    }
    public CoinListEntity(CoinListReponse coinListReponse){
        this.itemType = 2;
        this.coinListReponse = coinListReponse;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CoinListReponse getCoinListReponse() {
        return coinListReponse;
    }

    public void setCoinListReponse(CoinListReponse coinListReponse) {
        this.coinListReponse = coinListReponse;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
