package com.achpay.wallet.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.achpay.wallet.R;
import com.achpay.wallet.app.App;
import com.achpay.wallet.base.BaseActivity;
import com.achpay.wallet.base.Contract.CoinReceiveContract;
import com.achpay.wallet.model.event.StatusEvent;
import com.achpay.wallet.model.http.request.PayOrderReq;
import com.achpay.wallet.model.http.response.BillListReponse;
import com.achpay.wallet.model.http.response.PayOrderReponse;
import com.achpay.wallet.presenter.CoinReceivePresenter;
import com.achpay.wallet.util.SoftKeyBoardListener;
import com.achpay.wallet.util.StrUtils;
import com.achpay.wallet.util.SystemUtil;
import com.achpay.wallet.util.ToastUtil;
import com.achpay.wallet.widget.ContentWithSpaceEditText;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @desc阿根廷支付
 */
public class ArgentinaPayActivity extends BaseActivity<CoinReceivePresenter> implements CoinReceiveContract.View {


    @BindView(R.id.tv_countdowns)
    TextView tvCountdowns;

    @BindView(R.id.et_first_name)
    EditText etFirstName;
    @BindView(R.id.et_last_name)
    EditText etLastName;
    @BindView(R.id.et_phone)
    EditText etPhone;


    @BindView(R.id.tv_select_id)
    TextView tvSelectId;
    @BindView(R.id.et_id_number)
    EditText etIdNumber;
    @BindView(R.id.ll_id_pop)
    LinearLayout llIdPop;


    private String fiatAmount, coinType, coinName;
    private PayOrderReponse payOrder;

    private CountDownTimer countDownTimer;
    private SoftKeyBoardListener keyBoardListener;


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_argentina_pay;
    }

    @Override
    protected void initEventAndData() {

        fiatAmount = getIntent().getStringExtra("fiatAmount");
        coinType = getIntent().getStringExtra("coinType");
        coinName = getIntent().getStringExtra("coinName");


        setCountDown(15 * 60 * 1000);

        initKeyBoardListener();
    }


    private void initKeyBoardListener() {
        if (keyBoardListener == null) {
            keyBoardListener = new SoftKeyBoardListener(llIdPop);
            keyBoardListener.setOnSoftKeyBoardChangeListener(new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
                @Override
                public void onKeyBoardShow(int height) {
                    llIdPop.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onKeyBoardHide(int height) {
                }
            });

        }
    }


    @Override
    public void payOrderSuceess(PayOrderReponse payOrder) {

        Intent intent = new Intent(this, WebviewActivity.class);
        intent.putExtra("url", payOrder.getWebUrl());
        intent.putExtra("sysOrderNum", payOrder.getSysOrderNum());
        intent.putExtra("fiatAmount", fiatAmount);
        intent.putExtra("qrContent", payOrder.getQrcodeLink());
        intent.putExtra("payAmount", payOrder.getPayAmount());
        intent.putExtra("payAmountUnit", payOrder.getPayAmountUnit());
        startActivity(intent);

        finish();
    }

    @Override
    public void refreshStatus(StatusEvent event) {

    }


    @Override
    public void orderStatusSucess(BillListReponse order) {
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


    @OnClick({R.id.tv_confirm, R.id.ll_id, R.id.tv_cnpj, R.id.tv_cpf, R.id.ll_body})
    public void onClick(View view) {
        llIdPop.setVisibility(View.INVISIBLE);
        etFirstName.clearFocus();
        etLastName.clearFocus();
        etPhone.clearFocus();
        etIdNumber.clearFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        switch (view.getId()) {
            case R.id.ll_body:
                break;
            case R.id.ll_id:
                llIdPop.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_cnpj:
                tvSelectId.setText(getString(R.string.argentina_pay_cnpj));
                break;
            case R.id.tv_cpf:
                tvSelectId.setText(getString(R.string.argentina_pay_cpf));
                break;
            case R.id.tv_confirm:
                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String phone = etPhone.getText().toString();
                String Idnumber = etIdNumber.getText().toString().replaceAll(" ", "");


                if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName)) {
                    ToastUtil.shortShow(getString(R.string.fiat_card_name_hint));
                    return;
                }
                if (TextUtils.isEmpty(phone) || !StrUtils.isEmail(phone)) {
                    ToastUtil.shortShow(getString(R.string.fiat_card_phone_hint));
                    return;
                }
                if (TextUtils.isEmpty(Idnumber)) {
                    ToastUtil.shortShow(getString(R.string.fiat_card_id_hint));
                    return;
                }


//
//                mPresenter.payOrder(new PayOrderReq(App.getCurrencyCode(), fiatAmount, coinType, App.getMerchantCode(),
//                        firstName, lastName, email, number, year, month, cvv));


                break;
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }


    }


}
