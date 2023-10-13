package com.achpay.wallet.di.module;

import android.util.Log;

import com.achpay.wallet.app.App;
import com.achpay.wallet.app.Constants;
import com.achpay.wallet.ui.activity.LoginActivity;
import com.achpay.wallet.util.DecryptUtils.Base64Utils;
import com.achpay.wallet.util.DecryptUtils.FileUtils;
import com.achpay.wallet.util.DecryptUtils.RSAProvider;
import com.achpay.wallet.util.RSAUtils;
import com.achpay.wallet.util.StrUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * @Description: java类作用描述
 * @Author: cdfhy
 * @CreateDate: 2020/11/16 16:27
 * @Version: 1.0
 */
public class BaseInterceptor implements Interceptor {

    private final Charset UTF8 = Charset.forName("UTF-8");
    private final String TAG = "pay-Request";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originRequest = chain.request();
        Request request = null;
        if ("POST".equals(originRequest.method())) {
            JSONObject jsonObject = new JSONObject();
            if ((originRequest.body() instanceof FormBody)) {
                FormBody oldBody = (FormBody) originRequest.body();
                try {

                    for (int i = 0; i < oldBody.size(); i++) {
                        jsonObject.put(oldBody.name(i), oldBody.value(i));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    throw new IllegalArgumentException(TAG + "-JSONException:" + e.getMessage());
                }
                request = getRequest(originRequest, jsonObject);
            } else if (originRequest.body().contentType() == null) {

                request = getRequest(originRequest, jsonObject);

            } else if (originRequest.body().contentType().equals(MediaType.parse("application/json; charset=utf-8"))
                    || originRequest.body().contentType().equals(MediaType.parse("application/json; charset=UTF-8"))) {

                Buffer buffer = new Buffer();
                originRequest.body().writeTo(buffer);
                Charset charset = UTF8;
                MediaType contentType = originRequest.body().contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }
                String content = buffer.readString(charset);
                try {
                    jsonObject = new JSONObject(content);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Log.v(TAG, content);
                request = getRequest(originRequest, jsonObject);

            }

            Response response = chain.proceed(request);

            if (response.code()!=200)return response;

            String json = setDecryptStr(response,request);

            MediaType mediaType = response.body().contentType();
            //构造新的response
            ResponseBody newResponseBody = ResponseBody.create(mediaType, json);
            response = response.newBuilder().body(newResponseBody).build();

            response.close();

            return response;


        }

        Response response = chain.proceed(originRequest);


        return response;
    }

    private Request getRequest(Request originRequest, JSONObject jsonObject) {
        String requestBodyData = jsonObject.toString();

        try {
            String url = originRequest.url().toString();
            if (!url.contains(Constants.POST_LOGIN) &&StrUtils.isNotBlank(requestBodyData))
                requestBodyData = setEncryptParams(requestBodyData);
        } catch (Exception e) {
            e.printStackTrace();
        }


        RequestBody newBody = RequestBody.create(
                MediaType.parse("application/json; charset=utf-8"), requestBodyData);
        return originRequest.newBuilder()
                .method(originRequest.method(), newBody)
                .addHeader("token", App.getToken())
                .build();
    }

    private String setEncryptParams(String data) throws Exception {

        InputStream inPublic = App.getInstance().getResources().getAssets().open("rsa_public_key.pem");
        String publicKey = FileUtils.readString(inPublic);
        inPublic.close();

        String encryptStr = RSAUtils.encryptedDataOnJava(data, publicKey);

        return encryptStr;
    }


    private String setDecryptStr(Response response,Request originRequest) throws IOException {

        String json = response.body().string();

        String url = originRequest.url().toString();
        if (url.contains(Constants.POST_LOGIN) )return json;


        try {
            JSONObject object = new JSONObject(json);

            if (object.getString("code").equals("201")){
                App.getInstance().exitLogin(LoginActivity.class);
                return json;
            }


            //处理返回数据
            InputStream inPrivate = App.getInstance().getResources().getAssets().open("rsa_private_key.pem");
            String privateKey = FileUtils.readString(inPrivate);
            inPrivate.close();

            //读取私钥文件
            String data = object.getString("data");
            if (!StrUtils.isNotBlank(data))return json;

            String s1 = RSAUtils.decryptDataOnJava(data, privateKey);

            Object jsonStr = new JSONTokener(s1).nextValue();
            if(jsonStr instanceof JSONObject){
                JSONObject jo = (JSONObject)jsonStr;
                object.put("data", jo);

            }else if (jsonStr instanceof JSONArray){
                JSONArray ja = (JSONArray)jsonStr;
                object.put("data", ja);
            }else{
                object.put("data", jsonStr);
            }

            json = object.toString();
            Log.v(TAG, json);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }

}