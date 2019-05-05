package com.itssc.tool_network.config;

/**
 * author：dachao on 2019-05-05 11:17
 */
public class ApiException extends Exception {
    public int errorCode;
    public String errorMessage;

    public ApiException(Throwable throwable, int errorCode) {
        super(throwable);
        this.errorCode = errorCode;
    }
}


