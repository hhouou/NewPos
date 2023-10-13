package com.achpay.wallet.ui.rate;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.achpay.wallet.R;
import com.achpay.wallet.app.App;
import com.achpay.wallet.base.BaseActivity;
import com.achpay.wallet.base.Contract.SelectCryptoContract;
import com.achpay.wallet.base.Contract.SettingContract;
import com.achpay.wallet.component.ImageLoader;
import com.achpay.wallet.model.http.response.CoinListReponse;
import com.achpay.wallet.model.http.response.FiatListReponse;
import com.achpay.wallet.presenter.SelectCryptoPresenter;
import com.achpay.wallet.presenter.SettingPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @desc 选择币种
 * Created by hy on 21/10/13.
 */
public class SelectCryptoActivity extends BaseActivity<SelectCryptoPresenter> implements SelectCryptoContract.View {

    @BindView(R.id.rv_recycler)
    RecyclerView rvRecycler;
    private BaseQuickAdapter mAdapter;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_select_crypto;
    }

    @Override
    protected void initEventAndData() {

        rvRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new BaseQuickAdapter<CoinListReponse, BaseViewHolder>(R.layout.item_select_crypto) {
            @Override
            protected void convert(BaseViewHolder helper, CoinListReponse item) {

                ImageLoader.load(mContext,item.getImageAddress(),helper.getView(R.id.iv_coin_type));
                helper.setText(R.id.tv_coin_name,item.getCurrencyCode());
                helper.setText(R.id.tv_coin_full_name," ("+item.getCurrencyFullName()+")");
            }
        };
        mAdapter.bindToRecyclerView(rvRecycler);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CoinListReponse coin = (CoinListReponse) mAdapter.getItem(position);
                Intent intent  = new Intent();
                intent.putExtra("coinName",coin.getCurrencyCode());
                setResult(1100,intent);
                finish();
            }
        });



        mPresenter.getCoinList();
    }


    @Override
    public void getListSuceess(List<CoinListReponse> o) {
        mAdapter.setNewData(o);
    }
}
