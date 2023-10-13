package com.achpay.wallet.di.module;

import android.util.Log;

import com.achpay.wallet.di.module.Gson.GsonFactory;
import com.achpay.wallet.di.module.logging.Level;
import com.achpay.wallet.di.module.logging.LoggingInterceptor;
import com.achpay.wallet.model.http.api.PosApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.Proxy;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import com.achpay.wallet.app.App;
import com.achpay.wallet.app.Constants;
import com.achpay.wallet.di.qualifier.PosUrl;
import com.achpay.wallet.model.UserManager;
import com.achpay.wallet.util.Cookie.ClearableCookieJar;
import com.achpay.wallet.util.Cookie.PersistentCookieJar;
import com.achpay.wallet.util.Cookie.SetCookieCache;
import com.achpay.wallet.util.Cookie.persistence.SharedPrefsCookiePersistor;
import com.achpay.wallet.util.SystemUtil;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.platform.Platform;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import com.achpay.wallet.BuildConfig;

/**
 * Created by 95 on 2017/4/30.
 */

@Module
public class HttpModule {

    private File httpCacheDirectory;
    private Cache cache;


    @Singleton
    @Provides
    Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    @Singleton
    @Provides
    OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

    @Singleton
    @Provides
    @PosUrl
    Retrofit providePosRetrofit(Retrofit.Builder builder, OkHttpClient client) {
        return createRetrofit(builder, client, BuildConfig.API_BASE_URL);
    }


    @Singleton
    @Provides
    OkHttpClient provideClient(OkHttpClient.Builder builder, final UserManager userManager) {
        if (httpCacheDirectory == null) {
            httpCacheDirectory = new File(Constants.PATH_CACHE);
        }

        try {
            if (cache == null) {
                cache = new Cache(httpCacheDirectory, 1024 * 1024 * 50);
            }
        } catch (Exception e) {
            Log.e("payment", e.getMessage());
        }
        ClearableCookieJar cookieJar = new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.getInstance()));
        String buildType = BuildConfig.BUILD_TYPE;
        boolean isDebug = false;
        if ("debug".equals(buildType)) {
            isDebug = true;
        }
        builder.addInterceptor(new BaseInterceptor())
                .addInterceptor(new CacheInterceptor(App.getInstance()))
                .addInterceptor(new LoggingInterceptor
                                .Builder()//构建者模式
                                .loggable(isDebug) //是否开启日志打印//TODO
                                .loggable(true) //是否开启日志打印//TODO
                                .setLevel(Level.BASIC) //打印的等级
                                .log(Platform.INFO) // 打印类型
                                .request("pay-Request") // request的Tag
                                .response("pay-Response")// Response的Tag
                                .build()
                );
        // builder.cache(cache);
        builder.connectTimeout(30, TimeUnit.SECONDS);
        builder.readTimeout(30, TimeUnit.SECONDS);
        builder.writeTimeout(30, TimeUnit.SECONDS);
        builder.cookieJar(cookieJar);
        builder.retryOnConnectionFailure(true);
        if(!BuildConfig.DEBUG)
            builder.proxy(Proxy.NO_PROXY);
        return builder.build();

    }

    @Singleton
    @Provides
    PosApi providePosService(@PosUrl Retrofit retrofit) {
        return retrofit.create(PosApi.class);
    }


    private Retrofit createRetrofit(Retrofit.Builder builder, OkHttpClient client, String url) {
        return builder
                .baseUrl(url)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(GsonFactory.factory()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

}
