package com.achpay.wallet.util.print;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.RemoteException;
import android.text.TextUtils;
import android.widget.Toast;

import com.achpay.wallet.R;
import com.achpay.wallet.app.App;
import com.achpay.wallet.model.http.response.PayOrderReponse;
import com.achpay.wallet.model.http.response.TradeSummaryReponse;
import com.achpay.wallet.util.TimeUtils;
import com.achpay.wallet.util.sunmiPrint.ESCUtil;
import com.sunmi.peripheral.printer.ExceptionConst;
import com.sunmi.peripheral.printer.InnerLcdCallback;
import com.sunmi.peripheral.printer.InnerPrinterCallback;
import com.sunmi.peripheral.printer.InnerPrinterException;
import com.sunmi.peripheral.printer.InnerPrinterManager;
import com.sunmi.peripheral.printer.InnerResultCallback;
import com.sunmi.peripheral.printer.SunmiPrinterService;
import com.sunmi.peripheral.printer.WoyouConsts;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import wangpos.sdk4.libbasebinder.Printer;

/**
 * <pre>
 *      This class is used to demonstrate various printing effects
 *      Developers need to repackage themselves, for details please refer to
 *      http://sunmi-ota.oss-cn-hangzhou.aliyuncs.com/DOC/resource/re_cn/Sunmiprinter%E5%BC%80%E5%8F%91%E8%80%85%E6%96%87%E6%A1%A31.1.191128.pdf
 *  </pre>
 *
 * @author kaltin
 * @since create at 2020-02-14
 */
public class WiseasyPrintHelper {


    private static WiseasyPrintHelper helper;
    private Printer mPrinter;

    private WiseasyPrintHelper() {
    }

    public static WiseasyPrintHelper getInstance() {
        if (helper == null) helper = new WiseasyPrintHelper();
        return helper;
    }


    public void initWiseasyPrinterService(Context context) {
        new Thread() {
            @Override
            public void run() {
                mPrinter = new Printer(context);

                try {
                    mPrinter.setPrintType(0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    /**
     * deInit sunmi print service
     */
    public void deInitWiseasyPrinterService(Context context) {

    }


    /**
     * 答应汇总单
     */
    public void printSummary(Context mContext, String startTime, String endTime, TradeSummaryReponse reponse) {
        new Thread() {
            @Override
            public void run() {
                try {
                    wiseasyPrint3(mContext, startTime, endTime, reponse);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    public void printOrder(Context context, PayOrderReponse payOrder, String coinName) {
        new Thread() {
            @Override
            public void run() {
                try {
                    wiseasyPrint(context, payOrder, coinName);
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    public void printOrder(Context context, String sysOrderNum, String fiatAmount,String payAmountUnit,String payTime) {
        new Thread() {
            @Override
            public void run() {
                try {
                    wiseasyPrint(context, sysOrderNum, fiatAmount,payAmountUnit,payTime);
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    public void printExchangeRate(Context context, double fiatAmount, String fiatName, double coinAmount, String coinName) {
        new Thread() {
            @Override
            public void run() {
                try {
                    wiseasyPrint2(context, fiatAmount, fiatName, coinAmount + "", coinName);
                } catch (RemoteException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }


    private void wiseasyPrint(Context mContext, PayOrderReponse payOrder, String coinName) throws RemoteException, IOException {
        int result = -1;

        result = mPrinter.printInit();
        mPrinter.clearPrintDataCache();

        if (result != 0) return;

        String merchantName = App.getMerchantName();
        String merchantCode = App.getMerchantCode();
        String payAmpunt = payOrder.getPayAmount();
        String payAmountUnit = payOrder.getPayAmountUnit();
        String payAcount = payOrder.getPayAcount();
        String payAcounttUnit = payOrder.getPayAcountUnit();
        String orderNum = payOrder.getSysOrderNum();
        String createTime = payOrder.getOrderCtreateTime();


        if (!TextUtils.equals(merchantCode, "ME1653973679011")) {
            Bitmap bitmap1 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_print_logo_qpin);
            result = mPrinter.printImageBase(bitmap1, 310, 70, Printer.Align.CENTER, 0);
            result = mPrinter.printString("  ", 30, Printer.Align.CENTER, false, false);
        }

        Bitmap bitmap2 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_print_logo);
        result = mPrinter.printImageBase(bitmap2, 310, 70, Printer.Align.CENTER, 0);
        result = mPrinter.printString("  ", 30, Printer.Align.CENTER, false, false);


        Bitmap bitmap3 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_print_logo_epay);
        result = mPrinter.printImageBase(bitmap3, 310, 70, Printer.Align.CENTER, 0);


        if (coinName.contains("Binance")) {
            result = mPrinter.printString("  ", 30, Printer.Align.CENTER, false, false);
            Bitmap bitmap4 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_print_logo_binance);
            result = mPrinter.printImageBase(bitmap4, 310, 70, Printer.Align.CENTER, 0);
        }


        if (TextUtils.equals(merchantCode, "ME1653973679011")) {
            result = mPrinter.printString("  ", 30, Printer.Align.CENTER, false, false);
            Bitmap bitmap5 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_print_logo_artaverse);
            result = mPrinter.printImageBase(bitmap5, 310, 70, Printer.Align.CENTER, 0);
        }


        result = mPrinter.printString("  ", 30, Printer.Align.CENTER, false, false);
        result = mPrinter.print2StringInLine(mContext.getString(R.string.print_merchant), "Welling Supermarket", 2.0f, Printer.Font.DEFAULT, 20, Printer.Align.LEFT, false, false, false);
//        result = mPrinter.print2StringInLine("MERCHANT:", merchantName, 2.0f, Printer.Font.DEFAULT, 20, Printer.Align.LEFT, false, false, false);
        result = mPrinter.print2StringInLine(mContext.getString(R.string.print_merchant_no), merchantCode, 2.0f, Printer.Font.DEFAULT, 20, Printer.Align.LEFT, false, false, false);
        result = mPrinter.print2StringInLine(mContext.getString(R.string.print_fiat_amount), payAmpunt + " " + payAmountUnit, 2.0f, Printer.Font.DEFAULT, 20, Printer.Align.LEFT, false, false, false);
        result = mPrinter.print2StringInLine(mContext.getString(R.string.print_payment_method), coinName, 2.0f, Printer.Font.DEFAULT, 20, Printer.Align.LEFT, false, false, false);

        if (payAcount.length() > 12) {
            result = mPrinter.print2StringInLine(mContext.getString(R.string.print_digital_amount), "", 2.0f, Printer.Font.DEFAULT, 20, Printer.Align.LEFT, false, false, false);

            result = mPrinter.print2StringInLine("", payAcount + " " + payAcounttUnit, 2.0f, Printer.Font.DEFAULT, 20, Printer.Align.LEFT, false, false, false);
        } else {
            result = mPrinter.print2StringInLine(mContext.getString(R.string.print_digital_amount), payAcount + " " + payAcounttUnit, 2.0f, Printer.Font.DEFAULT, 20, Printer.Align.LEFT, false, false, false);
        }

        result = mPrinter.print2StringInLine(mContext.getString(R.string.print_ordernum), orderNum, 2.0f, Printer.Font.DEFAULT, 20, Printer.Align.LEFT, false, false, false);
        result = mPrinter.print2StringInLine(mContext.getString(R.string.print_date), createTime, 2.0f, Printer.Font.DEFAULT, 20, Printer.Align.LEFT, false, false, false);

        result = mPrinter.printString("  ", 30, Printer.Align.CENTER, false, false);

        Bitmap bitmapQR = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_website_qr);
        result = mPrinter.printImageBase(bitmapQR, 200, 200, Printer.Align.CENTER, 0);

        result = mPrinter.printString("  ", 20, Printer.Align.CENTER, false, false);


        result = mPrinter.printString("--------------------------------------------", 30, Printer.Align.CENTER, false, false);
        result = mPrinter.printString("  ", 70, Printer.Align.CENTER, false, false);


        result = mPrinter.printFinish();


    }


    private void wiseasyPrint(Context mContext, String sysOrderNum, String fiatAmount,String payAmountUnit,String payTime) throws RemoteException, IOException {
        int result = -1;

        result = mPrinter.printInit();
        mPrinter.clearPrintDataCache();

        if (result != 0) return;

        String merchantName = App.getMerchantName();
        String merchantCode = App.getMerchantCode();



        if (!TextUtils.equals(merchantCode, "ME1653973679011")) {
            Bitmap bitmap1 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_print_logo_qpin);
            result = mPrinter.printImageBase(bitmap1, 310, 70, Printer.Align.CENTER, 0);
            result = mPrinter.printString("  ", 30, Printer.Align.CENTER, false, false);
        }

        Bitmap bitmap2 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_print_logo);
        result = mPrinter.printImageBase(bitmap2, 310, 70, Printer.Align.CENTER, 0);
        result = mPrinter.printString("  ", 30, Printer.Align.CENTER, false, false);


        Bitmap bitmap3 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_print_logo_epay);
        result = mPrinter.printImageBase(bitmap3, 310, 70, Printer.Align.CENTER, 0);



        if (TextUtils.equals(merchantCode, "ME1653973679011")) {
            result = mPrinter.printString("  ", 30, Printer.Align.CENTER, false, false);
            Bitmap bitmap5 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_print_logo_artaverse);
            result = mPrinter.printImageBase(bitmap5, 310, 70, Printer.Align.CENTER, 0);
        }


        result = mPrinter.printString("  ", 30, Printer.Align.CENTER, false, false);
        result = mPrinter.print2StringInLine(mContext.getString(R.string.print_merchant), "Welling Supermarket", 2.0f, Printer.Font.DEFAULT, 20, Printer.Align.LEFT, false, false, false);
//        result = mPrinter.print2StringInLine("MERCHANT:", merchantName, 2.0f, Printer.Font.DEFAULT, 20, Printer.Align.LEFT, false, false, false);
        result = mPrinter.print2StringInLine(mContext.getString(R.string.print_merchant_no), merchantCode, 2.0f, Printer.Font.DEFAULT, 20, Printer.Align.LEFT, false, false, false);
        result = mPrinter.print2StringInLine(mContext.getString(R.string.print_fiat_amount), fiatAmount + " " + payAmountUnit, 2.0f, Printer.Font.DEFAULT, 20, Printer.Align.LEFT, false, false, false);



        result = mPrinter.print2StringInLine(mContext.getString(R.string.print_ordernum), sysOrderNum, 2.0f, Printer.Font.DEFAULT, 20, Printer.Align.LEFT, false, false, false);
        result = mPrinter.print2StringInLine(mContext.getString(R.string.print_date), payTime, 2.0f, Printer.Font.DEFAULT, 20, Printer.Align.LEFT, false, false, false);

        result = mPrinter.printString("  ", 30, Printer.Align.CENTER, false, false);

        Bitmap bitmapQR = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_website_qr);
        result = mPrinter.printImageBase(bitmapQR, 200, 200, Printer.Align.CENTER, 0);

        result = mPrinter.printString("  ", 20, Printer.Align.CENTER, false, false);


        result = mPrinter.printString("--------------------------------------------", 30, Printer.Align.CENTER, false, false);
        result = mPrinter.printString("  ", 70, Printer.Align.CENTER, false, false);


        result = mPrinter.printFinish();


    }


    private void wiseasyPrint2(Context mContext, double fiatAmount, String fiatName, String coinAmount, String coinName) throws RemoteException, IOException {
        int result = -1;

        result = mPrinter.printInit();
        mPrinter.clearPrintDataCache();

        if (result != 0) return;

        String merchantName = App.getMerchantName();
        String merchantCode = App.getMerchantCode();


        if (!TextUtils.equals(merchantCode, "ME1653973679011")) {
            Bitmap bitmap1 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_print_logo_qpin);
            result = mPrinter.printImageBase(bitmap1, 310, 70, Printer.Align.CENTER, 0);
            result = mPrinter.printString("  ", 30, Printer.Align.CENTER, false, false);
        }

        Bitmap bitmap2 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_print_logo);
        result = mPrinter.printImageBase(bitmap2, 310, 70, Printer.Align.CENTER, 0);
        result = mPrinter.printString("  ", 30, Printer.Align.CENTER, false, false);


        Bitmap bitmap3 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_print_logo_epay);
        result = mPrinter.printImageBase(bitmap3, 310, 70, Printer.Align.CENTER, 0);


        if (coinName.contains("Binance")) {
            result = mPrinter.printString("  ", 30, Printer.Align.CENTER, false, false);
            Bitmap bitmap4 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_print_logo_binance);
            result = mPrinter.printImageBase(bitmap4, 310, 70, Printer.Align.CENTER, 0);
        }


        if (TextUtils.equals(merchantCode, "ME1653973679011")) {
            result = mPrinter.printString("  ", 30, Printer.Align.CENTER, false, false);
            Bitmap bitmap5 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_print_logo_artaverse);
            result = mPrinter.printImageBase(bitmap5, 310, 70, Printer.Align.CENTER, 0);
        }


        result = mPrinter.printString("  ", 30, Printer.Align.CENTER, false, false);
        result = mPrinter.print2StringInLine(mContext.getString(R.string.print_merchant), "Welling Supermarket", 2.0f, Printer.Font.DEFAULT, 20, Printer.Align.LEFT, false, false, false);
        result = mPrinter.print2StringInLine(mContext.getString(R.string.print_merchant_no), merchantCode, 2.0f, Printer.Font.DEFAULT, 20, Printer.Align.LEFT, false, false, false);
        result = mPrinter.print2StringInLine(mContext.getString(R.string.print_fiat_amount), fiatAmount + " " + fiatName, 2.0f, Printer.Font.DEFAULT, 20, Printer.Align.LEFT, false, false, false);
        result = mPrinter.print2StringInLine(mContext.getString(R.string.print_payment_method), coinName, 2.0f, Printer.Font.DEFAULT, 20, Printer.Align.LEFT, false, false, false);

        if (coinAmount.length() > 12) {
            result = mPrinter.print2StringInLine(mContext.getString(R.string.print_digital_amount), "", 2.0f, Printer.Font.DEFAULT, 20, Printer.Align.LEFT, false, false, false);

            result = mPrinter.print2StringInLine("", coinAmount + " " + coinName, 2.0f, Printer.Font.DEFAULT, 20, Printer.Align.LEFT, false, false, false);
        } else {
            result = mPrinter.print2StringInLine(mContext.getString(R.string.print_digital_amount), coinAmount + " " + coinName, 2.0f, Printer.Font.DEFAULT, 20, Printer.Align.LEFT, false, false, false);
        }

        result = mPrinter.print2StringInLine(mContext.getString(R.string.print_date), TimeUtils.getnowtime(null, mContext), 2.0f, Printer.Font.DEFAULT, 20, Printer.Align.LEFT, false, false, false);

        result = mPrinter.printString("  ", 30, Printer.Align.CENTER, false, false);


        result = mPrinter.printString("  ", 20, Printer.Align.CENTER, false, false);

        result = mPrinter.printString("--------------------------------------------", 30, Printer.Align.CENTER, false, false);
        result = mPrinter.printString("  ", 70, Printer.Align.CENTER, false, false);


        result = mPrinter.printFinish();


    }

    private void wiseasyPrint3(Context mContext, String startTime, String endTime, TradeSummaryReponse reponse) throws RemoteException {
        int result = -1;

        result = mPrinter.printInit();
        mPrinter.clearPrintDataCache();

        if (result != 0) return;

        String merchantName = App.getMerchantName();


        result = mPrinter.printString(merchantName, 30, Printer.Align.CENTER, false, false);
        result = mPrinter.printString("  ", 20, Printer.Align.CENTER, false, false);
        result = mPrinter.printString("  ", 20, Printer.Align.CENTER, false, false);
        result = mPrinter.printString("------------------------------------------", 30, Printer.Align.CENTER, false, false);
        result = mPrinter.printString("  ", 10, Printer.Align.CENTER, false, false);



        result = mPrinter.printString("Start Time", 22, Printer.Align.LEFT, false, false);
        result = mPrinter.printString(startTime, 25, Printer.Align.LEFT, false, false);

        result = mPrinter.printString("End Time", 22, Printer.Align.LEFT, false, false);
        result = mPrinter.printString(endTime, 25, Printer.Align.LEFT, false, false);

        result = mPrinter.printString("Receipt Reference number", 22, Printer.Align.LEFT, false, false);
        result = mPrinter.printString(reponse.getPayTotal(), 25, Printer.Align.LEFT, false, false);


        result = mPrinter.printString("  ", 10, Printer.Align.CENTER, false, false);
        result = mPrinter.printString("------------------------------------------", 30, Printer.Align.CENTER, false, false);
        result = mPrinter.printString("  ", 22, Printer.Align.CENTER, false, false);


        int[] proportionArray = new int[]{ 1, 1, 1};
        String[] contentArray = new String[]{"Type", "Entries", "Amount"};
        result = mPrinter.printMultiseriateString(proportionArray, contentArray, 25, Printer.Align.CENTER, false, false);


        for (TradeSummaryReponse.ListBean item : reponse.getList()) {
            String type = item.getCoinCode();
            String Entries = item.getCoinPayCount();
            String amount = item.getFiatCode() + " " + item.getFiatPayAmount();
            String trade[] = new String[]{type, Entries, amount};
            result = mPrinter.printMultiseriateString(proportionArray, trade, 25, Printer.Align.CENTER, false, false);
        }


        result = mPrinter.printString("  ", 20, Printer.Align.CENTER, false, false);




        Bitmap bitmap2 = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_print_logo);
        result = mPrinter.printImageBase(bitmap2, 310, 70, Printer.Align.CENTER, 0);
        result = mPrinter.printString("  ", 70, Printer.Align.CENTER, false, false);
        result = mPrinter.printString("  ", 20, Printer.Align.CENTER, false, false);
        result = mPrinter.printString("  ", 20, Printer.Align.CENTER, false, false);


        result = mPrinter.printFinish();


    }


}
