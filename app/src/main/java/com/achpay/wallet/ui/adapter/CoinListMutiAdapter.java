package com.achpay.wallet.ui.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import com.achpay.wallet.R;
import com.achpay.wallet.component.ImageLoader;
import com.achpay.wallet.model.http.response.CoinListReponse;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;


public class CoinListMutiAdapter extends BaseMultiItemQuickAdapter<CoinListEntity, BaseViewHolder> {

    public CoinListMutiAdapter(List list, Context context) {
        super(list);
        this.mContext = context;
        addItemType(CoinListEntity.TYPE_TITLE, R.layout.item_coinlist_title);
        addItemType(CoinListEntity.TYPE_COMMENT, R.layout.item_select_coin);

    }

    @Override
    protected void convert(@NonNull BaseViewHolder helper, CoinListEntity item) {
        switch (item.getItemType()) {
            case CoinListEntity.TYPE_TITLE:
                helper.setText(R.id.tv_title,item.getTitle());
                break;
            case CoinListEntity.TYPE_COMMENT:
                bindComment(helper, item.getCoinListReponse());
                break;
        }
    }

    private void bindComment(BaseViewHolder helper, CoinListReponse item) {
        ImageLoader.load(mContext,item.getImageAddress(),helper.getView(R.id.iv_coin_type));
        helper.setText(R.id.tv_coin_name,item.getCurrencyCode());
        helper.setText(R.id.tv_coin_full_name,item.getCurrencyFullName());
    }


}
