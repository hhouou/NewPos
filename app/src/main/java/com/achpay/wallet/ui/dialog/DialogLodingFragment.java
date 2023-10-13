package com.achpay.wallet.ui.dialog;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.achpay.wallet.R;
import com.achpay.wallet.base.BaseDialogFragment;
import com.bumptech.glide.Glide;

import butterknife.BindView;

/**
 * 加载框
 */
public class DialogLodingFragment extends BaseDialogFragment {


    @BindView(R.id.iv_loading_anim)
    ImageView ivLoadingImg;
    @BindView(R.id.tv_hint)
    TextView tvHint;


    private static DialogLodingFragment fragment;

    private Animation animation;



    public static DialogLodingFragment init(boolean isTouchCancel) {
        if (fragment == null) {
            fragment = new DialogLodingFragment();
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("isTouchCancel",isTouchCancel);
        fragment.setArguments(bundle);
        return fragment;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        boolean isTouchCancel = getArguments().getBoolean("isTouchCancel",true);

        Window window = getDialog().getWindow();
        super.onActivityCreated(savedInstanceState);
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.CENTER;
        params.dimAmount = 0.0f;
        window.setAttributes(params);
        window.setBackgroundDrawable(new BitmapDrawable());


        if (!isTouchCancel){
//            getDialog().setCancelable(false);
            getDialog().setCanceledOnTouchOutside(false);
        }


    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_loading;
    }

    @Override
    protected void initData() {
//        animation = AnimationUtils.loadAnimation(mContext, R.anim.pop_center_loading);
//        ivLoadingImg.startAnimation(animation);

        Glide.with(mContext).load(R.drawable.loading_anim).into(ivLoadingImg);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        if (animation != null) animation.cancel();
    }


}
