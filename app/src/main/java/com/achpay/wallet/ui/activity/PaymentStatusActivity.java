package com.achpay.wallet.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.achpay.wallet.R;
import com.achpay.wallet.app.AppManager;
import com.achpay.wallet.base.SimpleActivity;
import com.achpay.wallet.component.ImageLoader;
import com.achpay.wallet.ui.main.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @desc 支付返回的状态
 * Created by hy on 21/10/13.
 */
public class PaymentStatusActivity extends SimpleActivity {


    @BindView(R.id.iv_img_status)
    ImageView ivImgStatus;
    @BindView(R.id.tv_status_des)
    TextView tvStatusDes;
    @BindView(R.id.ll_1)
    RelativeLayout ll1;
    @BindView(R.id.tv_amount)
    TextView tvAmount;
    @BindView(R.id.ll_layout_amount)
    LinearLayout llLayoutAmount;
    @BindView(R.id.tv_receipt)
    TextView tvReceipt;
    @BindView(R.id.ll_layout_receipt)
    LinearLayout llLayoutReceipt;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.ll_layout_address)
    LinearLayout llLayoutAddress;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.ll_layout_time)
    LinearLayout llLayoutTime;
    @BindView(R.id.tv_home)
    TextView tvHome;
    @BindView(R.id.rl_top_bg)
    RelativeLayout rlTopBg;

    @Override
    protected int getLayout() {
        return R.layout.activity_payment_status;
    }

    @Override
    protected void initEventAndData() {

        int status = getIntent().getIntExtra("status", 0);

        String statusStr = getIntent().getStringExtra("statusStr") ;
        String fiatAmount = getIntent().getStringExtra("fiatAmount") ;
        String qrContent = getIntent().getStringExtra("qrContent");
        String payAmount = getIntent().getStringExtra("payAmount");
        String payAmountUnit = getIntent().getStringExtra("payAmountUnit");
        String payTime = getIntent().getStringExtra("payTime");

        if (status==2){
            rlTopBg.setBackgroundResource(R.drawable.bg_red_angle);
            ImageLoader.load(mContext,R.drawable.icon_fail,ivImgStatus);
            tvStatusDes.setText(statusStr);
            llLayoutAddress.setVisibility(View.GONE);
            llLayoutAmount.setVisibility(View.GONE);
            llLayoutReceipt.setVisibility(View.GONE);
            tvHome.setBackgroundResource(R.drawable.bg_rect_red_corner_4);
        }else {
            rlTopBg.setBackgroundResource(R.drawable.bg_green_angle);
            ImageLoader.load(mContext,R.drawable.icon_success,ivImgStatus);
            tvStatusDes.setText(statusStr);
            tvHome.setBackgroundResource(R.drawable.bg_rect_green_corner_4);
            tvAmount.setText(payAmount+" ("+payAmountUnit+") ");


            if (!TextUtils.isEmpty(payTime)){
                llLayoutTime.setVisibility(View.VISIBLE);
                tvTime.setText(payTime);
                llLayoutReceipt.setVisibility(View.GONE);
                llLayoutAddress.setVisibility(View.GONE);
            }else {
                tvReceipt.setText(fiatAmount);
                tvAddress.setText(qrContent);
            }



        }


    }




    @OnClick({R.id.tv_home})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_home:
                AppManager.getAppManager().finishActivity();
                startActivity(new Intent(mContext, MainActivity.class));
                break;
        }
    }



}
