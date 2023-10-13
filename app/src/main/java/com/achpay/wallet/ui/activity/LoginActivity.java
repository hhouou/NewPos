package com.achpay.wallet.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.achpay.wallet.R;
import com.achpay.wallet.app.AppManager;
import com.achpay.wallet.base.BaseActivity;
import com.achpay.wallet.base.Contract.LoginContract;
import com.achpay.wallet.presenter.LoginPresenter;
import com.achpay.wallet.ui.main.MainActivity;
import com.achpay.wallet.util.StrUtils;
import com.achpay.wallet.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by  on 2021/10/10.
 */

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {


    @BindView(R.id.et_account)
    EditText etAccount;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.iv_key_clear)
    ImageView ivKeyClear;
    @BindView(R.id.iv_password_visibility)
    ImageView ivPasswordVisibility;

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initEventAndData() {

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                ivKeyClear.setVisibility(!TextUtils.isEmpty(s.toString()) ? View.VISIBLE : View.GONE);
            }
        });


    }


    @Override
    public void loginSuccess() {
        startActivity(new Intent(mContext, MainActivity.class));
        finish();
    }


    @OnClick({R.id.iv_key_clear, R.id.iv_password_visibility, R.id.tv_login_button, R.id.tv_go_to_register})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_key_clear:
                etPassword.setText("");
                break;
            case R.id.iv_password_visibility:

                ivPasswordVisibility.setSelected(!ivPasswordVisibility.isSelected());

                if (ivPasswordVisibility.isSelected()) {
                    //显示
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    //隐藏
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }

                break;
            case R.id.tv_login_button:

                String name = etAccount.getText().toString();
                String password = etPassword.getText().toString();
                if (!StrUtils.isNotBlank(name)){
                    ToastUtil.shortShow(getString(R.string.login_empty_phone));
                    return;
                }
                if (!StrUtils.isNotBlank(password)){
                    ToastUtil.shortShow(getString(R.string.login_empty_password));
                    return;
                }

                mPresenter.login(name,password);


                break;
            case R.id.tv_go_to_register:
                startActivity(new Intent(mContext,SignUpActivity.class));
                break;
        }
    }
}
