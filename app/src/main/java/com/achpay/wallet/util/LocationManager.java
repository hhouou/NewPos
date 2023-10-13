package com.achpay.wallet.util;

/*import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;*/

/**
 * 定位
 * Created by Administrator on 2017/5/11 0011.
 */

public class LocationManager {

  /*  private String location;

    private static LocationManager locationManager = null;
    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    private AMapLocationClientOption mLocationOption = null;

    public static LocationManager getInstance(){
        if (locationManager == null){
            synchronized (LocationManager.class){
                if (locationManager == null){
                    locationManager = new LocationManager();
                }
            }
        }
        return locationManager;
    }

    *//**
     * 启动定位
     * @param context 最好是 applicationcontext
     * @param aMapLocationListener 定位回调
     *//*
    public void startLocation(Context context, AMapLocationListener aMapLocationListener){
        //初始化定位
        mLocationClient = new AMapLocationClient(context);
        //设置定位回调监听
        mLocationClient.setLocationListener(aMapLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //获取一次定位结果：
        mLocationOption.setOnceLocation(true);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //设置是否允许模拟位置,默认为false，不允许模拟位置
        mLocationOption.setMockEnable(false);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    *//**
     * 停止定位
     *//*
    public void stopLocation(){
        //停止定位
        mLocationClient.stopLocation();
        //销毁定位客户端，同时销毁本地定位服务。
        mLocationClient.onDestroy();
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(String location1){
        location = location1;
    }*/
}
