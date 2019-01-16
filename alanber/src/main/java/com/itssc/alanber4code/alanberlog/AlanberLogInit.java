package com.itssc.alanber4code.alanberlog;

import android.content.Context;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import alanberlog.AlanberLog;
import alanberlog.Level;
import alanberlog.LogData;
import alanberlog.appender.AndroidAppender;
import alanberlog.appender.FileAppender;
import alanberlog.formatter.DateFileFormatter;
import alanberlog.interceptor.Interceptor;
import alanberlog.logger.AppenderLogger;

public class AlanberLogInit {

    public static final int BUFFER_SIZE = 1024 * 400; //400k

    public static void init(Context context) {
        int level = Level.DEBUG;
        Interceptor wrapInterceptor = new Interceptor() {
            @Override
            public boolean intercept(LogData logData) {
                logData.tag = "AlanberLog-" + logData.tag;
                return true;
            }
        };
        AndroidAppender androidAppender = new AndroidAppender.Builder()
                .setLevel(level)
                .addInterceptor(wrapInterceptor)
                .create();

        File log = FileUtils.getLogDir(context);
        String buffer_path = log.getAbsolutePath() + File.separator + ".logCache";
        String time = new SimpleDateFormat("yyyy_MM_dd", Locale.getDefault()).format(new Date());
        String log_path = log.getAbsolutePath() + File.separator + time + ".txt";
        FileAppender fileAppender = new FileAppender.Builder(context)
                .setLogFilePath(log_path)
                .setLevel(level)
                .addInterceptor(wrapInterceptor)
                .setBufferFilePath(buffer_path)
                .setFormatter(new DateFileFormatter())
                .setCompress(false)
                .setBufferSize(BUFFER_SIZE)
                .create();

        AppenderLogger logger = new AppenderLogger.Builder()
                .addAppender(androidAppender)
                .addAppender(fileAppender)
                .create();
        AlanberLog.setLogger(logger);
    }

}
