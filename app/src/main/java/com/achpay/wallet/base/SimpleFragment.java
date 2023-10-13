package com.achpay.wallet.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by 95 on 2017/5/2.
 */

public abstract class SimpleFragment extends SupportFragment {
    protected View mView;
    protected Activity mActivity;
    protected Context mContext;
    protected Unbinder mUnBinder;

    @Nullable
    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bSavedInstanceState) {
        mView = inflater.inflate(getLayout(), null);
        mUnBinder = ButterKnife.bind(this, mView);


        return mView;
    }



    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initEventAndData();
    }

    protected abstract int getLayout();

    protected abstract void initEventAndData();

    public int dip2px(float dpValue) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
