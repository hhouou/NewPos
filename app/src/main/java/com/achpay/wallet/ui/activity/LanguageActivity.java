package com.achpay.wallet.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.achpay.wallet.R;
import com.achpay.wallet.base.SimpleActivity;
import com.achpay.wallet.model.http.response.LanguageInfo;
import com.achpay.wallet.ui.main.MainActivity;
import com.achpay.wallet.util.SystemUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @desc 语言设置
 * Created by hy on 21/10/13.
 */
public class LanguageActivity extends SimpleActivity {

    @BindView(R.id.rv_recycler)
    RecyclerView rvRecycler;
    private BaseQuickAdapter mAdapter;
    Handler mHandler = new Handler(Looper.myLooper());
    private int languagePosition;


    @Override
    protected int getLayout() {
        return R.layout.activity_languahge;
    }

    @Override
    protected void initEventAndData() {



        String language = SystemUtil.getAppLanguage(this);
        ArrayList<LanguageInfo> mLanguages = new ArrayList<>();
        mLanguages.add(new LanguageInfo(getString(R.string.user_language_english), TextUtils.equals(language, getString(R.string.user_language_english))));
        mLanguages.add(new LanguageInfo(getString(R.string.user_language_indonesian), TextUtils.equals(language, getString(R.string.user_language_indonesian))));
        mLanguages.add(new LanguageInfo(getString(R.string.user_language_español), TextUtils.equals(language, getString(R.string.user_language_español))));
        mLanguages.add(new LanguageInfo(getString(R.string.user_language_português), TextUtils.equals(language, getString(R.string.user_language_português))));

        rvRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new BaseQuickAdapter<LanguageInfo, BaseViewHolder>(R.layout.item_setting_language) {
            @Override
            protected void convert(BaseViewHolder helper, LanguageInfo item) {
                if (item.checked)languagePosition = helper.getLayoutPosition();

                helper.getView(R.id.iv_select).setSelected(item.checked);

                helper.setText(R.id.tv_name, item.language);

                helper.addOnClickListener(R.id.iv_select);
            }
        };
        mAdapter.bindToRecyclerView(rvRecycler);
        mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {

                LanguageInfo language = (LanguageInfo) mAdapter.getItem(languagePosition);
                LanguageInfo curLanguage = (LanguageInfo) mAdapter.getItem(position);

                language.setChecked(false);
                curLanguage.setChecked(true);

                mAdapter.setData(languagePosition,language);
                mAdapter.setData(position,curLanguage);

                languagePosition = position;

                mAdapter.notifyItemChanged(languagePosition);
                mAdapter.notifyItemChanged(position);
            }
        });


        mAdapter.setNewData(mLanguages);

    }

    public void confirm(View view) {
        LanguageInfo languageInfo = (LanguageInfo) mAdapter.getItem(languagePosition);
        SystemUtil.setLanguage(languageInfo.getLanguage());
        SystemUtil.updateLocale(mContext);

//        mHandler.postDelayed(() -> {
            Intent intent = new Intent(mContext, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
//        }, 100);

    }

}
