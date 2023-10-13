package com.achpay.wallet.model.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import com.achpay.wallet.app.App;
import com.achpay.wallet.app.Constants;

/**
 * Created by 95 on 2017/4/30.
 */

public class ImplPreferencesHelper implements PreferencesHelper {
    private final SharedPreferences mSPrefs;
    private static final String SHARED_PREFERENCES_NAME = "my_sp";


    @Inject
    public ImplPreferencesHelper() {
        mSPrefs = App.getInstance().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }


    @Override
    public String getUserReplyContent() {
        return mSPrefs.getString(Constants.SP_USERREPLY_CONTENT, "");
    }




}

