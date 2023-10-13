package com.achpay.wallet.ui.fragment;


import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.achpay.wallet.R;
import com.achpay.wallet.app.App;
import com.achpay.wallet.app.AppManager;
import com.achpay.wallet.base.BaseFragment;
import com.achpay.wallet.base.Contract.MineContract;
import com.achpay.wallet.model.http.response.AccountReponse;
import com.achpay.wallet.presenter.MinePresenter;
import com.achpay.wallet.ui.activity.AboutActivity;
import com.achpay.wallet.ui.activity.LanguageActivity;
import com.achpay.wallet.ui.activity.LoginActivity;
import com.achpay.wallet.ui.activity.SettngActivity;
import com.achpay.wallet.ui.activity.TransactionSummaryActivity;
import com.achpay.wallet.ui.main.MainActivity;
import com.achpay.wallet.ui.rate.ExchangeRateActivity;
import com.achpay.wallet.util.NumberUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 95 on 2021/10/13.
 * 个人中心
 */

public class MineFragment extends BaseFragment<MinePresenter> implements MineContract.View {

    @BindView(R.id.tv_merchant_name)
    TextView tvMerchantName;
    @BindView(R.id.tv_merchant_code)
    TextView tvMerchantCode;
    @BindView(R.id.tv_base_account)
    TextView tvBaseAccount;
    @BindView(R.id.tv_pending_account)
    TextView tvPendingAccount;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;

    }


    @Override
    protected void initEventAndData() {

        tvMerchantName.setText(App.getMerchantName());
        tvMerchantCode.setText(App.getMerchantCode());
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
        mPresenter.account();
    }

    @OnClick({R.id.ll_setting, R.id.ll_about,R.id.tv_sign_out,R.id.ll_language,R.id.ll_exchange_rate,R.id.ll_transaction_summary})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_setting:
                startActivity(new Intent(mActivity, SettngActivity.class));
                break;
            case R.id.ll_about:
                startActivity(new Intent(mActivity, AboutActivity.class));
                break;
            case R.id.tv_sign_out:
                mPresenter.signOut();
                break;
            case R.id.ll_language:
                startActivity(new Intent(mActivity, LanguageActivity.class));
                break;
            case R.id.ll_exchange_rate:
                startActivity(new Intent(mActivity, ExchangeRateActivity.class));
                break;
            case R.id.ll_transaction_summary:
                startActivity(new Intent(mActivity, TransactionSummaryActivity.class));
                break;
        }
    }

    @Override
    public void signOutSuceess() {
        App.getInstance().exitLogin(LoginActivity.class);
    }

    @Override
    public void accountSuceess(AccountReponse account) {
        tvBaseAccount.setText(NumberUtils.formatDouble2(account.getUsableAmountStr()));
        tvPendingAccount.setText(NumberUtils.formatDouble2(account.getWaitAmountStr()));
    }


}
