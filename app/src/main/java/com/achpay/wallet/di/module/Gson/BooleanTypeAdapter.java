package com.achpay.wallet.di.module.Gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**	
 */	
	
public class BooleanTypeAdapter extends TypeAdapter<Boolean> {
	
    @Override
    public void write(JsonWriter out, Boolean value) throws IOException {
        if (value == null) {	
            out.nullValue();	
        } else {	
            out.value(value);	
        }	
    }	
	
    //in.nextString().equalsIgnoreCase("1")，如果想转换字符串"1"	
    @Override
    public Boolean read(JsonReader in) throws IOException {
        JsonToken peek = in.peek();
        switch (peek) {	
            case NULL:	
                in.nextNull();	
                return false;	
            case NUMBER:	
                return in.nextInt() == 1;	
            case BOOLEAN:	
                return in.nextBoolean();	
            case STRING:	
                return Boolean.parseBoolean(in.nextString());
            default:	
                throw new IllegalStateException("Expected BOOLEAN or NUMBER but was " + peek);
        }	
    }	
}	
