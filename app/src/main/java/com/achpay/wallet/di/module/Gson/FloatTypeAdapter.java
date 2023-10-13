package com.achpay.wallet.di.module.Gson;

import android.text.TextUtils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**	
 */	
	
public class FloatTypeAdapter extends TypeAdapter<Float> {
    @Override
    public void write(JsonWriter out, Float value) throws IOException {
        if (value == null) {	
            out.nullValue();	
        } else {	
            out.value(value);	
        }	
    }	
	
    @Override
    public Float read(JsonReader in) throws IOException {
        JsonToken peek = in.peek();
        switch (peek) {	
            case NULL:	
                in.nextNull();	
                return 0f;	
            case NUMBER:	
                return Float.valueOf(in.nextDouble() + "");
            case STRING:	
                String value = in.nextString();
                if (TextUtils.isEmpty(value.trim())) {
                    return 0f;	
                }	
                return Float.valueOf(value);
            default:	
                throw new IllegalStateException("Expected NUMBER but was " + peek);
        }	
    }	
}	
