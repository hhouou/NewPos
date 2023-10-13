package com.achpay.wallet.ui.fragment;


import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.achpay.wallet.R;
import com.achpay.wallet.app.App;
import com.achpay.wallet.app.Constants;
import com.achpay.wallet.base.BaseFragment;
import com.achpay.wallet.base.Contract.TransactionsContract;
import com.achpay.wallet.component.ImageLoader;
import com.achpay.wallet.model.event.StatusEvent;
import com.achpay.wallet.model.http.request.BillListReq;
import com.achpay.wallet.model.http.response.BillListReponse;
import com.achpay.wallet.presenter.TransactionsPresenter;
import com.achpay.wallet.ui.activity.OrderDetailsActivity;
import com.achpay.wallet.util.StrUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.List;
import butterknife.BindView;

/**
 * 2021.10.13
 * transactions
 */
public class TransactionsFragment extends BaseFragment<TransactionsPresenter> implements TransactionsContract.View {



    @BindView(R.id.rv_recycler)
    RecyclerView rvRecycler;
    @BindView(R.id.srl_refresh)
    SmartRefreshLayout srlRefresh;

    private BaseQuickAdapter mAdapter;

    private int pageNum = 1;


    @Override
    protected int getLayout() {
        return R.layout.fragment_transactions;
    }

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected void initEventAndData() {

        ClassicsHeader.REFRESH_HEADER_PULLING =getString(R.string.header_pulldown);
        ClassicsHeader.REFRESH_HEADER_REFRESHING = getString(R.string.header_refreshing);
        ClassicsHeader.REFRESH_HEADER_RELEASE = getString(R.string.header_release);
        ClassicsHeader.REFRESH_HEADER_FINISH = getString(R.string.header_finish);
        ClassicsHeader.REFRESH_HEADER_FAILED = getString(R.string.header_failed);

        ClassicsFooter.REFRESH_FOOTER_LOADING = getString(R.string.footer_loading);
        ClassicsFooter.REFRESH_FOOTER_FINISH = getString(R.string.footer_finish);
        ClassicsFooter.REFRESH_FOOTER_FAILED = getString(R.string.footer_failed);
        ClassicsFooter.REFRESH_FOOTER_NOTHING = getString(R.string.footer_nothing);

        pageNum = 1;
        mPresenter.billList(new BillListReq(pageNum, App.getMerchantCode()));
        srlRefresh.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                pageNum++;
                mPresenter.billList(new BillListReq(pageNum, App.getMerchantCode()));
            }

            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                pageNum = 1;
                mPresenter.billList(new BillListReq(pageNum, App.getMerchantCode()));
            }
        });

        rvRecycler.setLayoutManager(new LinearLayoutManager(mContext));

        mAdapter = new BaseQuickAdapter<BillListReponse, BaseViewHolder>(R.layout.item_transaction_order) {
            @Override
            protected void convert(BaseViewHolder helper, BillListReponse item) {

                ImageLoader.load(mContext,item.getDigitalCurrencyAddress(),helper.getView(R.id.iv_coin_img));

                helper.setText(R.id.tv_transaction_status, item.getPayStatusStr(mContext));
                helper.setText(R.id.item_order_coin_amount, StrUtils.formatData(item.getShouldCount()));
                helper.setText(R.id.item_order_cointype,item.getDigitalCurrencyCode());
                helper.setTextColor(R.id.tv_sigin,getResources().getColor(item.getPayStatusColor()));
                helper.setTextColor(R.id.item_order_coin_amount,getResources().getColor(item.getPayStatusColor()));
                helper.setTextColor(R.id.item_order_cointype,getResources().getColor(item.getPayStatusColor()));

                helper.setText(R.id.item_order_currency_amount,StrUtils.formatData(item.getCurrencyAmount()));
                helper.setText(R.id.item_order_currencytype,item.getCurrencyCode());

                helper.setGone(R.id.tv_sigin,item.getPayStatus()==1||item.getPayStatus()==2);

                helper.setText(R.id.tv_transaction_time, item.getCreateTime());

            }
        };
        mAdapter.bindToRecyclerView(rvRecycler);

        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                BillListReponse bill = (BillListReponse) mAdapter.getItem(position);
                Intent intent = new Intent(mActivity, OrderDetailsActivity.class);
                intent.putExtra("sysOrderNo", bill.getSysOrderNum());
                startActivity(intent);
            }
        });

    }


    @Override
    public void getBillListSuceess(List<BillListReponse> o) {

        if (o.size() < 20) srlRefresh.finishLoadMoreWithNoMoreData();

        if (pageNum == 1 && o.size() == 0) {
            srlRefresh.finishRefresh();
            mAdapter.setEmptyView(R.layout.common_empty_view);
            mAdapter.setNewData(null);
            return;
        }

        mAdapter.setNewData(o);

        if (pageNum == 1 ) {
            srlRefresh.finishRefresh();
        } else  {
            srlRefresh.finishLoadMore();
        }

    }

    @Override
    public void refreshStatus(StatusEvent event) {
      if (srlRefresh!=null) srlRefresh.autoRefresh();
    }

    @Override
    public void stateError() {
        srlRefresh.finishRefresh();
        mAdapter.setEmptyView(R.layout.common_empty_view);
        mAdapter.setNewData(null);
    }
}
