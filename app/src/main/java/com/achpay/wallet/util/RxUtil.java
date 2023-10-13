package com.achpay.wallet.util;

import com.achpay.wallet.R;
import com.achpay.wallet.app.App;
import com.achpay.wallet.di.module.ExceptionHandle;
import com.achpay.wallet.di.module.ResponseThrowable;
import com.achpay.wallet.model.http.response.Package;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 95 on 2017/4/29.
 */

public class RxUtil {


    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer unifySchedulerHelper() {
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return (Flowable<T>) observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .map(new HandleFuc());
//                        .onErrorResumeNext(new HttpResponseFunc());
            }
        };
    }

    public static class HttpResponseFunc<T> implements Function<Throwable, Observable<T>> {
        @Override
        public Observable<T> apply(Throwable e) {

            ResponseThrowable rt = ExceptionHandle.handleException(e);

            ToastUtil.shortShow(rt.message);

            return Observable.error(ExceptionHandle.handleException(e));
        }
    }

    public static class HandleFuc<T> implements Function<Package<T>, T> {
        @Override
        public T apply(Package<T> response) {
            if (response.getCode().equals("000000")) {
                return response.getData() == null ? (T) new Object() : response.getData();
            } else {
                throw new RuntimeException(getMessage(response.getMsg(),response.getCode()));
            }

        }
    }


    private static String getMessage(String message, String code) {
        switch (code) {
            case "10000":
                return App.getInstance().getString(R.string.error_merchant_not_exist);
            case "90001":
                return App.getInstance().getString(R.string.error_signature_error);
            case "99999":
                return App.getInstance().getString(R.string.error_request_failed);
            case "201":
                return App.getInstance().getString(R.string.error_login_again);
            case "10008":
                return App.getInstance().getString(R.string.error_wrong_password);
        }
        return message;
    }



}
