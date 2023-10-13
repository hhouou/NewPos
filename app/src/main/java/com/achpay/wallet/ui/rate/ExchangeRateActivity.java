package com.achpay.wallet.ui.rate;


import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.text.InputType;
import android.text.Selection;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.achpay.wallet.R;
import com.achpay.wallet.app.App;
import com.achpay.wallet.base.BaseActivity;
import com.achpay.wallet.base.Contract.ExchangeRateContract;
import com.achpay.wallet.presenter.ExchangeRatePresenter;
import com.achpay.wallet.util.StrUtils;
import com.achpay.wallet.util.print.WiseasyPrintHelper;
import com.achpay.wallet.util.sunmiPrint.SunmiPrintHelper;
import com.achpay.wallet.widget.KeyboardView;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @desc 语言设置
 * Created by hy on 21/10/13.
 */
public class ExchangeRateActivity extends BaseActivity<ExchangeRatePresenter> implements ExchangeRateContract.View {

    @BindView(R.id.et_difital_input)
    EditText etDifitalInput;
    @BindView(R.id.et_fiat_input)
    EditText etFiatInput;

    @BindView(R.id.tv_rate)
    TextView tvRate;

    @BindView(R.id.tv_difital)
    TextView tvDifital;
    @BindView(R.id.tv_fiat)
    TextView tvFiat;

    @BindView(R.id.kv_keyboard)
    KeyboardView kvKeyboard;
    private String coinName;
    private String fiatName;
    private Disposable mDisposable;
    private double fiatAmount,coinAmount,rate;


    @Override
    protected int getLayout() {
        return R.layout.activity_exchange_rate;
    }

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }


    @Override
    protected void initEventAndData() {

        coinName = "ETH";
        fiatName = "HKD";

        tvDifital.setText(coinName);
        tvFiat.setText(fiatName);

        disableShowInput(etDifitalInput);
        disableShowInput(etFiatInput);

        kvKeyboard.setOnTextChangeListener(new KeyboardView.TextChangeListener() {
            @Override
            public void changeText(String sub) {

                if (etDifitalInput.hasFocus()) {
                    etDifitalInput.setText(sub);
                    Selection.setSelection(etDifitalInput.getText(), etDifitalInput.getText().toString().length());

                    coinAmount = StrUtils.StrToDouble(sub);
                    setFialAmount();


                }

                if (etFiatInput.hasFocus()) {
                    etFiatInput.setText(sub);
                    Selection.setSelection(etFiatInput.getText(), etFiatInput.getText().toString().length());
                    fiatAmount = StrUtils.StrToDouble(sub);

                    setCoinAmount();
                }

            }



            @Override
            public void confirm() {

            }
        });

        etDifitalInput.setText("");
        etDifitalInput.setFocusable(true);
        etDifitalInput.setFocusableInTouchMode(true);
        etDifitalInput.requestFocus();

        initPrint();

        mDisposable = Observable.interval(0, 60, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {

                    mPresenter.getExchangeRate(coinName, fiatName);
                });

    }

    private void setFialAmount() {

        if (rate==0||coinAmount==0){
            fiatAmount = 0;
        }else {
            fiatAmount =StrUtils.formatCurrency(   coinAmount*rate,6);
        }

        etFiatInput.setText(fiatAmount + "");
    }
    private void setCoinAmount() {

        if (rate==0||fiatAmount==0){
            coinAmount = 0;
        }else {
            coinAmount =StrUtils.formatCurrency( fiatAmount/rate,2);
        }

        etDifitalInput.setText(coinAmount + "");
    }

    public void initPrint() {

        if (Build.MANUFACTURER.equals("SUNMI")) {
            SunmiPrintHelper.getInstance().initSunmiPrinterService(mContext);
        } else if (Build.MANUFACTURER.toLowerCase().equals("wiseasy")) {
            WiseasyPrintHelper.getInstance().initWiseasyPrinterService(mContext);

        }

    }


    private void startPrint() {


        if (Build.MANUFACTURER.equals("SUNMI")) {
            SunmiPrintHelper.getInstance().printExchangeRate(mContext, fiatAmount,fiatName,coinAmount, coinName);
        } else if (Build.MANUFACTURER.toLowerCase().equals("wiseasy")) {
            WiseasyPrintHelper.getInstance().printExchangeRate(mContext, fiatAmount,fiatName,coinAmount, coinName);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Build.MANUFACTURER.equals("SUNMI")) {
                    SunmiPrintHelper.getInstance().printExchangeRate(mContext, fiatAmount,fiatName,coinAmount, coinName);
                } else if (Build.MANUFACTURER.toLowerCase().equals("wiseasy")) {
                    WiseasyPrintHelper.getInstance().printExchangeRate(mContext, fiatAmount,fiatName,coinAmount, coinName);
                }

            }
        }, 2000);
    }

    @OnClick({R.id.et_difital_input, R.id.et_fiat_input, R.id.iv_print, R.id.tv_difital, R.id.iv_difital, R.id.tv_fiat, R.id.iv_fiat})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.et_difital_input:
                kvKeyboard.setNDecimal(6);

                etDifitalInput.setText("");
                etDifitalInput.setFocusable(true);
                etDifitalInput.setFocusableInTouchMode(true);
                etDifitalInput.requestFocus();

                etFiatInput.setFocusable(false);
                etFiatInput.setFocusableInTouchMode(false);
                etFiatInput.clearFocus();
                break;
            case R.id.et_fiat_input:
                kvKeyboard.setNDecimal(2);

                etFiatInput.setText("");
                etFiatInput.setFocusable(true);
                etFiatInput.setFocusableInTouchMode(true);
                etFiatInput.requestFocus();

                etDifitalInput.setFocusable(false);
                etDifitalInput.setFocusableInTouchMode(false);
                etDifitalInput.clearFocus();
                break;
            case R.id.iv_print:
                startPrint();

                break;
            case R.id.tv_difital:
            case R.id.iv_difital:
                startActivityForResult(new Intent(this, SelectCryptoActivity.class), 1100);
                break;
            case R.id.tv_fiat:
            case R.id.iv_fiat:
                startActivityForResult(new Intent(this, SelectFiatActivity.class), 2200);

                break;
        }

    }


    public void disableShowInput(EditText editText) {
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            editText.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method method;
            try {
                method = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(editText, false);
            } catch (Exception e) {//TODO: handle exception
            }
        }
    }


    @Override
    public void onExchangeRate(String rate1) {
        this.rate = StrUtils.formatCurrency(rate1,6);
        tvRate.setText(coinName + "/" + fiatName + " = " + rate);

        setFialAmount();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1100 && resultCode == 1100) {
            coinName = data.getStringExtra("coinName");

            tvDifital.setText(coinName);
            mPresenter.getExchangeRate(coinName, fiatName);

        }
        if (requestCode == 2200 && resultCode == 2200) {
            fiatName = data.getStringExtra("fiatName");
            tvFiat.setText(fiatName);
            mPresenter.getExchangeRate(coinName, fiatName);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mDisposable != null) {
            mDisposable.dispose();
            mDisposable = null;
        }

        SunmiPrintHelper.getInstance().deInitSunmiPrinterService(mContext);
        WiseasyPrintHelper.getInstance().deInitWiseasyPrinterService(mContext);

    }


}
