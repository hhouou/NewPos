package com.achpay.wallet.di.module.Gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * null 替换为""
 */

public class StringTypeAdapter extends TypeAdapter<String> {
    @Override
    public void write(JsonWriter out, String value) throws IOException {
        if (value == null) {
            out.nullValue();
        } else {
            out.value(value);
        }
    }

    @Override
    public String read(JsonReader in) {
        try {
            JsonToken peek = in.peek();
            switch (peek) {
                case NULL:
                    in.nextNull();
                    return "";
                case NUMBER:
                    return String.valueOf(in.nextLong());
                case STRING:
                    return in.nextString();
                default:
                    throw new IllegalStateException("Expected String but was " + peek);
            }

        } catch (IOException e) {
            throw new IllegalStateException("Expected String but was " + e.getMessage());
        }


    }
}	
