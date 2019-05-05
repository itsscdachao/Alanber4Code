package com.itssc.tool_network.config;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.net.ConnectException;

import retrofit2.HttpException;

public class ExceptionEngine {

    //对应HTTP的状态码
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    public static ApiException handleException(Throwable e) {
        ApiException ex;
        if (e instanceof HttpException) {//HTTP错误
            HttpException httpException = (HttpException) e;
            ex = new ApiException(e, NetWorkErrorCode.BAD_NETWORK_ERROR);
            switch (httpException.code()) {
                case UNAUTHORIZED:
                case FORBIDDEN:
                case NOT_FOUND:
                case REQUEST_TIMEOUT:
                case GATEWAY_TIMEOUT:
                case INTERNAL_SERVER_ERROR:
                case BAD_GATEWAY:
                case SERVICE_UNAVAILABLE:
                default:
                    ex.errorMessage = "网络错误";//均视为网络错误
                    break;
            }
            return ex;
        } else if (e instanceof ServerResponseException) {//服务器返回的错误
            ServerResponseException resultException = (ServerResponseException) e;
            ex = new ApiException(resultException, resultException.errorCode);
            ex.errorMessage = resultException.errorMessage;
            return ex;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            ex = new ApiException(e, NetWorkErrorCode.PARSE_DATA_ERROR);
            ex.errorMessage = "解析错误";//均视为解析错误
            return ex;
        } else if (e instanceof ConnectException) {
            ex = new ApiException(e, NetWorkErrorCode.CONNECT_FAILED_ERROR);
            ex.errorMessage = "连接失败";//均视为网络错误
            return ex;
        } else {
            ex = new ApiException(e, NetWorkErrorCode.UNKNOWN_ERROR);
            ex.errorMessage = "未知错误";//未知错误
            return ex;
        }
    }
}

