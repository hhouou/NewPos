package com.achpay.wallet.ui.activity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.achpay.wallet.BuildConfig;
import com.achpay.wallet.R;
import com.achpay.wallet.base.SimpleActivity;
import com.achpay.wallet.util.SoftKeyBoardListener;
import com.achpay.wallet.util.SystemUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by  on 2021/10/10.
 */

public class SignUpActivity extends SimpleActivity {

    @BindView(R.id.webview_content)
    WebView mWebView;
    @BindView(R.id.webview_layout)
    LinearLayout webviewLayout;


    private WebSettings mWebSettings;
    private SoftKeyBoardListener keyBoardListener;


    @Override
    protected int getLayout() {
        return R.layout.activity_sign_up;
    }

    @Override
    protected void initEventAndData() {
        initWebview();
        mWebView.loadUrl(BuildConfig.SIGN_UP_URL+"foundation-gateway/alchemypay/register.html");
        initKeyBoardListener();
    }


    private void initKeyBoardListener() {
        if (keyBoardListener == null) {
            keyBoardListener = new SoftKeyBoardListener(webviewLayout);
            keyBoardListener.setOnSoftKeyBoardChangeListener(new SoftKeyBoardListener.OnSoftKeyBoardChangeListener() {
                @Override
                public void onKeyBoardShow(int height) {
                    int keyboardHeight = SystemUtil.px2dp(SignUpActivity.this, height);
                    mWebView.loadUrl("javascript:onKeyBoardShow(" + keyboardHeight + ")");
                }

                @Override
                public void onKeyBoardHide(int height) {
                    mWebView.loadUrl("javascript:onKeyBoardHide()");
                }
            });
        }
    }

    @SuppressLint({"JavascriptInterface", "SetJavaScriptEnabled"})
    private void initWebview() {
        mWebSettings = mWebView.getSettings();
        mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        mWebSettings.setJavaScriptEnabled(true);
        mWebSettings.setSaveFormData(false);
        mWebSettings.setDomStorageEnabled(true);
        mWebSettings.setBlockNetworkImage(false); // 解决图片不显示
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            mWebSettings.setMediaPlaybackRequiresUserGesture(false);
        }
        mWebSettings.setUseWideViewPort(true);
        mWebSettings.setLoadWithOverviewMode(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            mWebSettings.setSafeBrowsingEnabled(false);//关闭安全浏览模式
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mWebSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);

            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String url) {
                if (url.startsWith("http")) {
                    webView.loadUrl(url);
                } else {
                    //schema 调用 此时页面可以消失掉 暂时关闭此接口
                    /*
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    */
                }
                return true;
            }

            @Override
            public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
                super.onReceivedError(webView, errorCode, description, failingUrl);
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
            }

            @Override
            public void onPageStarted(WebView webView, String url, Bitmap bitmap) {
                super.onPageStarted(webView, url, bitmap);
//                webView.loadUrl("javascript:window.QFPAY.getTitle(document.title);");
            }

            @Override
            public void onPageFinished(WebView webView, String url) {
                super.onPageFinished(webView, url);
                //cookie同步
//                CookieManager cookieManager = CookieManager.getInstance();
//                String CookieStr = cookieManager.getCookie(url);
//                Log.e("Cookies = " + CookieStr);
            }

            @Override
            public void onPageCommitVisible(WebView webView, String url) {
                super.onPageCommitVisible(webView, url);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
                super.onReceivedSslError(view, handler, error);
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                if (url.endsWith(".js") || url.endsWith(".css")) {
                    // 如果是注入到本地html中去掉这个判断
                    // 调用注入方法
                }
            }
        });

        //js和android交互,这里添加一个js的接口,以供在JavaScript里调用Android里面的方法
        mWebView.addJavascriptInterface(this, "AlchemyWallet");
    }

    @JavascriptInterface
    public void finishPage() {
        this.finish();
    }

    @JavascriptInterface
    public String getStatusBarHeight() {
        return String.valueOf(SystemUtil.px2dp(this, SystemUtil.getStatusBarHeight(mContext)));
    }

    @JavascriptInterface
    public void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
                return false;
            } else {
                this.finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.stopLoading();
            mWebView.destroy();
        }
    }


}
