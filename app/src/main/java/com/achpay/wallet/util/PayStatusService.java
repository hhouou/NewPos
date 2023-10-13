package com.achpay.wallet.util;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import com.achpay.wallet.BuildConfig;
import com.achpay.wallet.di.module.BaseInterceptor;
import com.achpay.wallet.di.module.HttpModule;
import com.achpay.wallet.model.http.api.PosApi;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PayStatusService extends IntentService {

    private final String TAG =  "PayStatusService";

    /**
     * @deprecated
     */
    public PayStatusService() {
        super("PayStatusService");

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        Log.v(TAG,"启动");

        String sysOrderNum = intent.getStringExtra("sysOrderNum");

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new BaseInterceptor())
                .build();

        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
        Request request = new Request.Builder()
                .url(BuildConfig.API_BASE_URL+"app/bill/orderStatus")
                .post(RequestBody.create(mediaType, sysOrderNum))
                .header("User-Agent", "OkHttp Example")
                .build();



            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.v(TAG,"失败"+e.getMessage());

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                   String json = response.body().toString();
                    Log.v(TAG,"成功"+json);

                }
            });


    }
}
