package com.itssc.alanber4code.alanberlog;

import android.content.Context;

import java.io.File;

public class FileUtils {

    public static File getLogDir(Context context) {
        File log = context.getExternalFilesDir("logs");
        if (log == null) {
            log = new File(context.getFilesDir(), "logs");
        }
        if (!log.exists()) {
            log.mkdir();
        }
        return log;
    }

}
