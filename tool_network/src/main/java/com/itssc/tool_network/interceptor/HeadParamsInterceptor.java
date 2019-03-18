package com.itssc.tool_network.interceptor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * author：dachao on 209/3/13 10:04
 */
public class HeadParamsInterceptor implements Interceptor {

    private Map<String, String> mHeaderParamsMap = new HashMap<>();

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request oldRequest = chain.request();
        // 添加新的参数，添加到url 中
        HttpUrl.Builder authorizedUrlBuilder = oldRequest.url().newBuilder().scheme(oldRequest.url().scheme()).host(oldRequest.url().host());

        //新的请求
        Request.Builder requestBuilder = oldRequest.newBuilder();
        requestBuilder.method(oldRequest.method(), oldRequest.body());

        //添加公共参数,添加到header中
        if (mHeaderParamsMap.size() > 0) {
            for (Map.Entry<String, String> params : mHeaderParamsMap.entrySet()) {
                requestBuilder.header(params.getKey(), params.getValue());
            }
        }
        Request newRequest = requestBuilder.build();
        return chain.proceed(newRequest);
    }

    public static class Builder {
        HeadParamsInterceptor mHttpHeaderParamsInterceptor;

        public Builder() {
            mHttpHeaderParamsInterceptor = new HeadParamsInterceptor();
        }

        public HeadParamsInterceptor.Builder addHeaderParams(String key, String value) {
            mHttpHeaderParamsInterceptor.mHeaderParamsMap.put(key, value);
            return this;
        }

        public HeadParamsInterceptor.Builder addHeaderParams(String key, int value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public HeadParamsInterceptor.Builder addHeaderParams(String key, float value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public HeadParamsInterceptor.Builder addHeaderParams(String key, long value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public HeadParamsInterceptor.Builder addHeaderParams(String key, double value) {
            return addHeaderParams(key, String.valueOf(value));
        }

        public HeadParamsInterceptor build() {
            return mHttpHeaderParamsInterceptor;
        }
    }
}
