package com.achpay.wallet.ui.activity;

import android.content.Intent;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.achpay.wallet.R;
import com.achpay.wallet.app.App;
import com.achpay.wallet.base.BaseActivity;
import com.achpay.wallet.base.Contract.SettingContract;
import com.achpay.wallet.model.http.response.FiatListReponse;
import com.achpay.wallet.presenter.SettingPresenter;
import com.achpay.wallet.ui.main.MainActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @desc 设置
 * Created by hy on 21/10/13.
 */
public class SettngActivity extends BaseActivity<SettingPresenter> implements SettingContract.View {

    @BindView(R.id.rv_recycler)
    RecyclerView rvRecycler;
    private BaseQuickAdapter mAdapter;
    private int selectPosition = -1;
    private String currencyCode="";

    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initEventAndData() {

        rvRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new BaseQuickAdapter<FiatListReponse, BaseViewHolder>(R.layout.item_setting_coin) {
            @Override
            protected void convert(BaseViewHolder helper, FiatListReponse item) {

                if (item.getDefaults()==1)selectPosition = helper.getLayoutPosition();
                helper.getView(R.id.iv_select).setSelected(item.getDefaults()==1);

                StringBuilder builder = new StringBuilder();
                String[] strArray = item.getCurrencyEnCode().split(" ");
                for (String s : strArray) {
                    String cap = s.substring(0, 1).toUpperCase() + s.substring(1);
                    builder.append(cap + " ");
                }

                helper.setText(R.id.tv_coin_name, builder.toString());
                helper.setText(R.id.tv_coin_full_name, "("+item.getCurrencyCode()+")");

                helper.addOnClickListener(R.id.iv_select);
            }
        };
        mAdapter.bindToRecyclerView(rvRecycler);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                if (selectPosition==-1)return;
                if (selectPosition == position)return;
                FiatListReponse fiat = (FiatListReponse) mAdapter.getItem(position);
                FiatListReponse fiatOld = (FiatListReponse) mAdapter.getItem(selectPosition);
                fiat.setDefaults(1);
                fiatOld.setDefaults(0);

                mAdapter.setData(position,fiat);
                mAdapter.setData(selectPosition,fiatOld);

                mAdapter.notifyItemChanged(position);
                mAdapter.notifyItemChanged(selectPosition);
            }
        });

        mPresenter.fiatList();
    }

    @OnClick(R.id.tv_confirm)
    public void onClick() {
        FiatListReponse fiat = (FiatListReponse) mAdapter.getItem(selectPosition);
        currencyCode = fiat.getCurrencyCode();
        mPresenter.fiatEdit(fiat.getId());
    }

    @Override
    public void getFiatListSeccess(List<FiatListReponse> list) {
        mAdapter.setNewData(list);

        for (FiatListReponse fiat:list){
            if (fiat.getDefaults()==1){
                App.setCurrencyCode(fiat.getCurrencyCode());
            }
        }


    }

    @Override
    public void fiatEditSeccess(Object o) {
        App.setCurrencyCode(currencyCode);

        Intent intent = new Intent(mContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
