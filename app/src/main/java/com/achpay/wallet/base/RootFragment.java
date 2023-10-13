package com.achpay.wallet.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.achpay.wallet.R;
import com.achpay.wallet.util.ToastUtil;

/**
 * Created by 95 on 2017/5/3.
 */

public abstract class RootFragment <T extends BasePresenter> extends BaseFragment<T> {

    private static final int STATE_MAIN = 0x00;
    private static final int STATE_LOADING = 0x01;
    private static final int STATE_ERROR = 0x02;

    //private ImageView ivLoading;
    private LinearLayout viewError;
    private FrameLayout viewLoading;
    private ViewGroup viewMain;

    private int currentState = STATE_MAIN;
    private TextView tvHint;
    private ImageView ivLoading;

    @Override
    protected void initEventAndData() {
        if (getView() == null)
            return;

        viewMain = (ViewGroup) getView().findViewById(R.id.view_main);
        if (viewMain == null){
            throw new IllegalStateException("The subclass of RootActivity must contain a View named view_main.");
        }
        if (!(viewMain.getParent() instanceof ViewGroup)){
            throw new IllegalStateException("view_main's ParentView should be a ViewGroup.");
        }
        ViewGroup parent = (ViewGroup) viewMain.getParent();
        View.inflate(mContext, R.layout.view_error,parent);
        View.inflate(mContext,R.layout.view_loading,parent);
        viewError = (LinearLayout)parent.findViewById(R.id.view_error);
        viewLoading = (FrameLayout)parent.findViewById(R.id.view_loading);
        tvHint=(TextView)viewLoading.findViewById(R.id.tv_loading_hint);
        ivLoading = (ImageView)viewLoading.findViewById(R.id.iv_loading_img);
        viewError.setVisibility(View.GONE);
        viewLoading.setVisibility(View.GONE);
        viewMain.setVisibility(View.VISIBLE);
    }

    //加载提示语
//    @Override
//    public void setLoadingHint(String conent){
//        if (tvHint==null)return;
//        tvHint.setText(conent);
//    }

    //设置加载图
//    @Override
//    public void setLoadingImg(int resId){
//        if (ivLoading==null)return;
//        ivLoading.setImageResource(resId);
//    }

    @Override
    public void stateError() {
        if (currentState == STATE_ERROR)
            return;
        hideCurrentView();
        currentState = STATE_ERROR;
        viewError.setVisibility(View.VISIBLE);
    }

    @Override
    public void stateLoading() {
        if (currentState == STATE_LOADING)
            return;
        hideCurrentView();
        currentState = STATE_LOADING;
        viewLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void stateMain() {
        if (currentState == STATE_MAIN)
            return;
        hideCurrentView();
        currentState = STATE_MAIN;
        viewMain.setVisibility(View.VISIBLE);
    }

    protected void hideCurrentView(){
        switch (currentState) {
            case STATE_MAIN:
                viewMain.setVisibility(View.GONE);
                break;
            case STATE_LOADING:
                viewLoading.setVisibility(View.GONE);
                break;
            case STATE_ERROR:
                viewError.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    public void showErrorMsg(String msg) {
        ToastUtil.shortShow(msg);
    }



}
