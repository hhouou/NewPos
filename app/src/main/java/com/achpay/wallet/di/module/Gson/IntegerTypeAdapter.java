package com.achpay.wallet.di.module.Gson;

import android.text.TextUtils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**	
 */	
	
public class IntegerTypeAdapter extends TypeAdapter<Integer> {
    @Override
    public void write(JsonWriter out, Integer value) throws IOException {
        if (value == null) {	
            out.nullValue();	
        } else {	
            out.value(value);	
        }	
    }	
	
    @Override
    public Integer read(JsonReader in) throws IOException {
        JsonToken peek = in.peek();
        switch (peek) {	
            case NULL:	
                in.nextNull();	
                return 0;	
            case NUMBER:	
                return in.nextInt();	
            case STRING:	
                String value = in.nextString();
                if (TextUtils.isEmpty(value.trim())) {
                    return 0;	
                }	
                return Integer.valueOf(value);
            default:	
                throw new IllegalStateException("Expected NUMBER or String but was " + peek);
        }	
    }	
}	
