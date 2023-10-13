package com.achpay.wallet.ui.activity;


import android.os.Build;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.achpay.wallet.R;
import com.achpay.wallet.app.App;
import com.achpay.wallet.base.BaseActivity;
import com.achpay.wallet.base.Contract.TransactionSummaryContract;
import com.achpay.wallet.model.http.request.TradeSummaryReq;
import com.achpay.wallet.model.http.response.LanguageInfo;
import com.achpay.wallet.model.http.response.PayOrderReponse;
import com.achpay.wallet.model.http.response.TradeSummaryReponse;
import com.achpay.wallet.presenter.TransactionSummaryPresenter;
import com.achpay.wallet.util.print.WiseasyPrintHelper;
import com.achpay.wallet.util.sunmiPrint.SunmiPrintHelper;
import com.achpay.wallet.widget.datepicker.DateFormatUtils;
import com.achpay.wallet.widget.datepicker.DatePicker;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by  on 2021/10/10.
 */

public class SummaryActivity extends BaseActivity<TransactionSummaryPresenter> implements TransactionSummaryContract.View {


    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_receipt_number)
    TextView tvReceiptNumber;

    @BindView(R.id.rv_recycler)
    RecyclerView rvRecycler;
    @BindView(R.id.tv_all_entries)
    TextView tvAllEntries;
    @BindView(R.id.tv_all_amount)
    TextView tvAllAmount;

    private BaseQuickAdapter mAdapter;


    private String startTime,endTime;
    private TradeSummaryReponse reponse;


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_summary;
    }

    @Override
    protected void initEventAndData() {

         startTime = getIntent().getStringExtra("startTime");
         endTime = getIntent().getStringExtra("endTime");

        mPresenter.onTradeSummary(new TradeSummaryReq(startTime, endTime));

        tvStartTime.setText(startTime);
        tvEndTime.setText(endTime);

        rvRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new BaseQuickAdapter<TradeSummaryReponse.ListBean, BaseViewHolder>(R.layout.item_summary) {
            @Override
            protected void convert(BaseViewHolder helper, TradeSummaryReponse.ListBean item) {

                helper.setText(R.id.tv_type, item.getCoinCode())
                        .setText(R.id.tv_entries, item.getCoinPayCount())
                        .setText(R.id.tv_amount,item.getFiatCode()+" "+ item.getFiatPayAmount());
            }
        };
        mAdapter.bindToRecyclerView(rvRecycler);


        initPrint();
    }


    public void initPrint() {

        if (Build.MANUFACTURER.equals("SUNMI")) {
            SunmiPrintHelper.getInstance().initSunmiPrinterService(mContext);
        } else if ((Build.MANUFACTURER.toLowerCase()).equals("wiseasy")) {
            WiseasyPrintHelper.getInstance().initWiseasyPrinterService(mContext);

        }

    }


    public void onPrint(View view) {

        if (Build.MANUFACTURER.equals("SUNMI")) {
            SunmiPrintHelper.getInstance().printSummary(mContext,startTime,endTime,reponse);
        } else if (Build.MANUFACTURER.toLowerCase().equals("wiseasy")) {
            WiseasyPrintHelper.getInstance().printSummary(mContext, startTime, endTime,reponse);
        }else {
            showErrorMsg(getString(R.string.summary_not_support_printing));
        }
    }

    @Override
    public void onSummarySeccuss(TradeSummaryReponse reponse) {
        this.reponse = reponse;

        tvReceiptNumber.setText(reponse.getPayTotal());
        tvAllEntries.setText(reponse.getPayTotal());
        tvAllAmount.setText(App.getInstance().getCurrencyCode()+" "+ reponse.getTotalAmount());
        mAdapter.setNewData(reponse.getList());

    }
}