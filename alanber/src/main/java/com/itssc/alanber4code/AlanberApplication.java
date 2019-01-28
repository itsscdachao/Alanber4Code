package com.itssc.alanber4code;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.itssc.alanber4code.alanberlog.AlanberLogInit;
import com.itssc.alanber4code.crash_spider.CrashSpiderTool;

public class AlanberApplication extends Application {

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //放在其他库初始化前
        CrashSpiderTool.init(this);
        //初始化NativeLog
        AlanberLogInit.init(this);
    }
}
