package com.achpay.wallet.di.module;

/**
 *
 */

public class ResponseThrowable extends Throwable {
    public int code;
    public String message;

    public ResponseThrowable(Throwable throwable, int code) {
        super(throwable);
        this.code = code;
    }
}
