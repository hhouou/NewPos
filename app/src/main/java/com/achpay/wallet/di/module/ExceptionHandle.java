package com.achpay.wallet.di.module;

import org.apache.http.conn.ConnectTimeoutException;
import java.net.ConnectException;


/**
 * 异常拦截器
 */
public class ExceptionHandle {

    public static ResponseThrowable handleException(Throwable e) {
        ResponseThrowable ex;
//        if (e instanceof ConnectException) {
//            ex = new ResponseThrowable(e, ERROR.NETWORD_ERROR);
//            ex.message = "连接失败";
//            return ex;
//        } else if (e instanceof javax.net.ssl.SSLException) {
//            ex = new ResponseThrowable(e, ERROR.SSL_ERROR);
//            ex.message = "证书验证失败";
//            return ex;
//        } else if (e instanceof ConnectTimeoutException) {
//            ex = new ResponseThrowable(e, ERROR.TIMEOUT_ERROR);
//            ex.message = "连接超时";
//            return ex;
//        } else if (e instanceof java.net.SocketTimeoutException) {
//            ex = new ResponseThrowable(e, ERROR.TIMEOUT_ERROR);
//            ex.message = "连接超时";
//            return ex;
//        } else if (e instanceof java.net.UnknownHostException) {
//            ex = new ResponseThrowable(e, ERROR.TIMEOUT_ERROR);
//            ex.message = "主机地址未知";
//            return ex;
//        } else {
//            ex = new ResponseThrowable(e, ERROR.UNKNOWN);
//            ex.message = e.getMessage();
//            return ex;
//        }
        ex = new ResponseThrowable(e, ERROR.UNKNOWN);
        ex.message = e.getMessage();
        return ex;
    }


    /**
     * 约定异常 这个具体规则需要与服务端或者领导商讨定义
     */
    class ERROR {
        /**
         * 未知错误
         */
        public static final int UNKNOWN = 1000;
        /**
         * 解析错误
         */
        public static final int PARSE_ERROR = 1001;
        /**
         * 网络错误
         */
        public static final int NETWORD_ERROR = 1002;
        /**
         * 协议出错
         */
        public static final int HTTP_ERROR = 1003;

        /**
         * 证书出错
         */
        public static final int SSL_ERROR = 1005;

        /**
         * 连接超时
         */
        public static final int TIMEOUT_ERROR = 1006;
    }

}

