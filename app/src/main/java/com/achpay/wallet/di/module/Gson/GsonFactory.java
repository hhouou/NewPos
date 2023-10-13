package com.achpay.wallet.di.module.Gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 *
 */

public class GsonFactory {
    public static Gson factory() {
        Gson gson = new GsonBuilder()
                .registerTypeAdapterFactory(new ResponseTypeAdapterFactory())
                .registerTypeAdapter(Integer.class, new IntegerTypeAdapter())
                .registerTypeAdapter(int.class, new IntegerTypeAdapter())
                .registerTypeAdapter(Float.class, new FloatTypeAdapter())
                .registerTypeAdapter(float.class, new FloatTypeAdapter())
                .registerTypeAdapter(Double.class, new DoubleTypeAdapter())
                .registerTypeAdapter(double.class, new DoubleTypeAdapter())
                .registerTypeAdapter(Boolean.class, new BooleanTypeAdapter())
                .registerTypeAdapter(boolean.class, new BooleanTypeAdapter())
                .registerTypeAdapter(Object.class, new StringTypeAdapter())
                .create();

        return gson;
    }
}	
