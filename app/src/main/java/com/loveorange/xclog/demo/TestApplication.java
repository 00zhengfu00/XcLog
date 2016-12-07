package com.loveorange.xclog.demo;

import android.app.Application;

import com.loveorange.xclog.CrashHandler;
import com.loveorange.xclog.XcFileLog;
import com.loveorange.xclog.demo.test.XcLogConfig;

/**
 * Date: 2016-12-06
 * Time: 16:38
 * Version 1.0
 */

public class TestApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        XcFileLog.init(new XcLogConfig());
        CrashHandler.getInstance().init();
    }
}
