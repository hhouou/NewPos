package com.achpay.wallet.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.ReplacementTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.achpay.wallet.util.SpUtils;
import com.achpay.wallet.util.StrUtils;
import com.achpay.wallet.util.ToastUtil;
import com.achpay.wallet.widget.ContentWithSpaceEditText;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.OnKeyboardListener;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @desc法币支付数据填写页面
 */
public class FiatCardPayActivity extends BaseActivity<CoinReceivePresenter> implements CoinReceiveContract.View {


    @BindView(R.id.tv_countdowns)
    TextView tvCountdowns;

    @BindView(R.id.et_first_name)
    EditText etFirstName;
    @BindView(R.id.et_last_name)
    EditText etLastName;
    @BindView(R.id.et_email)
    EditText etEmail;
    @BindView(R.id.et_number)
    ContentWithSpaceEditText etNumber;
    @BindView(R.id.et_date)
    ContentWithSpaceEditText etDate;
    @BindView(R.id.et_cvv)
    EditText etCVV;


    private String fiatAmount, coinType, coinName;
    private PayOrderReponse payOrder;

    private CountDownTimer countDownTimer;


    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_fiat_card;
    }

    @Override
    protected void initEventAndData() {

        ImmersionBar.with(this)
                .statusBarDarkFont(true)   //状态栏字体是深色，不写默认为亮色
                .keyboardEnable(true, WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
                .setOnKeyboardListener(new OnKeyboardListener() {
                    @Override
                    public void onKeyboardChange(boolean isPopup, int keyboardHeight) {

                    }
                })
                .init();

        etFirstName.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        etLastName.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_WORDS);


        fiatAmount = getIntent().getStringExtra("fiatAmount");
        coinType = getIntent().getStringExtra("coinType");
        coinName = getIntent().getStringExtra("coinName");
        String imgAddress = getIntent().getStringExtra("imgAddress");


        setCountDown(15 * 60 * 1000);


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


    @OnClick({R.id.tv_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_confirm:

//                Intent intent = new Intent(this,WebviewActivity.class);
//                intent.putExtra("url","https://pay.mercuryo.io/redirect/?link_reference=1bb3de5c596b801aca5d5137330acaea&lang=en");
//                startActivity(intent);

                String firstName = etFirstName.getText().toString();
                String lastName = etLastName.getText().toString();
                String email = etEmail.getText().toString();
                String number = etNumber.getText().toString().replaceAll(" ", "");
                String date = etDate.getText().toString();
                String cvv = etCVV.getText().toString();
                String month, year;

                if (TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName)) {
                    ToastUtil.shortShow(getString(R.string.fiat_card_name_hint));
                    return;
                }
                if (TextUtils.isEmpty(email) || !StrUtils.isEmail(email)) {
                    ToastUtil.shortShow(getString(R.string.fiat_card_email_hint));
                    return;
                }
                if (TextUtils.isEmpty(number)) {
                    ToastUtil.shortShow(getString(R.string.fiat_card_number_hint));
                    return;
                }
                if (TextUtils.isEmpty(date)) {
                    ToastUtil.shortShow(getString(R.string.fiat_card_date_hint));
                    return;
                }
                try {
                    String[] arrDate = date.split("/");
                    month = arrDate[0];
                    year = "20" + arrDate[1];
                    if (StrUtils.StrToInt(month) > 12) {
                        ToastUtil.shortShow(getString(R.string.fiat_card_date_hint));
                        return;
                    }
                    if (StrUtils.StrToInt(year) <= 21) {
                        ToastUtil.shortShow(getString(R.string.fiat_card_date_hint));
                        return;
                    }
                } catch (Exception e) {
                    ToastUtil.shortShow(getString(R.string.fiat_card_date_hint));
                    return;
                }

                if (TextUtils.isEmpty(cvv) || cvv.length() < 2) {
                    ToastUtil.shortShow(getString(R.string.fiat_card_cvv_hint));
                    return;
                }

                mPresenter.payOrder(new PayOrderReq(App.getCurrencyCode(), fiatAmount, coinType, App.getMerchantCode(),
                        firstName, lastName, email, number, year, month, cvv));


                break;
        }
    }

    //region 点击隐藏键盘
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isHideInput(view, ev)) {
                HideSoftInput(view.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    // 判定是否需要隐藏
    private boolean isHideInput(View v, MotionEvent ev) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left
                    + v.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top
                    && ev.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    // 隐藏软键盘
    private void HideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    //endregion

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }


    }


}
