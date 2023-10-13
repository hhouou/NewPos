package com.achpay.wallet.ui.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.achpay.wallet.R;
import com.achpay.wallet.app.App;
import com.achpay.wallet.app.Constants;
import com.achpay.wallet.base.BaseFragment;
import com.achpay.wallet.base.Contract.PaymentContract;
import com.achpay.wallet.component.ImageLoader;
import com.achpay.wallet.component.RxBus;
import com.achpay.wallet.model.event.StatusEvent;
import com.achpay.wallet.model.http.response.BillListReponse;
import com.achpay.wallet.presenter.PaymentPresenter;
import com.achpay.wallet.ui.activity.SelectCoinActivity;
import com.achpay.wallet.ui.main.MainFragment;
import com.achpay.wallet.util.NumberUtils;
import com.achpay.wallet.util.PayStatusService;
import com.achpay.wallet.widget.KeyboardView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 95 on 2021/10/11.
 * payment
 */

public class PaymentFragment extends BaseFragment<PaymentPresenter> implements PaymentContract.View {


//    @BindView(R.id.tv_first_income)
//    TextView tvFirstIncome;
//    @BindView(R.id.tv_checkout_incometime)
//    TextView tvCheckoutIncometime;
//    @BindView(R.id.tv_checkout_incomeamount)
//    TextView tvCheckoutIncomeamount;
//    @BindView(R.id.tv_checkout_incometype)
//    TextView tvCheckoutIncometype;
//    @BindView(R.id.tv_checkout_redpoint)
//    ImageView tvCheckoutRedpoint;
//    @BindView(R.id.tv_checkout_incomestatus)
//    TextView tvCheckoutIncomestatus;
//    @BindView(R.id.iv_checkout_right)
//    ImageView ivCheckoutRight;
    @BindView(R.id.rl_income_item)
    RelativeLayout rlIncomeItem;
    @BindView(R.id.tv_receipt_amount_unit)
    TextView tvReceiptAmountUnit;
    @BindView(R.id.et_receipt_input)
    EditText etReceiptInput;
    @BindView(R.id.kv_keyboard)
    KeyboardView kvKeyboard;


    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_payment;

    }

    @Override
    protected void initEventAndData() {

        kvKeyboard.setOnTextChangeListener(new KeyboardView.TextChangeListener() {
            @Override
            public void changeText(String sub) {
                etReceiptInput.setText(sub);
            }

            @Override
            public void confirm() {

                String amount = etReceiptInput.getText().toString();
                if (NumberUtils.isGreaterThanZero(amount)) {
                    Intent intent = new Intent(mActivity, SelectCoinActivity.class);
                    intent.putExtra("fiatAmount", amount);
                    startActivity(intent);
                }
            }
        });


    }



    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        tvReceiptAmountUnit.setText(App.getInstance().getCurrencyCode());
    }



    @OnClick(R.id.rl_income_item)
    public void onClick(View view) {
        MainFragment fragment = (MainFragment) getParentFragment();
        fragment.onClickTransactions();
    }
}
