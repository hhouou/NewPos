package com.achpay.wallet.di.module.Gson;


import android.os.Build;
import android.util.Base64;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.achpay.wallet.app.App;
import com.achpay.wallet.util.DecryptUtils.Base64Utils;
import com.achpay.wallet.util.DecryptUtils.FileUtils;
import com.achpay.wallet.util.DecryptUtils.RSAProvider;
import com.achpay.wallet.util.RSAUtils;
import com.achpay.wallet.util.StrUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.io.InputStream;


public class ResponseTypeAdapterFactory implements TypeAdapterFactory {


    public static final String ERROR_CODE = "ERROR_CODE ";
    public static final String ERROR_CODE_SUCCESS = "ERROR_CODE_SUCCESS";
    public static final String RESULT = "RESULT ";
    public static final String ERROR_MSG = "ERROR_MSG ";
    public static final String SEPARATOR = "&#-SEPARATOR-#&";


    public ResponseTypeAdapterFactory() {
    }

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {

        final TypeAdapter<T> delegateAdapter = gson.getDelegateAdapter(this, type);
        final TypeAdapter<JsonElement> jsonElementAdapter = gson.getAdapter(JsonElement.class);

        return new TypeAdapter<T>() {

            @Override
            public void write(JsonWriter out, T value) throws IOException {
                delegateAdapter.write(out, value);
            }

            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public T read(JsonReader in) throws IOException {

                String msg = "";
                int code = 0;
                int status = 0;
                try {

                    if (in.peek() == JsonToken.NULL) {
                        in.nextNull();
                        return null;
                    }
                    JsonElement jsonElement = jsonElementAdapter.read(in);
                    if (jsonElement.isJsonObject()) {
                        JsonObject jsonObject = jsonElement.getAsJsonObject();

//                        if (jsonObject.has("data")) {
//
//                            try {
//                                InputStream inPrivate = App.getInstance().getResources().getAssets().open("rsa_private_key.pem");
//                                String privateKey = FileUtils.readString(inPrivate);
//                                InputStream inPublic = App.getInstance().getResources().getAssets().open("rsa_public_key.pem");
//                                String publicKey = FileUtils.readString(inPublic);
//
//                                //读取私钥文件
////                                String privateKeyString = StrUtils.getKeyFromCRT("rsa_private_key.pem");
//                                String data = msg = jsonObject.get("data").getAsString();
//                                String s1 = RSAUtils.decryptDataOnJava(data, privateKey);
//
//                                jsonObject.addProperty("data",s1);
//
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                            }
//
//                        }


//                        if (jsonObject.has("message")) {
//                            msg = jsonObject.get("message").getAsString();
//                        }
//                        if (jsonObject.has("status")) {
//                            status = jsonObject.get("status").getAsInt();
//                        }
//                        if (jsonObject.has("code")) {
//                            code = jsonObject.get("code").getAsInt();
//                            if (status == 0) {
//                                if (TextUtils.isEmpty(App.getUser_no()) && msg.contains("token")){
//                                    return null;
//                                }
//                                if (code == 40101 || code == 40301) {
//                                    if (TextUtils.isEmpty(App.getUser_no())) {
//                                        return null;
//                                    }
//                                }
//                            }
//                        }


                        if (jsonObject.has(ERROR_CODE) && jsonObject.has(ERROR_MSG)) {
                            if (jsonObject.get(ERROR_CODE).getAsString().equals(ERROR_CODE_SUCCESS)) {
                                if (jsonObject.has(RESULT)) {
                                    if (jsonObject.get(RESULT).isJsonNull()) {
                                        return delegateAdapter.fromJsonTree(new JsonObject());
                                    }
                                    return delegateAdapter.fromJsonTree(jsonObject.get(RESULT));
                                } else {
                                    return delegateAdapter.fromJsonTree(new JsonObject());
                                }
                            } else {
                                throw new IOException(jsonObject.get(ERROR_CODE).getAsString() + SEPARATOR + jsonObject.get(ERROR_MSG).getAsString());
                            }
                        }
                    }
                    return delegateAdapter.fromJsonTree(jsonElement);

                } catch (Exception e) {
//                    if (status == 0) {
//                        if (TextUtils.isEmpty(App.getUser_no()) && msg.contains("token")){
//                            return null;
//                        }
//                        if (code == 40101 || code == 40301) {
//                            if (TextUtils.isEmpty(App.getUser_no())) {
//                                return null;
//                            }
//                            App.getInstance().exitLogin();
//                        }
//                    }
                    throw new IOException(msg);
                }

            }
        }.nullSafe();
    }

}
