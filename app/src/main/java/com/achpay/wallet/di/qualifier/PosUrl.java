package com.achpay.wallet.di.qualifier;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import javax.inject.Qualifier;

/**
 * Created by 95 on 2017/4/30.
 */

@Qualifier
@Documented
@Retention(RUNTIME)
public @interface PosUrl {
}
