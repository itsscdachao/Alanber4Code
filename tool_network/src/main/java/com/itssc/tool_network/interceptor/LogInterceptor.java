package com.itssc.tool_network.interceptor;

import android.util.Log;

import com.itssc.tool_network.config.NetWorkConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * author：dachao on 209/3/13 10:04
 */
public class LogInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long startTime = System.currentTimeMillis();
        Response response = chain.proceed(chain.request());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        Log.e(NetWorkConfig.NET_WORK_TAG, "----------Request Start----------------");
        Log.e(NetWorkConfig.NET_WORK_TAG, "| " + request.toString() + request.headers().toString());
        Log.e(NetWorkConfig.NET_WORK_TAG, "| Response:" + content);
        Log.e(NetWorkConfig.NET_WORK_TAG, "----------Request End: " + duration + " 毫秒----------");
        return response.newBuilder().body(ResponseBody.create(mediaType, content)).build();
    }
}
