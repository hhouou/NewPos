package com.achpay.wallet.ui.main;

import android.widget.ImageView;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;
import butterknife.BindView;
import butterknife.OnClick;
import com.achpay.wallet.R;
import com.achpay.wallet.app.Constants;
import com.achpay.wallet.base.BaseFragment;
import com.achpay.wallet.base.Contract.MainFragmentContract;
import com.achpay.wallet.presenter.MainFragmentPresenter;
import com.achpay.wallet.ui.fragment.MineFragment;
import com.achpay.wallet.ui.fragment.TransactionsFragment;
import com.achpay.wallet.ui.fragment.PaymentFragment;
import com.achpay.wallet.util.ToastUtil;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by 95 on 2017/9/12.
 */

public class MainFragment extends BaseFragment<MainFragmentPresenter> implements MainFragmentContract.View {


    PaymentFragment paymentFragment;
    TransactionsFragment transactionsFragment;
    MineFragment mineFragment;

    @BindView(R.id.iv_main_1)
    ImageView ivMain1;
    @BindView(R.id.tv_main_1)
    TextView tvMain1;

    @BindView(R.id.iv_main_2)
    ImageView ivMain2;
    @BindView(R.id.tv_main_2)
    TextView tvMain2;

    @BindView(R.id.iv_main_3)
    ImageView ivMain3;
    @BindView(R.id.tv_main_3)
    TextView tvMain3;



    private int hideFragment = 0;
    private int showFragment = 0;

    @Override
    protected void initInject() {
        getFragmentComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initEventAndData() {
        SupportFragment firstFragment = findChildFragment(PaymentFragment.class);

        if (firstFragment == null) {

            paymentFragment =new PaymentFragment();
            transactionsFragment =new TransactionsFragment();
            mineFragment = new MineFragment();

            loadMultipleRootFragment(R.id.view_main, 0,  paymentFragment,transactionsFragment,mineFragment);

        } else {
            paymentFragment = (PaymentFragment) firstFragment;
            transactionsFragment =findChildFragment(TransactionsFragment.class);
            mineFragment = findChildFragment(MineFragment.class);
        }



    }

    @OnClick(R.id.rl_main_1)
    public void onClickPayment() {
        ivMain1.setImageResource(R.drawable.icon_main_home_off);
        tvMain1.setTextColor(getResources().getColor(R.color.colorTitle));

        ivMain2.setImageResource(R.drawable.icon_main_transactions_no);
        tvMain2.setTextColor(getResources().getColor(R.color.colorGray));

        ivMain3.setImageResource(R.drawable.icon_main_mine_no);
        tvMain3.setTextColor(getResources().getColor(R.color.colorGray));


        showFragment = Constants.TYPE_MAIN_PAYMENT;
        changePage();
    }


    @OnClick(R.id.rl_main_2)
    public void onClickTransactions() {
        ivMain1.setImageResource(R.drawable.icon_main_home_no);
        tvMain1.setTextColor(getResources().getColor(R.color.colorGray));

        ivMain2.setImageResource(R.drawable.icon_main_transactions_off);
        tvMain2.setTextColor(getResources().getColor(R.color.colorTitle));

        ivMain3.setImageResource(R.drawable.icon_main_mine_no);
        tvMain3.setTextColor(getResources().getColor(R.color.colorGray));

        showFragment = Constants.TYPE_MAIN_TRANSACTIONS;
        changePage();
    }

    @OnClick(R.id.rl_main_3)
    public void onClickMine() {
        ivMain1.setImageResource(R.drawable.icon_main_home_no);
        tvMain1.setTextColor(getResources().getColor(R.color.colorGray));

        ivMain2.setImageResource(R.drawable.icon_main_transactions_no);
        tvMain2.setTextColor(getResources().getColor(R.color.colorGray));

        ivMain3.setImageResource(R.drawable.icon_main_mine_off);
        tvMain3.setTextColor(getResources().getColor(R.color.colorTitle));

        showFragment = Constants.TYPE_MAIN_MINE;
        changePage();
    }



    private SupportFragment getTargetFragment(int item) {
        switch (item) {
            case Constants.TYPE_MAIN_PAYMENT:
                return paymentFragment;
            case Constants.TYPE_MAIN_TRANSACTIONS:
                return transactionsFragment;
            case Constants.TYPE_MAIN_MINE:
                return mineFragment;
        }
        return paymentFragment;
    }

    private void changePage() {
        showHideFragment(getTargetFragment(showFragment), getTargetFragment(hideFragment));
        hideFragment = showFragment;

    }

    public void startBrotherFragment(SupportFragment fragment) {
        start(fragment);
    }




    boolean isfirst = false;

    @Override
    public boolean onBackPressedSupport() {
        //双击退出
        if (isfirst) {
            isfirst = false;
        } else {
            isfirst = true;
            ToastUtil.shortShow("再按一次退出程序");
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    isfirst = false;
                }
            }, 2000);
        }
        return isfirst;
    }


}
