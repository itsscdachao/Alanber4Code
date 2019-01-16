package com.itssc.alanber4code;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.itssc.alanber4code.alanberlog.AlanberLogInit;

public class AlanberApplication extends Application {

    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AlanberLogInit.init(this);
    }
}
