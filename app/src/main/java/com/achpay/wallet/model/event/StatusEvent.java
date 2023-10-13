package com.achpay.wallet.model.event;

/**
 * Created by 95 on 2017/9/17.
 */

/*
* 支付状态
* */
public class StatusEvent {



    /*
    * 0:等待 1:成功  2:失败
    * */
    private int status;

    /*
    * 1 代表成功
    * */
    private int result;

    private String data;

    public StatusEvent(){}
    public StatusEvent(int status){
        this.status=status;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public int getResult() {
        return result;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
