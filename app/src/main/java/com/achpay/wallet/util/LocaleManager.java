package com.achpay.wallet.util;

import android.content.Context;

public class LocaleManager {

    Context context;

    public LocaleManager(Context context) {
        this.context = context;
    }

    public Context setLocale(Context context) {
        return SystemUtil.updateLocale(context);
    }
}
