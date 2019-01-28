package com.itssc.alanber4code.crash_spider;

import android.content.Context;
import android.content.Intent;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;

import alanberlog.AlanberLog;

public class CrashSpiderTool implements Thread.UncaughtExceptionHandler {

    public static final String TAG = "CrashSpiderTool";

    private static CrashSpiderTool crashSpiderTool = new CrashSpiderTool();

    private static Context mContext;
    private Thread.UncaughtExceptionHandler mExceptionHandler;

    private CrashSpiderTool() {
        mExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    public static CrashSpiderTool init(Context context) {
        mContext = context;
        return crashSpiderTool;
    }

    @Override
    public void uncaughtException(Thread t, Throwable ex) {
        CrashModel model = parseCrash(ex);
        //Crash写入日志系统
        AlanberLog.i("CrashInfo", model.getFullException());
        AlanberLog.flush();
        handleException(model);
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    private void handleException(CrashModel model) {
        Intent intent = new Intent(mContext, CrashActivity.class);
        intent.putExtra(CrashActivity.CRASH_MODEL, model);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    private CrashModel parseCrash(Throwable ex) {
        CrashModel model = new CrashModel();
        try {
            model.setEx(ex);
            model.setTime(new Date().getTime());
            if (ex.getCause() != null) {
                ex = ex.getCause();
            }
            model.setExceptionMsg(ex.getMessage());
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            pw.flush();
            String exceptionType = ex.getClass().getName();

            if (ex.getStackTrace() != null && ex.getStackTrace().length > 0) {
                StackTraceElement element = ex.getStackTrace()[0];

                model.setLineNumber(element.getLineNumber());
                model.setClassName(element.getClassName());
                model.setFileName(element.getFileName());
                model.setMethodName(element.getMethodName());
                model.setExceptionType(exceptionType);
            }

            model.setFullException(sw.toString());
        } catch (Exception e) {
            return model;
        }
        return model;
    }
}