package alanberlog.interceptor;

import alanberlog.LogData;

public interface Interceptor {
    boolean intercept(LogData logData);
}
