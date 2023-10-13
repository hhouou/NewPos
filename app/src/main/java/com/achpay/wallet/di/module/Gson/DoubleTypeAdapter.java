package com.achpay.wallet.di.module.Gson;

import android.text.TextUtils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**	
 */	
	
public class DoubleTypeAdapter extends TypeAdapter<Double> {
    @Override
    public void write(JsonWriter out, Double value) throws IOException {
        if (value == null) {	
            out.nullValue();	
        } else {	
            out.value(value);	
        }	
    }	
	
    @Override
    public Double read(JsonReader in) throws IOException {
        JsonToken peek = in.peek();
        switch (peek) {	
            case NULL:	
                in.nextNull();	
                return 0d;	
            case NUMBER:	
                return in.nextDouble();	
            case STRING:	
                String value = in.nextString();
                if (TextUtils.isEmpty(value.trim())) {
                    return 0d;	
                }	
                return Double.valueOf(value);
            default:	
                throw new IllegalStateException("Expected NUMBER but was " + peek);
        }	
    }	
}	
