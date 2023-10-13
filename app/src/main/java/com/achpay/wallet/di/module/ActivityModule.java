package com.achpay.wallet.di.module;

import android.app.Activity;

import com.achpay.wallet.di.scope.ActivityScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by 95 on 2017/4/29.
 */

@Module
public class ActivityModule {
    private Activity mActivity;
    public ActivityModule(Activity activity){
        mActivity = activity;
    }

    @Provides
    @ActivityScope
    public Activity provideActivity(){
        return mActivity;
    }
}
