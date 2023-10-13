package com.achpay.wallet.ui.activity;

import android.content.Intent;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.achpay.wallet.R;
import com.achpay.wallet.base.BaseActivity;
import com.achpay.wallet.base.Contract.SelectCoinContract;
import com.achpay.wallet.component.ImageLoader;
import com.achpay.wallet.model.http.response.CoinListReponse;
import com.achpay.wallet.presenter.SelectCoinPresenter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import java.util.List;
import butterknife.BindView;

/**
 * Created by  on 2021/10/10.
 */

public class SelectCoinActivity extends BaseActivity<SelectCoinPresenter> implements SelectCoinContract.View {


    @BindView(R.id.rv_recycler_1)
    RecyclerView rvRecycler1;
    @BindView(R.id.rv_recycler_2)
    RecyclerView rvRecycler2;
    @BindView(R.id.rv_recycler_3)
    RecyclerView rvRecycler3;
    @BindView(R.id.rv_recycler_4)
    RecyclerView rvRecycler4;

    private BaseQuickAdapter mAdapter1, mAdapter2, mAdapter3,mAdapter4;
    private String fiatAmount;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_selectcoin;
    }

    @Override
    protected void initEventAndData() {

        fiatAmount = getIntent().getStringExtra("fiatAmount");
        rvRecycler1.setNestedScrollingEnabled(false);//禁止rcyc嵌套滑动
        rvRecycler2.setNestedScrollingEnabled(false);//禁止rcyc嵌套滑动
        rvRecycler3.setNestedScrollingEnabled(false);//禁止rcyc嵌套滑动

        rvRecycler1.setLayoutManager(new LinearLayoutManager(mContext));
        rvRecycler2.setLayoutManager(new LinearLayoutManager(mContext));
        rvRecycler3.setLayoutManager(new LinearLayoutManager(mContext));
        rvRecycler4.setLayoutManager(new LinearLayoutManager(mContext));

        mAdapter1 = new BaseQuickAdapter<CoinListReponse, BaseViewHolder>(R.layout.item_select_coin) {
            @Override
            protected void convert(BaseViewHolder helper, CoinListReponse item) {

                ImageLoader.load(mContext,item.getImageAddress(),helper.getView(R.id.iv_coin_type));
                helper.setText(R.id.tv_coin_name,item.getCurrencyCode());
//                helper.setText(R.id.tv_coin_full_name,item.getCurrencyFullName());
            }
        };
        mAdapter1.bindToRecyclerView(rvRecycler1);


        mAdapter2 = new BaseQuickAdapter<CoinListReponse, BaseViewHolder>(R.layout.item_select_coin) {
            @Override
            protected void convert(BaseViewHolder helper, CoinListReponse item) {

                ImageLoader.load(mContext,item.getImageAddress(),helper.getView(R.id.iv_coin_type));
                helper.setText(R.id.tv_coin_name,item.getCurrencyCode());
            }
        };
        mAdapter2.bindToRecyclerView(rvRecycler2);

        mAdapter3 = new BaseQuickAdapter<CoinListReponse, BaseViewHolder>(R.layout.item_select_coin) {
            @Override
            protected void convert(BaseViewHolder helper, CoinListReponse item) {

                ImageLoader.load(mContext,item.getImageAddress(),helper.getView(R.id.iv_coin_type));
                helper.setText(R.id.tv_coin_name,item.getCurrencyCode());
            }
        };
        mAdapter3.bindToRecyclerView(rvRecycler3);

        mAdapter4 = new BaseQuickAdapter<CoinListReponse, BaseViewHolder>(R.layout.item_select_coin) {
            @Override
            protected void convert(BaseViewHolder helper, CoinListReponse item) {

                ImageLoader.load(mContext,item.getImageAddress(),helper.getView(R.id.iv_coin_type));
                helper.setText(R.id.tv_coin_name,item.getCurrencyCode());
                helper.setText(R.id.tv_coin_full_name,item.getCurrencyFullName());

            }
        };
        mAdapter4.bindToRecyclerView(rvRecycler4);



        mAdapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CoinListReponse item = (CoinListReponse) adapter.getItem(position);
                Intent intent = new Intent(mContext, CoinReceiveActivity.class);
                intent.putExtra("fiatAmount",fiatAmount);
                intent.putExtra("imgAddress",item.getImageAddress());
                intent.putExtra("coinType",item.getPayType());
                intent.putExtra("coinName",item.getCurrencyFullName());
                startActivity(intent);

            }
        });
        mAdapter2.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CoinListReponse item = (CoinListReponse) adapter.getItem(position);
                Intent intent = new Intent(mContext, FiatCardPayActivity.class);
                intent.putExtra("fiatAmount",fiatAmount);
                intent.putExtra("imgAddress",item.getImageAddress());
                intent.putExtra("coinType",item.getPayType());
                intent.putExtra("coinName",item.getCurrencyFullName());
                startActivity(intent);
            }
        });
        mAdapter3.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CoinListReponse item = (CoinListReponse) adapter.getItem(position);
                Intent intent = new Intent(mContext, ArgentinaPayActivity.class);
                intent.putExtra("fiatAmount",fiatAmount);
                intent.putExtra("imgAddress",item.getImageAddress());
                intent.putExtra("coinType",item.getPayType());
                intent.putExtra("coinName",item.getCurrencyFullName());
                startActivity(intent);
            }
        });
        mAdapter4.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CoinListReponse item = (CoinListReponse) adapter.getItem(position);
                Intent intent = new Intent(mContext, CoinReceiveActivity.class);
                intent.putExtra("fiatAmount",fiatAmount);
                intent.putExtra("imgAddress",item.getImageAddress());
                intent.putExtra("coinType",item.getPayType());
                intent.putExtra("coinName",item.getCurrencyFullName());
                startActivity(intent);
            }
        });

        mPresenter.getCoinList();

    }



    @Override
    public void getListSuceess(List<CoinListReponse> walletList,List<CoinListReponse> fiatList, List<CoinListReponse> argentinaList,List<CoinListReponse> ethList) {
        mAdapter1.setNewData(walletList);
        mAdapter2.setNewData(fiatList);
//        mAdapter3.setNewData(argentinaList);
        mAdapter4.setNewData(ethList);
    }
}
