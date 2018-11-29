package com.itssc.alanber4code.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;

/**
 * author：dachao on 2018/11/29 13:54
 */
public class AppUtil {
    //判断当前应用是否是debug状态
    public static boolean isAppInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
        }

        return false;
    }
}
