package com.achpay.wallet.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.util.Base64;

import com.achpay.wallet.app.App;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;


/**
 * Created by hy on 18/4/21.
 */

public class StrUtils {

    public static int StrToInt(Object str) {
        int i = 0;
        try {
            i = Integer.parseInt(str.toString());
        } catch (Exception e) {
            return i;
        }
        return i;
    }

    public static long StrToLong(String str) {
        long i = 0;
        try {
            i = Long.parseLong(str);
        } catch (Exception e) {
            return i;
        }
        return i;
    }

    public static double StrToDouble(Object str) {
        double i = 0;
        try {
            i = Double.parseDouble(str.toString());
        } catch (Exception e) {
            return i;
        }
        return i;
    }

    public static String ObjectToStr(Object str) {
        String i = "";
        try {
            i = str.toString();
        } catch (Exception e) {
            return i;
        }
        return i;
    }

    //判断不为null也不是空
    public static boolean isNotBlank(String str) {
        try {
            if (str != null && str.length() != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }


    public static String formatData(String amount){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.DOWN);
        return df.format(StrToDouble(amount));
    }
    /**
     * 判断集合是否为空
     *
     * @param c
     * @param <V>
     * @return
     */
    public static <V> boolean isEmpty(Collection<V> c) {
        return (c == null || c.size() == 0);
    }


    //根据long型的数据获取时间值
    public static String getNormalTime(long value) {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH");
        String time = format.format(new Date(value));
        return time;
    }

    public static String getIPAddress(Context context) {
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                try {
                    //Enumeration<NetworkInterface> en=NetworkInterface.getNetworkInterfaces();
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
                return ipAddress;
            }
        } else {
            //当前无网络连接,请在设置中打开网络
        }
        return "";
    }

    /**
     * 验证是否为正确的邮箱
     */
    public static boolean isEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        }
        String emailRegex = "^[A-Za-z0-9\\u4e00-\\u9fa5'\"{}\\\\(\\\\)\\\\[\\\\]\\\\*&.?!,…:;_\\-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
        return email.matches(emailRegex);
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }

    /**
     * 将图片转换成Base64编码的字符串
     *
     * @param path
     * @return base64编码的字符串
     */
    public static String imageToBase64(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }

        Bitmap bitmap = BitmapFactory.decodeFile(path);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, bos);//参数100表示不压缩
        byte[] bytes = bos.toByteArray();
        return Base64.encodeToString(bytes, Base64.DEFAULT);

    }


    /**
     * base64编码字符集转化成图片文件。
     *
     * @param base64Str
     * @param path      文件存储路径
     * @return 是否成功
     */
    public static boolean base64ToFile(String base64Str, String path) {
        byte[] data = Base64.decode(base64Str, Base64.DEFAULT);
        for (int i = 0; i < data.length; i++) {
            if (data[i] < 0) {
                //调整异常数据
                data[i] += 256;
            }
        }
        OutputStream os = null;
        try {
            os = new FileOutputStream(path);
            os.write(data);
            os.flush();
            os.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }


    //复制当前文字
    public static void getCopy(Context context, String txt, String alert) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(context.CLIPBOARD_SERVICE);
        cm.setText(txt);
        if (!alert.isEmpty())
            ToastUtil.shortShow(alert);
    }

    public static boolean isAppInstalled(Context context, String uri) {
        PackageManager pm = context.getPackageManager();
        boolean installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }

    public static double formatCurrency(Object str, int d) {

        BigDecimal decimal = new BigDecimal(str.toString()).setScale(d, RoundingMode.HALF_UP);
        return decimal.doubleValue();

    }

    /**
     * 判断2个时间大小
     * yyyy-MM-dd HH:mm 格式（自己可以修改成想要的时间格式）
     * @param startTime
     * @param endTime
     * @return
     */
    public static int timeCompare(String startTime, String endTime){
        int i=0;
        //注意：传过来的时间格式必须要和这里填入的时间格式相同
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date date1 = dateFormat.parse(startTime);//开始时间
            Date date2 = dateFormat.parse(endTime);//结束时间
            // 1 结束时间小于开始时间 2 开始时间与结束时间相同 3 结束时间大于开始时间
            if (date2.getTime()<date1.getTime()){
                //结束时间小于开始时间
                i= 1;
            }else if (date2.getTime()==date1.getTime()){
                //开始时间与结束时间相同
                i= 2;
            }else if (date2.getTime()>date1.getTime()){
                //结束时间大于开始时间
                i= 3;
            }
        } catch (Exception e) {

        }
        return  i;
    }


}
