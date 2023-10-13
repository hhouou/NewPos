package com.achpay.wallet.util;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.achpay.wallet.app.App;

/**
 * Created by 黑明阳 on 2017/9/27.
 */

public class VersionUtil {

  /**
  2  * 获取版本号
  3  * @return 当前应用的版本号
  4  */
          public static String getVersion() {
            try {
                    PackageManager manager = App.getInstance().getPackageManager();
                     PackageInfo info = manager.getPackageInfo( App.getInstance().getPackageName(), 0);
                    String version = info.versionName;
                    return version;
                 } catch (Exception e) {
                     e.printStackTrace();
                    return "";
                }
        }
}
