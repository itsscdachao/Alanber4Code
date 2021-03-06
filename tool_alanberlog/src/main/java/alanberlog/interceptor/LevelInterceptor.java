package alanberlog.interceptor;

import alanberlog.Level;
import alanberlog.LogData;

public class LevelInterceptor implements Interceptor {

    private int level = Level.VERBOSE;

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean intercept(LogData logData) {
        return logData != null && logData.logLevel >= level;
    }
}
