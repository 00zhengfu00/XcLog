package com.loveorange.xclog.demo;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import java.util.Locale;

/**
 * 应用类
 */
public class AppUtils {
    private AppUtils() {
    }

    public static Application application = null;

    private static String DEVICE_ID;
    private static Handler handler = new Handler();

    /**
     * 获取应用程序的上下文
     *
     * @return 应用程序的上下文
     */
    public static Context getCtx() {
        return application;
    }

    /**
     * 判断是否是Pad
     * @return true 是平板，false 是手机
     * */
    public static boolean isPad(){
        if(application == null){
            return false;
        }
        return (application.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    public static void post(Runnable task) {
        handler.post(task);
    }

    public static boolean isRunOnUIThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }

    /**
     * 获取设备唯一id
     *
     * @return
     */
    public static String getDeviceId() {
        if (DEVICE_ID == null) {
            TelephonyManager tm = (TelephonyManager) getCtx().getSystemService(Context.TELEPHONY_SERVICE);
            try {
                DEVICE_ID = tm.getDeviceId();
            } catch (Throwable t) {
            }
            if (DEVICE_ID == null) {
                DEVICE_ID = Settings.Secure.getString(getCtx().getContentResolver(), Settings.Secure.ANDROID_ID);
            }
        }
        return DEVICE_ID;
    }

    /**
     * 获取当前语言
     *
     * @return
     */
    public static String getLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取版本号(version_code)
     *
     * @return 当前应用的版本号
     */
    public static int getVersionCode() {
        try {
            PackageManager manager = getCtx().getPackageManager();
            PackageInfo info = manager.getPackageInfo(getCtx().getPackageName(), 0);
            int versionCode = info.versionCode;
            return versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 获取设备名称
     *
     * @return
     */
    public static String getDeviceName() {
        return Build.MANUFACTURER + " " + Build.MODEL;
    }

    /**
     * 获取设备操作系统
     *
     * @return
     */
    public static String getDeviceoOS() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 检查设备是否存在SDCard
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 有存储的SDCard
            return true;
        } else {
            return false;
        }
    }

}
