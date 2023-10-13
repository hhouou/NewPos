package com.achpay.wallet.ui.activity;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Handler;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.achpay.wallet.R;
import com.achpay.wallet.base.BaseActivity;
import com.achpay.wallet.base.Contract.CoinReceiveContract;
import com.achpay.wallet.model.event.StatusEvent;
import com.achpay.wallet.model.http.response.BillListReponse;
import com.achpay.wallet.model.http.response.PayOrderReponse;
import com.achpay.wallet.presenter.CoinReceivePresenter;
import com.achpay.wallet.util.SpUtils;
import com.achpay.wallet.util.print.WiseasyPrintHelper;
import com.achpay.wallet.util.sunmiPrint.SunmiPrintHelper;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;


public class WebviewActivity extends BaseActivity<CoinReceivePresenter> implements CoinReceiveContract.View {


    @BindView(R.id.webview)
    WebView webview;
    private Handler handler;
    private Timer timer;

    public String url;
    protected WebSettings mWebSettings;
    private String sysOrderNum,fiatAmount,qrContent,payAmount,payAmountUnit,payTime;



    @Override
    protected void initInject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initEventAndData() {

        url = getIntent().getStringExtra("url");
        sysOrderNum = getIntent().getStringExtra("sysOrderNum");
        fiatAmount = getIntent().getStringExtra("fiatAmount");
        qrContent = getIntent().getStringExtra("qrContent");
        payAmount = getIntent().getStringExtra("payAmount");
        payAmountUnit = getIntent().getStringExtra("payAmountUnit");

        webview.setBackgroundColor(getResources().getColor(R.color.white)); // 设置背景色
        webview.getBackground().setAlpha(255); // 设置填充透明度 范围：0-255
        initWebview();

        stateLoading();
        webview.loadUrl(url);

        initPrint();
    }


    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    private void initWebview() {
        mWebSettings = webview.getSettings();
//        mWebSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

//        mWebSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setSaveFormData(false);
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setBlockNetworkImage(false); // 解决图片不显示
        mWebSettings.setMediaPlaybackRequiresUserGesture(false);
//        mWebSettings.setUseWideViewPort(true);
//        mWebSettings.setLoadWithOverviewMode(true);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mWebSettings.setSafeBrowsingEnabled(false);//关闭安全浏览模式
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.setAcceptThirdPartyCookies(webview, true);//TODO 跨域cookie读取
        }

//        if (API.TEST)
        WebView.setWebContentsDebuggingEnabled(true);

        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                    webView.loadUrl(url);

                return true;
            }

            @Override
            public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
                super.onReceivedError(webView, errorCode, description, failingUrl);
                stateStop();
                String errorInfo;
                if (errorCode == WebViewClient.ERROR_CONNECT || errorCode == WebViewClient.ERROR_TIMEOUT || errorCode == WebViewClient.ERROR_HOST_LOOKUP || errorCode == WebViewClient.ERROR_UNKNOWN) {
                    errorInfo = "网络异常";
                } else if (errorCode == WebViewClient.ERROR_BAD_URL) {
                    errorInfo = "无效URL";
                } else if (errorCode == WebViewClient.ERROR_FILE_NOT_FOUND || errorCode == WebViewClient.ERROR_FILE) {
                    errorInfo = "文件找不到";
                } else {
                    errorInfo = "请求出错";
                }

//                if (AppUtil.isNetConnected()) {
//                    webView.loadUrl("file:///android_asset/error/wrong.html");
//                } else {
//                    webView.loadUrl("file:///android_asset/error/networkError.html");
//                }
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

            @Override
            public void onPageStarted(WebView webView, String url, Bitmap bitmap) {
                super.onPageStarted(webView, url, bitmap);

            }

            @Override
            public void onPageFinished(WebView webView, String url) {
                super.onPageFinished(webView, url);
                stateStop();
                callAsynchronousTask();

            }
        });
    }

    public void callAsynchronousTask() {
        handler = new Handler();
        timer = new Timer();
        TimerTask doAsynchronousTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        mPresenter.orderStatus(sysOrderNum);
                    }
                });
            }
        };
        timer.schedule(doAsynchronousTask, 0, 5000); //execute in every 10000 ms

    }


    @Override
    public void payOrderSuceess(PayOrderReponse o) {

    }

    @Override
    public void refreshStatus(StatusEvent event) {

    }

    @Override
    public void orderStatusSucess(BillListReponse order) {
        this.payTime = order.getPayTime();
        int status = 2;
        if (order.getPayStatus() == 0) return;

        if (order.getPayStatus() == 1 || order.getPayStatus() == 2) {
            status = 1;
        } else {
            status = 2;
        }

        boolean isSave = SpUtils.getBoolean(mContext, sysOrderNum, false);
        if (isSave) return;
        SpUtils.putBoolean(mContext, sysOrderNum, true);

        startPrint();

        Intent intent = new Intent(mContext, PaymentStatusActivity.class);
        intent.putExtra("status", status);
        intent.putExtra("statusStr", order.getPayStatusStr(mContext));
        intent.putExtra("payAmount", payAmount);
        intent.putExtra("payAmountUnit", payAmountUnit);
        intent.putExtra("payTime", order.getPayTime());
        startActivity(intent);

        finish();
    }


    public void initPrint() {
        if (Build.MANUFACTURER.equals("SUNMI")) {
            SunmiPrintHelper.getInstance().initSunmiPrinterService(mContext);
        } else if (Build.MANUFACTURER.toLowerCase().equals("wiseasy")) {
            WiseasyPrintHelper.getInstance().initWiseasyPrinterService(mContext);

        }
    }


    private void startPrint() {


        if (Build.MANUFACTURER.equals("SUNMI")) {
            SunmiPrintHelper.getInstance().printOrder(mContext, sysOrderNum, fiatAmount,payAmountUnit,payTime);
        } else if (Build.MANUFACTURER.toLowerCase().equals("wiseasy")) {
            WiseasyPrintHelper.getInstance().printOrder(mContext, sysOrderNum, fiatAmount,payAmountUnit,payTime);
        }

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Build.MANUFACTURER.equals("SUNMI")) {
                    SunmiPrintHelper.getInstance().printOrder(mContext, sysOrderNum, fiatAmount,payAmountUnit,payTime);
                } else if (Build.MANUFACTURER.toLowerCase().equals("wiseasy")) {
                    WiseasyPrintHelper.getInstance().printOrder(mContext, sysOrderNum, fiatAmount,payAmountUnit,payTime);
                }

            }
        }, 2000);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (timer != null) timer.cancel();
    }

}