package com.achpay.wallet.ui.rate;

import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.achpay.wallet.R;
import com.achpay.wallet.app.App;
import com.achpay.wallet.base.BaseActivity;
import com.achpay.wallet.base.Contract.SelectFiatContract;
import com.achpay.wallet.base.Contract.SettingContract;
import com.achpay.wallet.component.ImageLoader;
import com.achpay.wallet.model.http.response.CoinListReponse;
import com.achpay.wallet.model.http.response.FiatListReponse;
import com.achpay.wallet.presenter.SelectFiatPresenter;
import com.achpay.wallet.presenter.SettingPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * @desc 选择法币
 * Created by hy on 21/10/13.
 */
public class SelectFiatActivity extends BaseActivity<SelectFiatPresenter> implements SelectFiatContract.View {

    @BindView(R.id.rv_recycler)
    RecyclerView rvRecycler;
    private BaseQuickAdapter mAdapter;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_select_fiat;
    }

    @Override
    protected void initEventAndData() {

        rvRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new BaseQuickAdapter<FiatListReponse, BaseViewHolder>(R.layout.item_select_fiat) {
            @Override
            protected void convert(BaseViewHolder helper, FiatListReponse item) {

                helper.setText(R.id.tv_fiat_name,item.getCurrencyCode());
            }
        };
        mAdapter.bindToRecyclerView(rvRecycler);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                FiatListReponse fiat = (FiatListReponse) mAdapter.getItem(position);
                Intent intent  = new Intent();
                intent.putExtra("fiatName",fiat.getCurrencyCode());
                setResult(2200,intent);
                finish();
            }
        });

        mPresenter.fiatList();
    }


    @Override
    public void onFiatlist(List<FiatListReponse> list) {
        mAdapter.setNewData(list);
    }
}
