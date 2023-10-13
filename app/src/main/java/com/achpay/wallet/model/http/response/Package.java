package com.achpay.wallet.model.http.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by hy on 2017/9/14.
 */

public class Package<T> {
    @Expose
    @SerializedName("msg")
    private String msg;

    @Expose
    @SerializedName("code")
    private String code;

    @Expose
    @SerializedName("data")
    T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
