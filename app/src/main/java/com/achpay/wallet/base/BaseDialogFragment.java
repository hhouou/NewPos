package com.achpay.wallet.base;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseDialogFragment extends DialogFragment {

    protected View mView;

    protected Activity mActivity;
    protected Context mContext;
    protected Unbinder mUnBinder;

    protected OnItemListener onClickListener;
    protected boolean isShow = false;

    @Nullable
    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        mView = inflater.inflate(getLayout(), null);
        mUnBinder = ButterKnife.bind(this, mView);
        initData();

        getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_SEARCH)
                    return true; // pretend we've processed it
                else if (keyCode == KeyEvent.KEYCODE_DEL)
                    return false; // pretend we've processed it
                else
                    dismiss();
                return false; // pass on to be processed as normal

            }
        });


        return mView;
    }


    @Override
    public void show(FragmentManager manager, String tag) {

        if (isShow) {
            dismiss();
            return;
        }
        try {
            //在每个add事务前增加一个remove事务，防止连续的add
            manager.beginTransaction().remove(this).commit();
            super.show(manager, tag);

            isShow = true;
        } catch (Exception e) {
            //同一实例使用不同的tag会异常,这里捕获一下
            e.printStackTrace();
        }

    }

    @Override
    public int show(FragmentTransaction transaction, String tag) {
        return super.show(transaction, tag);
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        isShow = false;
        super.onDismiss(dialog);
    }


    @Override
    public void dismiss() {
        if (!isShow)return;
        if (onClickListener != null) {
            onClickListener.onCancelClick();
        }
        super.dismiss();
    }

    public boolean isShow() {
        return isShow;
    }


    @Override
    public void onDestroyView() {
        if (mUnBinder != null)
            mUnBinder.unbind();
        super.onDestroyView();
    }

    protected abstract int getLayout();

    protected abstract void initData();



    public void setOnClickListener(OnItemListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnItemListener<T> {
        void onAffirmClick(T content);

        void onCancelClick();
    }

}
