package com.achpay.wallet.ui.activity;


import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.achpay.wallet.R;
import com.achpay.wallet.base.BaseActivity;
import com.achpay.wallet.base.Contract.OrderDetailsContract;
import com.achpay.wallet.component.ImageLoader;
import com.achpay.wallet.model.http.response.BillListReponse;
import com.achpay.wallet.model.http.response.PayOrderReponse;
import com.achpay.wallet.presenter.OrderDetailsPresenter;
import com.achpay.wallet.util.StrUtils;
import com.achpay.wallet.util.print.WiseasyPrintHelper;
import com.achpay.wallet.util.sunmiPrint.SunmiPrintHelper;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @desc 详情
 * Created by hy on 22/1/17.
 */
public class OrderDetailsActivity extends BaseActivity<OrderDetailsPresenter> implements OrderDetailsContract.View {

    @BindView(R.id.tv_order_no)
    TextView tvOrderNo;
    @BindView(R.id.tv_status)
    TextView tvStatus;
    @BindView(R.id.ll_time)
    LinearLayout llTime;
    @BindView(R.id.tv_time)
    TextView tvTime;

    @BindView(R.id.tv_fiat_amount)
    TextView tvFiatAount;
    @BindView(R.id.tv_digital_amount)
    TextView tvDigitalAmount;
    @BindView(R.id.iv_payment_method_img)
    ImageView ivpayImage;
    @BindView(R.id.tv_payment_method)
    TextView tvPaymentMethod;
    @BindView(R.id.tv_order_create_time)
    TextView tvOrderCreateTime;
    @BindView(R.id.tv_address_to)
    TextView tvAddressTo;
    @BindView(R.id.ll_order_closed)
    LinearLayout llOrderClosed;
    @BindView(R.id.tv_order_closed)
    TextView tvOderClosed;

    @BindView(R.id.tv_payment_method_title)
    TextView tvPaymentMethodTitle;
    @BindView(R.id.ll_payment_method_body)
    LinearLayout llPaymentMethodbody;
    @BindView(R.id.tv_payment_amount)
    TextView tvPaymentAmount;
    @BindView(R.id.tv_order_payed_time)
    TextView tvOrderPayedTime;
    @BindView(R.id.tv_blockchain_id)
    TextView tvBlockchainId;
    @BindView(R.id.tv_address_from)
    TextView tvAddressForm;

//    @BindView(R.id.tv_confirm)
//    TextView tvConfirm;
    @BindView(R.id.iv_print)
    ImageView ivPrint;

    private CountDownTimer countDownTimer;
    private String sysOrderNo;
    private BillListReponse billDetals;


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_order_detals;
    }

    @Override
    protected void initEventAndData() {

        sysOrderNo = getIntent().getStringExtra("sysOrderNo");
        mPresenter.getDetails(sysOrderNo);

        initPrint();
    }


    public void initPrint() {

        if (Build.MANUFACTURER.equals("SUNMI")) {
            SunmiPrintHelper.getInstance().initSunmiPrinterService(mContext);
        } else if ((Build.MANUFACTURER.toLowerCase()).equals("wiseasy")) {
            WiseasyPrintHelper.getInstance().initWiseasyPrinterService(mContext);

        }

    }

    private void startPrint() {
        PayOrderReponse payOrder = new PayOrderReponse();
        payOrder.setPayAmount(StrUtils.formatData(billDetals.getCurrencyAmount()));
        payOrder.setPayAmountUnit(billDetals.getCurrencyCode());
        payOrder.setPayAcount(StrUtils.formatData(billDetals.getRealCount()));
        payOrder.setPayAcountUnit(billDetals.getDigitalCurrencyCode());
        payOrder.setOrderCtreateTime(billDetals.getCreateTime());
        payOrder.setSysOrderNum(billDetals.getSysOrderNum());
        String coinName = billDetals.getPaymentMethod();


        if (Build.MANUFACTURER.equals("SUNMI")) {
            SunmiPrintHelper.getInstance().printOrder(mContext, payOrder, coinName);
        } else if (Build.MANUFACTURER.toLowerCase().equals("wiseasy")) {
            WiseasyPrintHelper.getInstance().printOrder(mContext, payOrder, coinName);
        }
    }




    @Override
    public void onDetailsSeccuss(BillListReponse detals) {
        billDetals = detals;

        if (detals.getPayStatus()==1||detals.getPayStatus()==2){
            ivPrint.setVisibility(View.VISIBLE);
        }

        tvOrderNo.setText(detals.getSysOrderNum());
        tvStatus.setText(detals.getPayStatusStr(mContext));
        tvStatus.setTextColor(getResources().getColor(detals.getPayStatusColor()));
        if (detals.getPayStatus()==0){
            llTime.setVisibility(View.VISIBLE);
//            tvConfirm.setVisibility(View.VISIBLE);
            setCountDown(detals.getRemainingPaymentTime()* 1000);
        }else {
            llTime.setVisibility(View.GONE);
//            tvConfirm.setVisibility(View.GONE);
            if (countDownTimer!=null)countDownTimer.cancel();
        }

        tvFiatAount.setText(StrUtils.formatData(detals.getCurrencyAmount())+" "+detals.getCurrencyCode());
        tvDigitalAmount.setText(StrUtils.formatData(detals.getRealCount())+" "+detals.getDigitalCurrencyCode());
        ImageLoader.load(mContext,detals.getIcon(),ivpayImage);
        tvPaymentMethod.setText(detals.getDigitalCurrencyCode());
        tvOrderCreateTime.setText(detals.getCreateTime());
        tvAddressTo.setText(detals.getToAddress());
        if (detals.getPayStatus()==4){
            llOrderClosed.setVisibility(View.VISIBLE);
            tvOderClosed.setText(detals.getUpdateTime());
        }else {
            llOrderClosed.setVisibility(View.GONE);
        }

        if (detals.getPayStatus()==1||detals.getPayStatus()==2||detals.getPayStatus()==3){
            tvPaymentMethodTitle.setVisibility(View.VISIBLE);
            llPaymentMethodbody.setVisibility(View.VISIBLE);

            tvPaymentAmount.setText(StrUtils.formatData(detals.getCoboRealCount())+" "+detals.getDigitalCurrencyCode());
            tvOrderPayedTime.setText(detals.getPayTime());
            tvBlockchainId.setText(detals.getTradeHash());
            tvAddressForm.setText(detals.getFromAddress());

        }else {
            tvPaymentMethodTitle.setVisibility(View.GONE);
            llPaymentMethodbody.setVisibility(View.GONE);
        }


    }

    /**
     * CountDownTimer 实现倒计时
     */
    private void setCountDown(long millisInFuture) {
        if (countDownTimer!=null)countDownTimer.cancel();
        countDownTimer = new CountDownTimer(millisInFuture, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                DecimalFormat dec = new DecimalFormat("##.##");

                tvTime.setText((int) Math.floor(millisUntilFinished / 60000) + ":" + dec.format((millisUntilFinished % 60000) / 1000));
            }
            @Override
            public void onFinish() {
                mPresenter.getDetails(sysOrderNo);
            }
        }.start();
    }



    @OnClick({R.id.iv_print})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_print:
                startPrint();
                break;
//            case R.id.tv_confirm:
//                Intent intent = new Intent(mContext, CoinReceiveActivity.class);
//                intent.putExtra("sysOrderNum",sysOrderNo);
//                startActivity(intent);
//                break;
        }
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer!=null)countDownTimer.cancel();
    }
}
