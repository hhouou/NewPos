package com.achpay.wallet.di.module;

import android.app.Activity;

import androidx.fragment.app.Fragment;

import com.achpay.wallet.di.scope.FragmentScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by 95 on 2017/5/3.
 */

@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment){
        mFragment = fragment;
    }

    @Provides
    @FragmentScope
    public Activity provideActivity(){
        return mFragment.getActivity();
    }
}
