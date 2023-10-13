package com.achpay.wallet.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.achpay.wallet.R;
import com.achpay.wallet.app.App;
import com.achpay.wallet.base.BaseActivity;
import com.achpay.wallet.base.Contract.CoinReceiveContract;
import com.achpay.wallet.component.ImageLoader;
import com.achpay.wallet.model.event.StatusEvent;
import com.achpay.wallet.model.http.request.PayOrderReq;
import com.achpay.wallet.model.http.response.BillListReponse;
import com.achpay.wallet.model.http.response.PayOrderReponse;
import com.achpay.wallet.presenter.CoinReceivePresenter;
import com.achpay.wallet.ui.main.MainActivity;
import com.achpay.wallet.util.QRCode;
import com.achpay.wallet.util.SpUtils;
import com.achpay.wallet.util.StrUtils;
import com.achpay.wallet.util.SystemUtil;
import com.achpay.wallet.util.TimeUtils;
import com.achpay.wallet.util.ToastUtil;
import com.achpay.wallet.util.print.WiseasyPrintHelper;
import com.achpay.wallet.util.sunmiPrint.SunmiPrintHelper;


import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @desc订单支付页 Created by hy on 21/10/13.
 */
public class CoinReceiveActivity extends BaseActivity<CoinReceivePresenter> implements CoinReceiveContract.View {


    @BindView(R.id.iv_coin_type)
    ImageView ivCoinType;
    @BindView(R.id.tv_countdowns)
    TextView tvCountdowns;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.iv_imageview_qrcode)
    ImageView ivImageviewQrcode;
    @BindView(R.id.iv_check_amount)
    ImageView ivCheckAmount;
    @BindView(R.id.tv_check_amount)
    TextView tvCheckAmount;
    @BindView(R.id.ll_check_amount)
    LinearLayout llCheckAmount;
    @BindView(R.id.view_line)
    View viewLine;


    private String fiatAmount, coinType;
    private PayOrderReponse payOrder;
    private BillListReponse order;

    private Handler handler;
    private Timer timer;
    private CountDownTimer countDownTimer;
    private String coinName;


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_coin_receive;
    }

    @Override
    protected void initEventAndData() {

        fiatAmount = getIntent().getStringExtra("fiatAmount");
        coinType = getIntent().getStringExtra("coinType");
        coinName = getIntent().getStringExtra("coinName");
        String imgAddress = getIntent().getStringExtra("imgAddress");

        ImageLoader.load(mContext, imgAddress, ivCoinType);


        Log.v("pay-", Build.MANUFACTURER);

        if (TextUtils.equals("Solana Pay", coinName)) {
            llCheckAmount.setVisibility(View.GONE);
            viewLine.setVisibility(View.INVISIBLE);
        }

        ivCheckAmount.setSelected(true);
        tvCheckAmount.setText(getString(R.string.receipt_with_amount));


        mPresenter.payOrder(new PayOrderReq(App.getCurrencyCode(), fiatAmount, coinType, App.getMerchantCode()));


        initPrint();


    }



    public void initPrint() {

        if (Build.MANUFACTURER.equals("SUNMI")) {
            SunmiPrintHelper.getInstance().initSunmiPrinterService(mContext);
        } else if (Build.MANUFACTURER.toLowerCase().equals("wiseasy")) {
            WiseasyPrintHelper.getInstance().initWiseasyPrinterService(mContext);

        }

    }


    @Override
    public void payOrderSuceess(PayOrderReponse o) {
        this.payOrder = o;


        if (TextUtils.isEmpty(payOrder.getQrContent())) {
            ivImageviewQrcode.setImageResource(R.color.colorGray);
        } else {
            String qr = ivCheckAmount.isSelected() ? payOrder.getQrContent() : payOrder.getQrcodeLink();
            ImageLoader.load(mContext, QRCode.createQRCode(qr, SystemUtil.dp2px(mContext, 500)), ivImageviewQrcode);
        }


        tvAddress.setText(payOrder.getQrcodeLink());
        tvType.setText(o.getPayAcount() + "  " + o.getPayAcountUnit());

        setCountDown(payOrder.getRemainingPaymentTime() * 1000);
        callAsynchronousTask();

    }

    @Override
    public void refreshStatus(StatusEvent event) {
        if (event.getStatus() == 0) return;

        boolean isSave = SpUtils.getBoolean(mContext, payOrder.getSysOrderNum(), false);
        if (isSave) return;
        SpUtils.putBoolean(mContext, payOrder.getSysOrderNum(), true);


        startPrint();


        Intent intent = new Intent(mContext, PaymentStatusActivity.class);
        intent.putExtra("status", event.getStatus());
        intent.putExtra("statusStr", event.getStatus() == 1 ? getString(R.string.pay_status_payment_success) : getString(R.string.pay_status_failed));
        intent.putExtra("fiatAmount", fiatAmount);
        intent.putExtra("qrContent", payOrder.getQrcodeLink());
        intent.putExtra("payAmount", payOrder.getPayAmount());
        intent.putExtra("payAmountUnit", payOrder.getPayAmountUnit());
        startActivity(intent);

        finish();
    }


    @Override
    public void orderStatusSucess(BillListReponse order) {
        this.order = order;
        int status = 2;
        if (order.getPayStatus() == 0) return;

        if (order.getPayStatus() == 1 || order.getPayStatus() == 2) {
            status = 1;
        } else {
            status = 2;
        }

        boolean isSave = SpUtils.getBoolean(mContext, payOrder.getSysOrderNum(), false);
        if (isSave) return;
        SpUtils.putBoolean(mContext, payOrder.getSysOrderNum(), true);

        startPrint();

        Intent intent = new Intent(mContext, PaymentStatusActivity.class);
        intent.putExtra("status", status);
        intent.putExtra("statusStr", order.getPayStatusStr(mContext));
        intent.putExtra("fiatAmount", fiatAmount);
        intent.putExtra("qrContent", payOrder.getQrcodeLink());
        intent.putExtra("payAmount", payOrder.getPayAmount());
        intent.putExtra("payAmountUnit", payOrder.getPayAmountUnit());
        startActivity(intent);

        finish();
    }

    /**
     * CountDownTimer 实现倒计时
     */
    private void setCountDown(long millisInFuture) {

        countDownTimer = new CountDownTimer(millisInFuture, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                DecimalFormat dec = new DecimalFormat("##.##");

                tvCountdowns.setText((int) Math.floor(millisUntilFinished / 60000) + ":" + dec.format((millisUntilFinished % 60000) / 1000));
            }

            @Override
            public void onFinish() {
                finish();
            }
        }.start();
    }


    @OnClick({R.id.tv_address, R.id.ll_layout_address, R.id.ll_check_amount})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_address:
            case R.id.ll_layout_address:
                StrUtils.getCopy(mContext, payOrder.getQrcodeLink(), getString(R.string.receipt_copy_success));
                break;
            case R.id.ll_check_amount:

                ivCheckAmount.setSelected(!ivCheckAmount.isSelected());
                tvCheckAmount.setText(getString(ivCheckAmount.isSelected() ? R.string.receipt_with_amount : R.string.receipt_without_amount));

                String qr = ivCheckAmount.isSelected() ? payOrder.getQrContent() : payOrder.getQrcodeLink();
                ImageLoader.load(mContext, QRCode.createQRCode(qr, SystemUtil.dp2px(mContext, 200)), ivImageviewQrcode);
                break;
        }
    }


    public void callAsynchronousTask() {
        handler = new Handler();
        timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        mPresenter.orderStatus(payOrder.getSysOrderNum());
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 5000); //execute in every 10000 ms

    }


    private void startPrint() {

        payOrder.setOrderCtreateTime(order.getPayTime());

        if (Build.MANUFACTURER.equals("SUNMI")) {
            SunmiPrintHelper.getInstance().printOrder(mContext, payOrder, coinName);
        } else if (Build.MANUFACTURER.toLowerCase().equals("wiseasy")) {
            WiseasyPrintHelper.getInstance().printOrder(mContext, payOrder, coinName);
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Build.MANUFACTURER.equals("SUNMI")) {
                    SunmiPrintHelper.getInstance().printOrder(mContext, payOrder, coinName);
                } else if (Build.MANUFACTURER.toLowerCase().equals("wiseasy")) {
                    WiseasyPrintHelper.getInstance().printOrder(mContext, payOrder, coinName);
                }

            }
        }, 2000);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        handler = null;
        if (timer != null) timer.cancel();

        SunmiPrintHelper.getInstance().deInitSunmiPrinterService(mContext);
        WiseasyPrintHelper.getInstance().deInitWiseasyPrinterService(mContext);

    }


}
